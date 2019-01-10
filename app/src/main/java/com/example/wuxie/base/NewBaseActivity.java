package com.example.wuxie.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public abstract class NewBaseActivity extends AppCompatActivity {

    public static final String TAG = NewBaseActivity.class.getSimpleName();
    static final int PERMISSIONS_REQUEST_READ_CONTACTS = 0x100;


    protected NewBaseActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 透明的
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        mActivity = this;


        Log.w("NewBaseActivity", getClass().getName());

    }


    protected void startAppSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, PERMISSIONS_REQUEST_READ_CONTACTS);
    }

    protected <T extends View> T $(int id) {
        return (T) super.findViewById(id);
    }


    protected void showToast(final CharSequence msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), msg + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void showToast(@StringRes int msg) {

        showToast(getText(msg));
    }


    //-------------------------------title bar 的几个点击方法 end--------------------------------------------------------


}
