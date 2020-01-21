package com.acoder.students.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.acoder.students.ModelClass.CommonResponseSingle;
import com.acoder.students.Utility.ApiClient;
import com.google.gson.JsonObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserControlViewModel extends AndroidViewModel {


    public UserControlViewModel(@NonNull Application application) {
        super(application);
    }


//    public MutableLiveData<CommonResponseSingle> loginWithEmailPass(JsonObject params) {
//
//        MutableLiveData<CommonResponseSingle> userRegistrationDataResponse = new MutableLiveData<>();
//
//        ApiClient.getApiClient().emailLogin(params).enqueue(new Callback<CommonResponseSingle<UserProfile>>() {
//            @Override
//            public void onResponse(Call<CommonResponseSingle<UserProfile>> call, Response<CommonResponseSingle<UserProfile>> response) {
//                if (response.code() == 200) {
//                    userRegistrationDataResponse.setValue(response.body());
//                } else {
//                    userRegistrationDataResponse.setValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CommonResponseSingle<UserProfile>> call, Throwable t)  {
//                CommonResponseSingle commonResponseSingle = new CommonResponseSingle();
//                commonResponseSingle.setSuccess(false);
//                commonResponseSingle.setDontShowDialog(true);
//                commonResponseSingle.setMessage(t.getLocalizedMessage());
//                userRegistrationDataResponse.setValue(commonResponseSingle);
//            }
//        });
//
//
//        return userRegistrationDataResponse;
//    }
//
//    public MutableLiveData<CommonResponseSingle> requestForEmailVerify(HashMap<String, String> params) {
//
//        MutableLiveData<CommonResponseSingle> userRegistrationDataResponse = new MutableLiveData<>();
//
//        ApiClient.getApiClient().createAccount(params).enqueue(new Callback<CommonResponseSingle<UserProfile>>() {
//            @Override
//            public void onResponse(Call<CommonResponseSingle<UserProfile>> call, Response<CommonResponseSingle<UserProfile>> response) {
//                if (response.code() == 200) {
//                    userRegistrationDataResponse.setValue(response.body());
//                } else {
//                    userRegistrationDataResponse.setValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CommonResponseSingle<UserProfile>> call, Throwable t) {
//                CommonResponseSingle commonResponseSingle = new CommonResponseSingle();
//                commonResponseSingle.setSuccess(false);
//                commonResponseSingle.setDontShowDialog(true);
//                commonResponseSingle.setMessage(t.getLocalizedMessage());
//                userRegistrationDataResponse.setValue(commonResponseSingle);
//            }
//        });
//
//
//        return userRegistrationDataResponse;
//    }


}
