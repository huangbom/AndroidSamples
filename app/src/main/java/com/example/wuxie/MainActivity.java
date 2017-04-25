package com.example.wuxie;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.wuxie.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_main);

//        MyCartActivity.start(this);
    }

    public void onDataBindingClick(View v){
        DataBindingActivity.start(this);
    }
}
