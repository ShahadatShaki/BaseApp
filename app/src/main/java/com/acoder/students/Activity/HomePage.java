package com.acoder.students.Activity;

import android.content.Context;

import androidx.lifecycle.ViewModelProviders;

import com.acoder.students.Base.BaseActivity;

import com.acoder.students.R;
import com.acoder.students.ViewModel.UserControlViewModel;
import com.acoder.students.databinding.ActivityHomePageBinding;

import java.util.ArrayList;

public class HomePage extends BaseActivity {

    ActivityHomePageBinding b;
    Context context;
    UserControlViewModel viewModel;



    @Override
    protected int getLayoutResourceFile() {
        return R.layout.activity_home_page;
    }

    @Override
    protected void initComponent() {
        context = this;
        b = getBinding();

        viewModel = ViewModelProviders.of(this).get(UserControlViewModel.class);

        getUserProfile();

        checkForUpdate();

    }

    public void getUserProfile() {
        viewModel.getUserProfile().observe(this, response->{
            showSuccessToast(response.getMessage());
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
