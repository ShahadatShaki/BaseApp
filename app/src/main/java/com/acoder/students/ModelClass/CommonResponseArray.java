package com.acoder.students.ModelClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Shaki on 5/12/2017.
 */

public class CommonResponseArray<Item> implements Serializable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ArrayList<Item> object = null;
    @SerializedName("ErrorMgs")
    @Expose
    private String errorMgs;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("success")
    @Expose
    private boolean success;

    private int code;


    public ArrayList<Item> getData() {
        return object;
    }

    public void setObject(ArrayList<Item> object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public boolean isSuccess() {
        return success ;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code ;
    }

    public void setCode(int code) {
        this.code = code;
    }
}