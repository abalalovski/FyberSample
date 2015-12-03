package com.twodwarfs.fyber.model;

import com.google.gson.annotations.SerializedName;
import com.twodwarfs.fyber.cons.Fields;

/**
 * Created by Aleksandar Balalovski Balalovski on 20.11.15.
 */

public class Offer extends BaseModel {

    @SerializedName(Fields.TITLE)
    private String mTitle;

    @SerializedName(Fields.OFFER_ID)
    private long mOfferId;

    @SerializedName(Fields.TEASER)
    private String mTeaser;

    @SerializedName(Fields.REQ_ACTIONS)
    private String mRequiredActions;

    @SerializedName(Fields.LINK)
    private String mUrl;

    @SerializedName(Fields.PAYOUT)
    private int mPayout;

    @SerializedName(Fields.THUMBS)
    private Thumbnail mThumb;

    public String getTitle() {
        return mTitle;
    }

    public long getOfferId() {
        return mOfferId;
    }

    public String getTeaser() {
        return mTeaser;
    }

    public String getRequiredActions() {
        return mRequiredActions;
    }

    public String getUrl() {
        return mUrl;
    }

    public int getPayout() {
        return mPayout;
    }

    public Thumbnail getThumb() {
        return mThumb;
    }
}
