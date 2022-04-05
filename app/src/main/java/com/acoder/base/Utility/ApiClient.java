package com.acoder.base.Utility;

import com.acoder.base.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class ApiClient {
    private static Retrofit retrofit;

//    private static Retrofit retrofit = null;


    public static void initRetrofit() {

        final String tokenId = SharedPreferencesEnum.getUserToken();
        final String id = SharedPreferencesEnum.getUserId();
        final Integer versionCode = BuildConfig.VERSION_CODE;


        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(logging);

        okHttpClient.readTimeout(30, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS);

        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                        .addHeader("Authorization", tokenId)
                        .addHeader("appVersion", String.valueOf(versionCode))
                        .addHeader("id", id).build();
                return chain.proceed(request);
            }
        });

        Gson gson = new GsonBuilder()
                .setLenient()
//                .excludeFieldsWithoutExposeAnnotation()
                .create();


        retrofit = new Retrofit.Builder()
                .baseUrl(URLs.ROOT_URL_MAIN)
                .client(okHttpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static APIInterface getApiClient() {
        return retrofit.create(APIInterface.class);
    }


}
