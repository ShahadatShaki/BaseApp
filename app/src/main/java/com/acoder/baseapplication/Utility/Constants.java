package com.acoder.baseapplication.Utility;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.acoder.baseapplication.R;
import com.crashlytics.android.Crashlytics;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    public static void deleteAllCacheFile(Context context) {
        ArrayList<String> allFile = getOfflineList(context, ALL_FILE);
        for (int i = 0; i < allFile.size(); i++) {
            //context.deleteFile(allFile.get(i));

            saveOffline(context, allFile.get(i), null);
        }
    }

    public static void deleteCacheFile(Context context, String file) {
        context.deleteFile(file);
    }

    public static <CLASS> void saveOffline(Context context, String file, CLASS data) {

        try {
            ArrayList<String> allFiles = getOfflineList(context, ALL_FILE);
            if (allFiles != null) {
                if (!allFiles.contains(file)) {
                    allFiles.add(file);
                    if (!file.equals(ALL_FILE)) {
                        saveOffline(context, ALL_FILE, allFiles);
                    }
                }
            } else {
                allFiles = new ArrayList<>();
                allFiles.add(ALL_FILE);
                saveOffline(context, ALL_FILE, allFiles);
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

//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    public static <CLASS> CLASS getOfflineSingle(Context context, String file) {

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

    public static <CLASS> ArrayList<CLASS> getOfflineList(Context context, String file) {

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
        return items;
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

    public static void loadImage(ImageView imageView, String url) {
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




}
