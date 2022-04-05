package com.acoder.base.Base;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.acoder.base.ModelClass.VersionControlModel;
import com.acoder.base.R;
import com.acoder.base.Utility.SharedPreferencesEnum;
import com.acoder.base.ViewModel.UserControlViewModel;
import com.acoder.base.databinding.AdminMessageDialogBinding;
import com.acoder.base.databinding.NoItemLayoutBinding;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.shashank.sony.fancytoastlib.FancyToast;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

import static android.text.util.Linkify.ALL;
import static androidx.databinding.DataBindingUtil.inflate;
import static com.acoder.base.R.style.DialogTheme;


public abstract class BaseActivity extends AppCompatActivity {

    public Context context;
    LinearLayout shimmerFrameLayout;
    UserControlViewModel viewModel;
    boolean appVersionDialogViewing = false;
    private ProgressDialog progressDialog;
    private Activity mActivity;
    private View loadingView, noDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        viewModel = ViewModelProviders.of(this).get(UserControlViewModel.class);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setExitTransition(new Explode());
        initVariable();


        //notification count from shared preference

        initComponent();


    }

    protected abstract int getLayoutResourceFile();

    protected abstract void initComponent();

    public <B> B getBinding() {

        return (B) DataBindingUtil.setContentView(this, getLayoutResourceFile());
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

    public void setToolbar(String name) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(name);
        }
    }

    public void setToolbar(String name, boolean enableBackBtn) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(name);

        if (enableBackBtn)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                finish();
            }
        });
    }

    public void callNumber(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
        startActivity(intent);
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
        Picasso.get().load(imgUrl).placeholder(R.drawable.generic_placeholder).error(R.drawable.generic_placeholder).into(imageView);
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

    public void checkForUpdate() {
        //        showProgressDialog("Signing Up..");
        viewModel.getVersionControlModel().observe(this, response -> {
            //            hideProgressDialog();
            if (response != null) {
                if (response.isSuccess()) {
                    checkAppUpdate((VersionControlModel) response.getData());
                } else {
                    showFailedToast(response.getMessage());
                }
            } else {
                showFailedToast(getString(R.string.something_went_wrong));
            }

        });
    }

    private void checkAppUpdate(VersionControlModel versionControlModel) {
        if (versionControlModel == null)
            return;

        if (appVersionDialogViewing)
            return;


        final Integer versionCode = 0;

        if (versionControlModel.getAppVersion() > versionCode) {

            AdminMessageDialogBinding binding = inflate(getLayoutInflater(), R.layout.admin_message_dialog, null, false);

            final Dialog dialog = new Dialog(getContext(), DialogTheme);
            dialog.setContentView(binding.getRoot());

            binding.updateAppBtn.setVisibility(View.VISIBLE);
            binding.tvMessage.setAutoLinkMask(ALL);
            binding.title.setText(versionControlModel.getTitle());
            binding.tvMessage.setText(Html.fromHtml(versionControlModel.getMessage()));


            if (versionControlModel.getForce()) {
                dialog.setCancelable(false);
                binding.btnClose.setVisibility(View.GONE);
            } else {
                binding.btnClose.setVisibility(View.VISIBLE);
                dialog.setCancelable(true);

                if (SharedPreferencesEnum.getInt(SharedPreferencesEnum.Key.APP_UPDATE_SUPPRESED_VERSION) == versionControlModel.getAppVersion()) {
                    return;
                }
            }

            if (versionCode < versionControlModel.getForceableVersion()) {
                dialog.setCancelable(false);
                binding.btnClose.setVisibility(View.GONE);
            }


            binding.btnClose.setOnClickListener(view -> {
                SharedPreferencesEnum
                        .put(SharedPreferencesEnum.Key.APP_UPDATE_SUPPRESED_VERSION, versionControlModel.getAppVersion());
                dialog.dismiss();
            });


            binding.updateAppBtn.setOnClickListener(view -> {
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            });
            dialog.show();
            appVersionDialogViewing = true;


        }

    }

    public void checkMPermission(int type) {

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
//                showPermissionDeniedSnackbar(b.getRoot());
            }


        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }

    public static void checkEmptyData(int size, NoItemLayoutBinding b) {
        if (size == 0) {
            b.mainLayout.setVisibility(View.VISIBLE);
        } else {
            b.mainLayout.setVisibility(View.GONE);
        }
    }

}
