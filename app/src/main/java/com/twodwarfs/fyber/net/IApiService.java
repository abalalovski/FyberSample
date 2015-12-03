package com.twodwarfs.fyber.net;

import com.twodwarfs.fyber.cons.Fields;
import com.twodwarfs.fyber.model.OffersWrapper;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IApiService {

    @GET(Fields.SUFFIX)
    Call<OffersWrapper> getOffers(@Query("format") String format,
                                  @Query("appid") String appId,
                                  @Query("uid") String uid,
                                  @Query("locale") String locale,
                                  @Query("timestamp") String timestamp);

}
