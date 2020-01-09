package com.acoder.students.ModelClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Shaki on 5/12/2017.
 */

public class ModelResponseGETSingle<Item> {

    @SerializedName("mgs")
    @Expose
    private String msg;
    @SerializedName("reported")
    @Expose
    private boolean reported;
    @SerializedName("data")
    @Expose
    private Item items = null;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Item getData() {
        return items;
    }

    public void setObject(Item items) {
        this.items = items;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }
}