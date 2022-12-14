package com.acoder.imagecompressor.Utility;



import com.acoder.imagecompressor.ModelClass.CommonResponseSingle;
import com.acoder.imagecompressor.ModelClass.CommonResponseArray;
import com.acoder.imagecompressor.ModelClass.UserProfile;
import com.acoder.imagecompressor.ModelClass.VersionControlModel;

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
