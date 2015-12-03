package com.twodwarfs.fyber;

import android.test.suitebuilder.annotation.SmallTest;

import com.twodwarfs.fyber.net.FyberApi;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.lang.Exception;
import java.lang.Override;

import static org.junit.Assert.*;

/**
 * Api Test, I don't know what other can be tested in this sort of app
 */

public class ApiTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @SmallTest
    public void testApiObject() {
        Call<FyberApi> call = FyberApi.getInstance().getOffers();
        assertNotNull(call);
    }

    public void testApiRequestNotNull() {
        Call<OffersWrapper> call = ShutterApi.getInstance().getOffers();
        call.enqueue(new Callback<OffersWrapper>() {
            @Override
            public void onResponse(Response<OffersWrapper> response, Retrofit retrofit) {
                OffersWrapper body = response.body();
                assertThat(body, is(not(null)));
            }

            @Override
            public void onFailure(Throwable t) {
                Logger.doLogException(t);
                Assert.fail("Failed request to server");
            }
        });
    }

    public void testApiRequestNotEmpty() {
        Call<OffersWrapper> call = ShutterApi.getInstance().getOffers();
        call.enqueue(new Callback<OffersWrapper>() {
            @Override
            public void onResponse(Response<OffersWrapper> response, Retrofit retrofit) {
                OffersWrapper body = response.body();
                assertThat(body.getOffers(), is(not(0)));
            }

            @Override
            public void onFailure(Throwable t) {
                Logger.doLogException(t);
                Assert.fail("Failed request to server");
            }
        });
    }
}