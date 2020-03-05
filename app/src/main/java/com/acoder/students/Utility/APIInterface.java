package com.acoder.students.Utility;



import com.acoder.students.ModelClass.Class;
import com.acoder.students.ModelClass.CommonResponseSingle;
import com.acoder.students.ModelClass.ModelResponseGET;
import com.acoder.students.ModelClass.UserProfile;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;


public interface APIInterface {



    //region Referral
    @GET("general/merchant")
    Call<ModelResponseGET<Object>> getMerchant();

    @GET("http://ticketbari.info/api/user/74")
    Single<CommonResponseSingle<UserProfile>> getUserProfile();
//
//    @FormUrlEncoded
//    @POST("user")
//    Call<ModelResponsePOST> useReferral(@Field("code") String code);


    //endregion



}
