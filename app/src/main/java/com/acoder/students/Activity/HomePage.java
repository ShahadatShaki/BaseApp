package com.acoder.students.Activity;

import android.content.Context;
import androidx.databinding.DataBindingUtil;

import com.acoder.students.Base.BaseActivity;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.acoder.students.ModelClass.ModelResponseGET;
import com.acoder.students.R;
import com.acoder.students.Utility.ApiClient;
import com.acoder.students.databinding.ActivityHomePageBinding;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends BaseActivity {

    ActivityHomePageBinding b;
    Context context;



    @Override
    protected int getLayoutResourceFile() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void initComponent() {
        context = this;
        b = getBinding();

        getNearbyPlaces();

    }

    public void getNearbyPlaces() {


        ApiClient.getApiClient().getMerchant().enqueue(new Callback<ModelResponseGET<Object>>() {
            @Override
            public void onResponse(Call<ModelResponseGET<Object>> call,
                                   Response<ModelResponseGET<Object>> response) {

                if (response.code() > 199 && response.code() < 300) {
                    populateList(response.body().getData());
                } else {
                    try {
                        Snackbar.make(b.getRoot(), "Sorry Something went wrong", 3000)
                                .setAction("Try Again", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        getNearbyPlaces();
                                    }
                                }).show();
                    } catch (Exception e) {
                        Toast.makeText(context, "Sorry Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelResponseGET<Object>> call, Throwable t) {

                if (t.getMessage().contains("Unable to resolve host")) {
//                    Constants.showNoInternetSnackbar(context, b.searching);
                    return;
                }

                if (t.getMessage().contains("timed out")) {
                    getNearbyPlaces();
                } else {

                    if (t instanceof IOException) {
                        Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Something went wrong, try again", Toast.LENGTH_SHORT).show();
                    }

                }


            }


        });

    }


    private void populateList(ArrayList<Object> merchantProfiles) {

//        b.recycleView.setVisibility(View.VISIBLE);
//        b.recycleView.setHasFixedSize(true);
//        b.recycleView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//
//        CustomListAdapter adapter = new CustomListAdapter(context, merchantProfiles);
//        b.recycleView.setAdapter(adapter);

    }

}
