package com.acoder.baseapplication.ModelClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Shaki on 5/12/2017.
 */

public class ModelResponseGET<Item> {

    @SerializedName("msg")
    @Expose
    private String  success;
    @SerializedName("data")
    @Expose
    private ArrayList<Item> object = null;
    @SerializedName("ErrorMgs")
    @Expose
    private String errorMgs;
    @SerializedName("total")
    @Expose
    private String total;



    public ArrayList<Item> getData() {
        return object;
    }

    public void setObject(ArrayList<Item> object) {
        this.object = object;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getErrorMgs() {
        return errorMgs;
    }

    public void setErrorMgs(String errorMgs) {
        this.errorMgs = errorMgs;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}