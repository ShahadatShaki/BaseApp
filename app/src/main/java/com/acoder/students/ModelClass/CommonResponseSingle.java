package com.acoder.students.ModelClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shaki on 5/12/2017.
 */

public class CommonResponseSingle<Item> {

    @SerializedName("result")
    @Expose
    private String msg;
    @SerializedName("reported")
    @Expose
    private boolean reported;
    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("data")
    @Expose
    private Item items = null;

    public String getMessage() {
        return msg;
    }

    public void setMessage(String msg) {
        this.msg = msg;
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
}