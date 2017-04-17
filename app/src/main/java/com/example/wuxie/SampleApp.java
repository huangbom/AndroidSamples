package com.example.wuxie;

import android.app.Application;

/**
 * Created by huangyaoshi on 2017/4/17.
 */

public class SampleApp extends Application {

    static SampleApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static SampleApp getInstance(){
        return instance;
    }
}
