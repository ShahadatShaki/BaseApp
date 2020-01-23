package com.acoder.students.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.acoder.students.Base.BaseActivity;
import com.acoder.students.R;
import com.acoder.students.Utility.SharedPreferencesEnum;
import com.acoder.students.databinding.ActivitySplashBinding;

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
                    // startActvity(SplashActivity.this, MainActivity.class, true);
                    startActivity(new Intent(context, HomePage.class));
                    finish();
                } else {
//                     startActvity(SplashActivity.this, SignInActivity.class, true);
                    startActivity(new Intent(SplashActivity.this, HomePage.class));
                    finish();
                }
            }
        }, 1000);
    }


}
