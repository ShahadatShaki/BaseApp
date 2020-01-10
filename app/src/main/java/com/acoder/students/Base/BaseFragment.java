package com.acoder.students.Base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.acoder.students.Base.BaseActivity.ALL_FILE;


public abstract class BaseFragment extends Fragment {
    private ViewDataBinding binding;
    private ProgressDialog progressDialog;
    private LinearLayout loadingView, noDataView;
    private ShimmerFrameLayout shimmerFrameLayout;
    private Context context;
    LayoutInflater layoutInflater;
    ViewGroup viewGroup;

    // Inflate the shimmerFrameLayout for the fragment based on layout XML
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();

        layoutInflater = inflater;
        viewGroup = container;
        return binding.getRoot();
    }

    protected abstract Integer layoutResourceId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFragmentComponents();
    }

    public <B> B getBinding() {
        return (B) DataBindingUtil.inflate(layoutInflater, layoutResourceId(), viewGroup, false);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    protected abstract void initFragmentComponents();



    public Fragment getFragment() {
        return this;
    }

    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void startActivity(Class<?> cls, boolean finishSelf) {
        Intent intent = new Intent(getActivity(), cls);

        startActivity(intent);
        if (finishSelf) {
            getActivity().finish();
        }
    }

//    public ApiInterface getApiService() {
//        return RestClient.getInstance().callRetrofit();
//    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public void showSnackbar(View v, String msg) {
        Snackbar.make(v, msg, 3000).show();
    }


    public void showLoader() {
        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }

        if (noDataView != null) {
            noDataView.setVisibility(View.GONE);
        }
    }

    public void hideLoader() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
        if (noDataView != null) {
            noDataView.setVisibility(View.GONE);
        }
    }

    public void showEmptyView() {
        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
        if (noDataView != null) {
            noDataView.setVisibility(View.VISIBLE);
        }
    }

    public void startActivity(Activity activity, Class<?> cls, boolean finishSelf, Bundle bundle) {
        Intent intent = new Intent(activity, cls);
        intent.putExtras(bundle);
        startActivity(intent);
        if (finishSelf) {
            activity.finish();
        }
    }


    public void initShimmer(ShimmerFrameLayout view) {
        this.shimmerFrameLayout = view;
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        shimmerFrameLayout.startShimmer();
    }

    public void stopShimmer() {
        shimmerFrameLayout.stopShimmer();
        shimmerFrameLayout.setVisibility(View.GONE);
    }

    public void showSuccessToast(String txt) {
        FancyToast.makeText(context, "" + txt, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }

    public void showFailedToast(String txt) {
        FancyToast.makeText(context, "" + txt, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
    }

    public <CLASS> void saveOffline(String file, CLASS data) {

        try {
            ArrayList<String> allFiles = getOfflineList(ALL_FILE);
            if (allFiles != null) {
                if (!allFiles.contains(file)) {
                    allFiles.add(file);
                    if (!file.equals(ALL_FILE)) {
                        saveOffline(ALL_FILE, allFiles);
                    }
                }
            } else {
                allFiles = new ArrayList<>();
                allFiles.add(ALL_FILE);
                saveOffline(ALL_FILE, allFiles);
            }

        } catch (Exception e) {
        }

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(file, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = null;
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
        } catch (Exception e) {
            int i = 0;
//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    public <CLASS> CLASS getOfflineSingle(String file) {

        CLASS items = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(file);
            ObjectInputStream objectInputStream = null;
            objectInputStream = new ObjectInputStream(fileInputStream);
            items = (CLASS) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
        return items;
    }

    public <CLASS> ArrayList<CLASS> getOfflineList(String file) {

        ArrayList<CLASS> items = new ArrayList<>();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(file);
            ObjectInputStream objectInputStream = null;
            objectInputStream = new ObjectInputStream(fileInputStream);
            items = (ArrayList<CLASS>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
        if (items == null) {
            return new ArrayList<>();
        }
        return items;
    }

}