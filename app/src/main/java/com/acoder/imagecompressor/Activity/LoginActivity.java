package com.acoder.imagecompressor.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;

import com.acoder.imagecompressor.Base.BaseActivity;
import com.acoder.imagecompressor.R;
import com.acoder.imagecompressor.ViewModel.UserControlViewModel;
import com.acoder.imagecompressor.databinding.LoginActivityBinding;

/**
 * Created by Shaki
 * 01685558803
 * shahadat.shaki03@gmail.com
 */

public class LoginActivity extends BaseActivity {

    private Context context;
    LoginActivityBinding b;
    UserControlViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(UserControlViewModel.class);

    }

    @Override
    protected int getLayoutResourceFile() {
        return R.layout.login_activity;
    }

    @Override
    protected void initComponent() {
        context = this;

        b = getBinding();

        b.signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HomePage.class));
            }
        });
    }


}
