package com.acoder.students.Repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.acoder.students.Base.BaseRepository;
import com.acoder.students.ModelClass.CommonResponseSingle;
import com.acoder.students.ModelClass.UserProfile;
import com.acoder.students.Utility.ApiClient;
import com.acoder.students.Utility.OfflineCache;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserRepository extends BaseRepository {
    private Application application;
//
//    public UserRepository(Application application) {
//        this.application = application;
//    }


    public MutableLiveData<CommonResponseSingle> getUserProfile() {

        MutableLiveData<CommonResponseSingle> liveData = new MutableLiveData<>();

        getOfflineSingle(OfflineCache.USER_PROFILE_FILE, liveData);
        ApiClient.getApiClient().getUserProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<CommonResponseSingle<UserProfile>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(CommonResponseSingle<UserProfile> response) {

                        OfflineCache.saveOffline(OfflineCache.USER_PROFILE_FILE, response);
                        liveData.postValue(response);
                    }

                    @Override
                    public void onError(Throwable e) {
                        liveData.postValue(getResponseSingle(e));
                    }
                });

        return liveData;

    }


}
