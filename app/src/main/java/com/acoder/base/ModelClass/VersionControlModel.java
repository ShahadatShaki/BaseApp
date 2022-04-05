package com.acoder.base.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VersionControlModel implements Serializable {
    @SerializedName("isForce")
    @Expose
    private boolean force;
    @SerializedName("version")
    @Expose
    private Integer appVersion;
    @SerializedName("forceable_version")
    @Expose
    private Integer forceableVersion;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("display_version")
    @Expose
    private String displayVersion;
    @SerializedName("change_log")
    @Expose
    private String changeLog;

    public boolean getForce() {
        return force;
    }

    public void setForce(boolean i) {
        force = i;
    }

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {

        return title == null ? new String() : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public Integer getForceableVersion() {

        return forceableVersion == null ? new Integer(0) : forceableVersion;
    }

    public void setForceableVersion(Integer forceableVersion) {
        this.forceableVersion = forceableVersion;
    }

    public String getDisplayVersion() {

        return displayVersion == null ? "" : displayVersion;
    }

    public void setDisplayVersion(String displayVersion) {
        this.displayVersion = displayVersion;
    }

    public String getChangeLog() {

        return changeLog == null ? "" : changeLog;
    }

    public void setChangeLog(String changeLog) {
        this.changeLog = changeLog;
    }
}