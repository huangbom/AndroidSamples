package com.example.wuxie.app;

import android.app.Application;

import com.example.wuxie.ServiceGenerator;
import com.facebook.stetho.Stetho;

/**
 * Created by huangyaoshi on 2017/4/17.
 */

public class SampleApp extends Application {

    static SampleApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Stetho.initializeWithDefaults(this);
    }

    public static SampleApp getInstance(){
        return instance;
    }

    public void <S> S createService(Class<S> serviceClass) {
        return ServiceGenerator.createService(serviceClass);
    }
}
