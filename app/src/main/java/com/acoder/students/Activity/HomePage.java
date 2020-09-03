package com.acoder.students.Activity;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acoder.students.Adapter.CommonAdapter;
import com.acoder.students.Base.BaseActivity;

import com.acoder.students.R;
import com.acoder.students.Utility.EndlessRecyclerViewScrollListener;
import com.acoder.students.ViewModel.UserControlViewModel;
import com.acoder.students.databinding.ActivityHomePageBinding;

import java.util.ArrayList;

public class HomePage extends BaseActivity {

    ActivityHomePageBinding b;
    Context context;
    UserControlViewModel viewModel;
    private int totalPage;


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

    private void initRecycleView() {

        ArrayList<String > mcqModels = new ArrayList<>();
        b.recycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        b.recycleView.setLayoutManager(linearLayoutManager);

        CommonAdapter adapter = new CommonAdapter(context, mcqModels);
        b.recycleView.setAdapter(adapter);

        showProgressDialog("Getting Questions..");
//        getMcqQuestions("1");

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                page += 1;
                if (page <= totalPage) {
//                    getMcqQuestions(String.valueOf(page));
//                    b.progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScroll(int dx, int dy) {

            }

        };

        b.recycleView.addOnScrollListener(scrollListener);
        // Adds the scroll listener to RecyclerView


    }


}
