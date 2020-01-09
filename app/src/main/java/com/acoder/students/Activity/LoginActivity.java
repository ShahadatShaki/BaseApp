package com.acoder.students.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.acoder.students.Base.BaseActivity;
import com.acoder.students.R;
import com.acoder.students.databinding.LoginActivityBinding;

public class LoginActivity extends BaseActivity {

    private Context context;
    LoginActivityBinding b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
