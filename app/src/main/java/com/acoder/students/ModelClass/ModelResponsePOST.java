package com.acoder.students.ModelClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shaki on 5/12/2017.
 */

public class ModelResponsePOST<CLASS> {

    @SerializedName("mgs")
    @Expose
    private String mgs;
    @SerializedName("errMgs")
    @Expose
    private String errMgs;
    @SerializedName("error_type")
    @Expose
    private String errorType;
    @SerializedName("hasError")
    @Expose
    private Integer hasError;
    @SerializedName("data")
    @Expose
    private CLASS data;

    public String getMgs() {
        return mgs;
    }

    public void setMgs(String mgs) {
        this.mgs = mgs;
    }

    public CLASS getObject() {
        return data;
    }

    public void setData(CLASS data) {
        this.data = data;
    }


    public String getErrMgs() {
        return errMgs;
    }

    public void setErrMgs(String errMgs) {
        this.errMgs = errMgs;
    }

    public Integer getHasError() {
        return hasError;
    }

    public void setHasError(Integer hasError) {
        this.hasError = hasError;
    }

    public String getErrorType() {
        if(errorType==null)
            return "";
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }
}