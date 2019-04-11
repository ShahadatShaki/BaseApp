package com.acoder.baseapplication.Utility;



import com.acoder.baseapplication.ModelClass.Class;
import com.acoder.baseapplication.ModelClass.ModelResponseGET;
import com.acoder.baseapplication.ModelClass.ModelResponseGETSingle;
import com.acoder.baseapplication.ModelClass.ModelResponsePOST;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface APIInterface {



    //region Referral
    @GET("general/merchant")
    Call<ModelResponseGET<Object>> getReferral();

    @FormUrlEncoded
    @POST("user/referrals/referred_by")
    Call<ModelResponsePOST> useReferral(@Field("code") String code);


    //endregion



}
