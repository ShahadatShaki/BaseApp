package com.acoder.students.Utility;



import com.acoder.students.ModelClass.Class;
import com.acoder.students.ModelClass.ModelResponseGET;
import com.acoder.students.ModelClass.ModelResponsePOST;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIInterface {



    //region Referral
    @GET("general/merchant")
    Call<ModelResponseGET<Object>> getMerchant();

    @GET("https://5cded1256f4437001467ac10.mockapi.io/users")
    Call<Class> getData();

    @FormUrlEncoded
    @POST("user")
    Call<ModelResponsePOST> useReferral(@Field("code") String code);


    //endregion



}
