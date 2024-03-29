package com.acoder.base.Base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acoder.base.databinding.NoItemLayoutBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;


public abstract class BaseFragment extends Fragment {
    private ViewDataBinding binding;
    private ProgressDialog progressDialog;
    private LinearLayout loadingView, noDataView;
    private LinearLayout shimmerFrameLayout;
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
        binding = DataBindingUtil.inflate(layoutInflater, layoutResourceId(), viewGroup, false);
        return binding.getRoot();
    }

    protected abstract Integer layoutResourceId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFragmentComponents();
    }

    public <B> B getBinding() {
        return (B) binding;
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
        if (context == null) return false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                final Network network = connectivityManager.getActiveNetwork();
                if (network != null) {
                    final NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(network);

                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            } else {
                NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
                for (NetworkInfo tempNetworkInfo : networkInfos) {
                    if (tempNetworkInfo.isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
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

    public void initShimmer(LinearLayout view) {
        this.shimmerFrameLayout = view;
        shimmerFrameLayout.setVisibility(View.VISIBLE);
    }

    public void stopShimmer() {

        if (shimmerFrameLayout != null) {
            shimmerFrameLayout.setVisibility(View.GONE);
        }
    }

    public void showSuccessToast(String txt) {
        FancyToast.makeText(context, "" + txt, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }

    public void showFailedToast(String txt) {
        FancyToast.makeText(context, "" + txt, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
    }

    public static void checkEmptyData(int size, NoItemLayoutBinding b) {
        if (size == 0) {
            b.mainLayout.setVisibility(View.VISIBLE);
        } else {
            b.mainLayout.setVisibility(View.GONE);
        }
    }


}