package com.acoder.base.Utility;

import android.app.Application;
import android.content.Context;

/**
 * Created by SHAKI on 23-Aug-18.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesEnum.initSharedPref(getApplicationContext());
        ApiClient.initRetrofit();
        OfflineCache.initOfflineCache(getApplicationContext());
    }



}