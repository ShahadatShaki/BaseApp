package com.acoder.students.Utility;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.material.snackbar.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.acoder.students.R;
import com.crashlytics.android.Crashlytics;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by SHAKI on 30-Dec-17.
 */

public class Constants {

    public static final String UPDATE_FILE = "UPDATE_FILE";
    public static final String SETTINGS = "SETTINGS";
    public static String ALL_FILE = "All_file";


    private static ProgressDialog progressDialog;
    private static Dialog dialog;
    public static boolean dataArrived;


    static String sharedPreferencesFile = "sharedPreferencesFile";


    private static SharedPreferences getPref(Context context) {

        return context.getSharedPreferences(sharedPreferencesFile, MODE_PRIVATE);

    }

    public static void clearSharedprefFile(Context context) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.clear();
        editor.apply();

    }

    public static void saveLogingStage(Context context, boolean login) {
        SharedPreferences.Editor editor = getPref(context).edit();
        editor.putBoolean("login", login);
        editor.apply();
    }

    public static boolean getLogingStage(Context context) {
        return getPref(context).getBoolean("login", false);
    }

    public static boolean isConnected(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public static void showProcessDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        try {
            progressDialog.show();
        } catch (Exception e) {
            Crashlytics.logException(e);
        }
    }

    public static void showProcessDialogNotCancleable(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        try {
            progressDialog.show();
        } catch (Exception e) {
        }
    }

    public static void showProcessDialogCustomText(Context context, String message) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        try {
            progressDialog.show();
        } catch (Exception e) {
        }
    }

    public static void showProcessDialogCustomTextCancleable(Context context, String message) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        try {
            progressDialog.show();
        } catch (Exception e) {
        }
    }

    public static void dissmissProcess() {
        try {
            progressDialog.dismiss();
        } catch (Exception e) {

        }

        try {
            dialog.dismiss();
        } catch (Exception e) {
            Log.d("Dialog", "dissmissProcess: " + e.getLocalizedMessage());
        }
    }

    public static void showNoInternetSnackbar(final Context context, View view) {

        try {
            Snackbar snackbar = Snackbar
                    .make(view, "No internet connection!", Snackbar.LENGTH_LONG)
                    .setActionTextColor(context.getResources().getColor(android.R.color.holo_red_dark))
                    .setAction("CONNECT", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            context.startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));

                        }
                    });

            snackbar.show();
        } catch (Exception e) {
            Toast.makeText(context, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }

    }

    public static void loadImageLand(ImageView imageView, String url) {
        try {
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .resize(1024, 720)
                    .centerCrop()
                    .into(imageView);
        } catch (Exception e) {
            Crashlytics.logException(e);
        }
    }


    public static void loadImageSqure(ImageView imageView, String url, int cornerRadius, int borderRadius) {
        try {
            Transformation transformation = new RoundedTransformationBuilder()
                    .borderColor(Color.WHITE)
                    .borderWidthDp(borderRadius)
                    .cornerRadiusDp(cornerRadius)
                    .oval(false)
                    .build();

            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.round_shape_5dp_corner_light_aysh)
                    .error(R.drawable.round_shape_5dp_corner_light_aysh)
                    .transform(transformation)
                    .resize(800, 800)
                    .centerCrop()
                    .into(imageView);
        } catch (Exception e) {
            Crashlytics.logException(e);
        }
    }




}
