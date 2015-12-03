package com.twodwarfs.fyber.net;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.twodwarfs.fyber.cons.Constants;
import com.twodwarfs.fyber.cons.Fields;
import com.twodwarfs.fyber.model.OffersWrapper;
import com.twodwarfs.fyber.utils.Logger;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Author: Aleksandar Balalovski
 * <p/>
 * Main API interface singleton by which API features can be accessed
 * from anywhere
 */

public class FyberApi implements ApiMethods {
    private static FyberApi mInstance;
    private IApiService mApiService;

    private IApiService getApi() {
        if (mApiService == null) {

            OkHttpClient client = new OkHttpClient();
            LoggingInterceptor logging = new LoggingInterceptor();

            client.interceptors().add(mInterceptor);
            client.interceptors().add(logging);

            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mApiService = restAdapter.create(IApiService.class);
        }
        return mApiService;
    }

    public static FyberApi getInstance() {
        return mInstance == null ? (mInstance = new FyberApi()) : mInstance;
    }

    @Override
    public Call<OffersWrapper> getOffers() {
        String timestamp = System.currentTimeMillis() / 1000L + "";

        return getApi().getOffers("json", Constants.APP_ID,
                Constants.UID, Constants.LOCALE, timestamp);
    }

    private Interceptor mInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            String newUrl = calculateNewUrl(request.url().getQuery());

            Request newRequest = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(newUrl)
                    .build();

            Response response = chain.proceed(newRequest);

            return response;
        }
    };

    private String calculateNewUrl(String query) {
        String[] params = query.split("&");
        Arrays.sort(params);
        String sortedString = Arrays.toString(params);
        sortedString = sortedString.substring(1, sortedString.length() - 1);

        String cleanedQuery = sortedString.replaceAll(",", "&")
                .replaceAll("\\s+", "");

        // calculate hashkey
        String hash = encrypt(cleanedQuery + "&" + Constants.API_KEY);
        String hashKey = "hashkey=" + hash;

        String newUrl = Constants.BASE_URL +
                Fields.SUFFIX + "?" + cleanedQuery + "&" + hashKey;

        return newUrl;
    }

    public static String encrypt(String toEncrypt) {
        StringBuffer sb = new StringBuffer();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            byte[] result = digest.digest(toEncrypt.getBytes());

            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            Logger.doLogException(e);
        }

        return sb.toString();
    }

}
