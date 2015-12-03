package com.twodwarfs.fyber.model;

import com.google.gson.annotations.SerializedName;
import com.twodwarfs.fyber.cons.Fields;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandar Balalovski on 03.12.15.
 */
public class OffersWrapper extends BaseModel {

    @SerializedName(Fields.CODE)
    private String mCode;

    @SerializedName(Fields.MESSAGE)
    private String mMessage;

    @SerializedName(Fields.COUNT)
    private int mCount;

    @SerializedName(Fields.PAGES)
    private int mPages;

    @SerializedName(Fields.OFFERS)
    private List<Offer> mOffers = new ArrayList<>();

    public List<Offer> getOffers() {
        return mOffers;
    }
}
