package com.acoder.students.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.acoder.students.ModelClass.CommonResponseSingle;
import com.acoder.students.ModelClass.UserProfile;
import com.acoder.students.Utility.ApiClient;
import com.google.gson.JsonObject;

import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserControlViewModel extends AndroidViewModel {


    public UserControlViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<CommonResponseSingle> getUserProfile() {

        MutableLiveData<CommonResponseSingle> liveData = new MutableLiveData<>();

        ApiClient.getApiClient().getUserProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CommonResponseSingle<UserProfile>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(CommonResponseSingle<UserProfile> response) {
                        liveData.postValue(response);
                    }

                    @Override
                    public void onError(Throwable e) {

                        CommonResponseSingle response = new CommonResponseSingle();
                        response.setMsg(e.getLocalizedMessage());
                        response.setSuccess(false);

                        liveData.postValue(response);

                    }
                });

        return liveData;

    }

}