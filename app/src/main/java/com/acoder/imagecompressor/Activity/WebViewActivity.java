package com.acoder.imagecompressor.Activity;

import android.webkit.WebViewClient;

import com.acoder.imagecompressor.Base.BaseActivity;
import com.acoder.imagecompressor.R;
import com.acoder.imagecompressor.databinding.ActivityWebViewBinding;

/**
 * Created by Shaki
 * 01685558803
 * shahadat.shaki03@gmail.com
 */

public class WebViewActivity extends BaseActivity {

    private ActivityWebViewBinding binding;

    @Override
    protected int getLayoutResourceFile() {

        return R.layout.activity_web_view;
    }

    @Override
    protected void initComponent() {

        binding = getBinding();
        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");

        setToolbar(title);
        //Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        binding.webView.setWebViewClient(new WebViewClient());

        binding.webView.loadUrl(url);
    }

}
