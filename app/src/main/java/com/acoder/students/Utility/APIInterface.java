package com.acoder.students.Utility;



import com.acoder.students.ModelClass.CommonResponseSingle;
import com.acoder.students.ModelClass.CommonResponseArray;
import com.acoder.students.ModelClass.UserProfile;
import com.acoder.students.ModelClass.VersionControlModel;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;


public interface APIInterface {



    //region Referral
    @GET("general/merchant")
    Call<CommonResponseArray<Object>> getMerchant();

    @GET("http://ticketbari.info/api/user/74")
    Single<CommonResponseSingle<UserProfile>> getUserProfile();

    @GET("http://ticketbari.info/api/user/74")
    Single<CommonResponseSingle<VersionControlModel>> getVersionControlModel();
//
//    @FormUrlEncoded
//    @POST("user")
//    Call<ModelResponsePOST> useReferral(@Field("code") String code);


    //endregion



}
