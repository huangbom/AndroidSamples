package com.example.wuxie;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.wuxie.base.BaseActivity;
import com.example.wuxie.goldapi.RetrofitSampleActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_main);

//        RetrofitSampleActivity.start(this);
//        MyCartActivity.start(this);
    }

    public void onDataBindingClick(View v){
        DataBindingActivity.start(this);
    }
    public void retrofit(View v){
        RetrofitSampleActivity.start(this);
    }
}
