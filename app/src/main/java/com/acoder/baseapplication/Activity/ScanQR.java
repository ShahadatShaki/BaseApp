package com.acoder.baseapplication.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.acoder.baseapplication.R;
import com.acoder.baseapplication.databinding.ActivityScanQrBinding;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.crashlytics.android.Crashlytics;
import com.google.zxing.Result;

public class ScanQR extends AppCompatActivity {

    ActivityScanQrBinding b;
    Context context;
    boolean isActivityOpend;
    CodeScanner mCodeScanner;
    CodeScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_scan_qr);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("QR Code");
        context = this;

        scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadQR() {

        final String qrNotMatchMessage = "Qr doesn't match, Tab to retry";
        if(mCodeScanner.isPreviewActive()){return;}


        try {
            //<editor-fold desc="Scanner handle">
            mCodeScanner.setDecodeCallback(new DecodeCallback() {
                @Override
                public void onDecoded(@NonNull final Result result) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //                        Toast.makeText((Acti this, result.getText(), Toast.LENGTH_SHORT).show();
                            String text = result.getText();
                            if (!isActivityOpend) {
                                try {
                                    Toast.makeText(context, ""+text, Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Crashlytics.logException(e);
                                    Toast.makeText(context, "Something went wrong, Tab to retry", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }
            });
            //</editor-fold>
        } catch (Exception e) {
            Toast.makeText(context, "Unable to open camera", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            Crashlytics.logException(e);
        }

        mCodeScanner.startPreview();

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //<editor-fold desc="Camera Permission">
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.CAMERA)) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CAMERA}, 409);
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{Manifest.permission.CAMERA}, 409);
                }
                return;

            }
        }
        //</editor-fold>
        loadQR();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                mCodeScanner.releaseResources();

            }
        }else{
            mCodeScanner.releaseResources();
        }
        super.onPause();

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 409:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    loadQR();
                    mCodeScanner.startPreview();
                    break;
                }else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0])) {
                    Snackbar snackbar = Snackbar.make(b.scannerView, getResources().getString(R.string.message_no_camera_permission_snackbar), 10000);
                    snackbar.setAction(getResources().getString(R.string.allow), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivity(intent);
                        }
                    });
                    snackbar.show();
                } else {
                    finish();
                }
        }
    }
}
