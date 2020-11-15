package com.acoder.students.Base;

import androidx.lifecycle.MutableLiveData;


import com.acoder.students.ModelClass.CommonResponseArray;
import com.acoder.students.ModelClass.CommonResponseSingle;
import com.acoder.students.Utility.OfflineCache;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public class BaseRepository {

    public CommonResponseSingle getResponseSingle(Throwable e) {
        int code = 0;
        try {
            code = ((HttpException) e).code();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        CommonResponseSingle response = new CommonResponseSingle();
        response.setMessage(e.getLocalizedMessage());
        response.setSuccess(false);
        response.setCode(code);

        return response;
    }

    public CommonResponseArray getResponseArray(Throwable e) {
        int code = 0;
        try {
            code = ((HttpException) e).code();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        CommonResponseArray response = new CommonResponseArray();
        response.setMessage(e.getLocalizedMessage());
        response.setSuccess(false);
        response.setCode(code);

        return response;
    }

    public void getOfflineArray(String fileName, MutableLiveData<CommonResponseArray> liveData) {
        //<editor-fold desc="For offline with rx">
        Observable.fromCallable(() -> {
            CommonResponseArray object = OfflineCache.getOfflineSingle(fileName);
            return object;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonResponseArray>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CommonResponseArray object) {
                        if (object != null) {
                            liveData.postValue(object);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //</editor-fold>

    }

    public void getOfflineSingle(String districtFile, MutableLiveData<CommonResponseSingle> liveData) {
        //<editor-fold desc="For offline with rx">
        Observable.fromCallable(() -> {
            CommonResponseSingle object = OfflineCache.getOfflineSingle(districtFile);
            return object;
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommonResponseSingle>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(CommonResponseSingle object) {
                        if (object != null) {
                            liveData.postValue(object);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //</editor-fold>

    }


}
