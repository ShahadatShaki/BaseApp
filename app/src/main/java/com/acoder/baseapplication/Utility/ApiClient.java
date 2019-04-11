package com.acoder.baseapplication.Utility;

import android.content.Context;

import com.acoder.baseapplication.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiClient {

//    private static Retrofit retrofit = null;

    public static APIInterface getAPIInterface(Context context) {

//        final String tokenId = Constants.getUserToken(context);
//        final String id = Constants.getUserId(context);
        final Integer versionCode = BuildConfig.VERSION_CODE;


        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.readTimeout(15, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(15, TimeUnit.SECONDS);

        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("Authorization", "")
                        .addHeader("appVersion", String.valueOf(versionCode))
                        .addHeader("id","").build();
                return chain.proceed(request);
            }
        });

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit;

            retrofit = new Retrofit.Builder()
                    .baseUrl(URLs.ROOT_URL_MAIN)
                    .client(okHttpClient.build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        return retrofit.create(APIInterface.class);
    }




}
