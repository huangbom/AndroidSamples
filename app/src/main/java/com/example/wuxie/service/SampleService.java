package com.example.wuxie.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class SampleService extends Service {

    public static final String TAG = "SampleService";

    public SampleService() {
    }



    private MyBinder binder = new MyBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand: ");
        
        return super.onStartCommand(intent, flags, startId);
    }

    public class MyBinder extends Binder{

        public SampleService getService(){
            return SampleService.this;
        }

    }

}
