package com.example.wuxie.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wuxie.R;
import com.example.wuxie.goldapi.GoldApiUtils;
import com.example.wuxie.goldapi.GoldClient;


/**
 * 此类取代BaseActivity,使用的是5.0主题
 *
 * @author jiaohongyun
 * @date 2015年5月22日
 */
public abstract class NewBaseActivity extends AppCompatActivity {

    public static final String TAG = NewBaseActivity.class.getSimpleName();


    protected Context mContext;
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

        mContext = this;
        mActivity = this;

//        // 添加Activity到堆栈
//        AppManager.getAppManager().addActivity(this);
//        lastClickTimes = new SparseArray<>();


        Log.w("NewBaseActivity", getClass().getName());

    }

    protected GoldClient getGoldClient(){
        return GoldApiUtils.getGoldClient();
    }

    static final int PERMISSIONS_REQUEST_READ_CONTACTS = 0x100;

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


    //-------------------------------title bar 的几个点击方法 1.3新增----需要使用 @layout/title_layout_1_3 布局--------------------------------------------------------
    public void onBackClick(View view) {
        onBackPressed();
    }

    public void onTitleClick(View view) {
        // defalut do nothing
    }

    public void onRight2Click(View view) {
        Log.e(TAG, "必须实现 onRight2Click 方法");
//        throw new RuntimeException("必须实现 onRight2Click 方法");
    }

    public void onRight1Click(View view) {
        Log.e(TAG, "必须实现 onRight1Click 方法");
//        throw new RuntimeException("必须实现 onRight1Click 方法");
    }

    public void setTitle(CharSequence title) {
        TextView tv = (TextView) findViewById(R.id.tb_title);
        tv.setText(title);
    }

    public void setTitle(@StringRes int title) {
        TextView tv = (TextView) findViewById(R.id.tb_title);
        tv.setText(title);
    }

    public void setMenuTitle(CharSequence title1, CharSequence title2) {
        try {
            TextView tv =  $(R.id.tb_right_tv1);
            TextView tv2 =   $(R.id.tb_right_tv2);
            tv.setText(title1);
            tv2.setText(title2);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    //-------------------------------title bar 的几个点击方法 end--------------------------------------------------------

    public boolean isCompatDestroyed() {
        if (isFinishing()) return true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (isDestroyed()) return true;
        }

        return false;
    }


}
