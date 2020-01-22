package com.acoder.students.Base;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.acoder.students.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;


public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Activity mActivity;
    private ViewDataBinding binding;


    public Context context;
    ShimmerFrameLayout shimmerFrameLayout;
    private View loadingView, noDataView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        initVariable();


        //notification count from shared preference

        initComponent();

    }



    protected abstract int getLayoutResourceFile();

    protected abstract void initComponent();




    public<B> B getBinding() {
        return  (B)DataBindingUtil.setContentView(this, getLayoutResourceFile());
    }


    private void initVariable() {
        context = getApplicationContext();
        mActivity = BaseActivity.this;
    }


    public void showProgressDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog = ProgressDialog.show(BaseActivity.this, null, "Please Wait...", true);
            }
        });
    }

    public void showProgressDialog(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog = ProgressDialog.show(BaseActivity.this, null, msg, true);
            }
        });
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                }
            });
        }
    }



    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




//    public void initLoader() {
//        loadingView = (LinearLayout) findViewById(R.id.loadingView);
//        noDataView = (LinearLayout) findViewById(R.id.noDataView);
//    }

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


    public Activity getActivity() {
        return this;
    }

    public void startActivity(Class<?> cls, boolean finishSelf, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        if (finishSelf) {
            finish();
        }
    }

    public void startActivity(Class<?> cls, boolean finishSelf) {
        Intent intent = new Intent(context, cls);

//        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        startActivity(intent);
        if (finishSelf) {
            finish();
        }
    }



    public Context getContext() {
        return this;
    }


    public void showSuccessToast(String txt) {
        FancyToast.makeText(mActivity, "" + txt, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();
    }

    public void showFailedToast(String txt) {
        FancyToast.makeText(mActivity, "" + txt, FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
    }

    public void datePickerDialog(final EditText editText) {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                String selectedDate = (year + "-" + month + "-" + dayOfMonth);

                editText.getText().clear();
                editText.setText(selectedDate);
            }
        };

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                listener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();

    }


    public void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.ThemeOverlay_Material_Dialog_Alert
            );
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        month += 1;
        return year + "-" + month + "-" + day;
    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

    protected void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }







    public void enableBackButton() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }




    public void setToolbar(String name) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(name);
        }
    }







    @Override
    protected void onStart() {


//        if (onStartCount > 1) {
//            this.overridePendingTransition(R.anim.anim_slide_in_right,
//                    R.anim.anim_slide_out_right);
//
//        } else if (onStartCount == 1) {
//            onStartCount++;
//        }

        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void showImage(ImageView imageView, String imgUrl) {
        Picasso.get().load(imgUrl).placeholder(R.drawable.ic_place_image).error(R.drawable.ic_place_image).into(imageView);
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

    @Override
    protected void onPause() {
        if (shimmerFrameLayout != null)
            shimmerFrameLayout.stopShimmer();
        super.onPause();
    }


    public static final String ALL_FILE = "ALL_FILE";
    public static final String USER_PROFILE_FILE = "USER_PROFILE_FILE";
    public static final String USER_FAVOURITE_PACKEGES = "USER_FAVOURITE_PACKEGES";
    public static final String RECENT_BUS_SEARCH = "RECENT_BUS_SEARCH";
    public static final String HOME_BANNER_IMAGES = "HOME_BANNER_IMAGES";
    public static final String BUS_TERMINALS = "BUS_TERMINALS";
    public static final String VISA_COUNTRY_LIST = "VISA_COUNTRY_LIST";
    public static final String VISA_TYPE_LIST = "VISA_TYPE_LIST";
    public static final String BUS_SEARCH = "BUS_SEARCH";
    public static final String NOTIFICATION_LIST = "NOTIFICATION_LIST";

    //<editor-fold desc="Handle Cache">


    public void deleteAllCacheFile() {
        ArrayList<String> allFile = getOfflineList(ALL_FILE);
        for (int i = 0; i < allFile.size(); i++) {
            //context.deleteFile(allFile.get(i));

            saveOffline(allFile.get(i), null);
        }
    }

    public void deleteCacheFile(String file) {
        context.deleteFile(file);
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
    //</editor-fold>


}