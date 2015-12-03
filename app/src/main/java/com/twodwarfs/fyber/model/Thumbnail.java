package com.twodwarfs.fyber.model;

import com.google.gson.annotations.SerializedName;
import com.twodwarfs.fyber.cons.Fields;

/**
 * Created by Aleksandar Balalovski on 3.12.15.
 */

public class Thumbnail extends BaseModel {

    @SerializedName(Fields.LOWRES)
    private String mLowRes;

    @SerializedName(Fields.HIRES)
    private String mHighRes;

    public String getLowRes() {
        return mLowRes;
    }

    public String getHighRes() {
        return mHighRes;
    }
}
