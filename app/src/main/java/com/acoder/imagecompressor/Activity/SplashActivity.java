package com.acoder.imagecompressor.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.acoder.imagecompressor.Base.BaseActivity;
import com.acoder.imagecompressor.R;
import com.acoder.imagecompressor.Utility.SharedPreferencesEnum;
import com.acoder.imagecompressor.databinding.ActivitySplashBinding;

/**
 * Created by Shaki
 * 01685558803
 * shahadat.shaki03@gmail.com
 */

public class SplashActivity extends BaseActivity {

    ActivitySplashBinding binding;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);

        context = this;
    }

    @Override
    protected int getLayoutResourceFile() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initComponent() {

        binding =  getBinding();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // startActvity(SplashActivity.this, MainActivity.class, true);
                if (SharedPreferencesEnum.getBoolean(SharedPreferencesEnum.Key.IS_LOGIN)) {
                    startActivity(new Intent(context, HomePage.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, HomePage.class));
                    finish();
                }
            }
        }, 1000);
    }


}
