package com.acoder.students.Utility;

import android.app.Application;

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