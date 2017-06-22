package com.example.wuxie;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import android.view.View;

import com.example.wuxie.base.NewBaseActivity;
import com.example.wuxie.service.SampleService;

public class BindServiceActivity extends NewBaseActivity {

    public static final String TAG = "BindServiceActivity";

    public static void start(Context context) {
        Intent intent = new Intent(context, BindServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            SampleService.MyBinder binder = (SampleService.MyBinder) service;
            SampleService service1 = binder.getService();

        }

        //client 和service连接意外丢失时，会调用该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v("hjz","onServiceDisconnected  A");
        }
    };

    public void bindService(View view){
        Intent intent = new Intent(this, SampleService.class);
        startService(intent);
        bindService(intent,conn,BIND_AUTO_CREATE);
    }


    public void unBind(View view){
        unbindService(conn);
    }


    public void exit(View view){
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
