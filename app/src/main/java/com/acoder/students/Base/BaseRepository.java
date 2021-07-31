package com.acoder.students.Base;

import androidx.lifecycle.MutableLiveData;

import com.acoder.students.ModelClass.CommonResponseArray;
import com.acoder.students.ModelClass.CommonResponseSingle;
import com.acoder.students.Utility.OfflineCache;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;

import java.util.Iterator;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class BaseRepository {

    public CommonResponseSingle getResponseSingle(Throwable e) {
        CommonResponseSingle errorParser = new CommonResponseSingle();

        if (e instanceof HttpException) {
            ResponseBody body = ((HttpException) e).response().errorBody();
            Gson gson = new Gson();
            TypeAdapter<CommonResponseSingle> adapter = gson.getAdapter(CommonResponseSingle.class);
            try {
                errorParser = adapter.fromJson(body.string());
                errorParser.setCode(((HttpException) e).code());

                final JsonObject errorsObject = errorParser.getErrors();
                Iterator<String> keys = errorParser.getErrors().keySet().iterator();
                String errors = "";
                while (keys.hasNext()) {
                    final String key = keys.next();
                    final String jsonString = errorsObject.get(key).toString()
                            .replace("]", "")
                            .replace("[", "")
                            .replace("\"", "");
                    errors = errors + (jsonString + "\n");
                }

                errorParser.setMessage(errorParser.getMessage() + "\n" + errors);
            } catch (Exception t) {
                t.printStackTrace();
            }
        }
        errorParser.setSuccess(false);
        return errorParser;
    }

    public CommonResponseArray getResponseArray(Throwable e) {
        CommonResponseArray errorParser = new CommonResponseArray();

        if (e instanceof HttpException) {
            ResponseBody body = ((HttpException) e).response().errorBody();
            Gson gson = new Gson();
            TypeAdapter<CommonResponseArray> adapter = gson.getAdapter(CommonResponseArray.class);
            try {
                errorParser = adapter.fromJson(body.string());
                errorParser.setCode(((HttpException) e).code());

                final JsonObject errorsObject = errorParser.getErrors();
                Iterator<String> keys = errorParser.getErrors().keySet().iterator();
                String errors = "";
                while (keys.hasNext()) {
                    final String key = keys.next();
                    final String jsonString = errorsObject.get(key).toString()
                            .replace("]", "")
                            .replace("[", "")
                            .replace("\"", "");
                    errors = errors + (jsonString + "\n");
                }

                errorParser.setMessage(errorParser.getMessage() + "\n" + errors);
            } catch (Exception t) {
                t.printStackTrace();
            }
        }
        errorParser.setSuccess(false);
        return errorParser;
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
