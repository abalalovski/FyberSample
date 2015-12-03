package com.twodwarfs.fyber.net;

import com.twodwarfs.fyber.model.OffersWrapper;

import retrofit.Call;

public interface ApiMethods {

    Call<OffersWrapper> getOffers();

}
