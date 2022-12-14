package com.acoder.imagecompressor.ModelClass;


import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Shaki on 5/12/2017.
 */

public class CommonResponseSingle<Item>  implements Serializable {

    @SerializedName("result")
    @Expose
    private String message;
    @SerializedName("reported")
    @Expose
    private boolean reported;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private Item items = null;
    private int code;


    @SerializedName("errors")
    @Expose
    private JsonObject errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public Item getData() {
        return items;
    }

    public void setData(Item items) {
        this.items = items;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public boolean isSuccess() {
        return success ;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JsonObject getErrors() {
        return errors == null ? new JsonObject() : errors;
    }

    public void setErrors(JsonObject errors) {
        this.errors = errors;
    }
}