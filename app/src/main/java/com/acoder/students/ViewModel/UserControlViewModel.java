package com.acoder.students.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.acoder.students.ModelClass.CommonResponseSingle;
import com.acoder.students.ModelClass.VersionControlModel;
import com.acoder.students.Repository.UserRepository;

public class UserControlViewModel extends AndroidViewModel {

    UserRepository userRepository;

    public UserControlViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository();

    }


    public MutableLiveData<CommonResponseSingle> getUserProfile() {
        return userRepository.getUserProfile();
    }

    public MutableLiveData<CommonResponseSingle> getVersionControlModel() {

        MutableLiveData<CommonResponseSingle> liveData = new MutableLiveData<>();

        VersionControlModel versionControlModel = new VersionControlModel();
        versionControlModel.setAppVersion(2);
        versionControlModel.setForceableVersion(0);
        versionControlModel.setForce(false);
        versionControlModel.setMessage("This is dami text");
        versionControlModel.setTitle("This is title");
        CommonResponseSingle response = new CommonResponseSingle();
        response.setSuccess(true);
        response.setData(versionControlModel);
        liveData.postValue(response);

        return liveData;

//        //<editor-fold desc="For offline with rx">
//        Observable.fromCallable(() -> {
//            VersionControlModel object = OfflineCache.getOfflineSingle(OfflineCache.APP_VERSION);
//            return object;
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<VersionControlModel>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(VersionControlModel object) {
//
//                        if (object != null) {
//                            CommonResponseSingle response = new CommonResponseSingle();
//                            response.setSuccess(true);
//                            response.setData(object);
//                            liveData.postValue(response);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//        //</editor-fold>
//
//
//        ApiClient.getApiClient().getVersionControlModel()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<CommonResponseSingle<VersionControlModel>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onSuccess(CommonResponseSingle<VersionControlModel> response) {
//                        VersionControlModel object = response.getData();
//                        OfflineCache.saveOffline(OfflineCache.APP_VERSION, object);
//                        liveData.postValue(response);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                        CommonResponseSingle response = new CommonResponseSingle();
//                        response.setMessage(e.getLocalizedMessage());
//                        response.setSuccess(false);
//
//                        liveData.postValue(response);
//
//                    }
//                });
//
//        return liveData;

    }

}