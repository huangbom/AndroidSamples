package com.example.wuxie;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this,R.layout.activity_main);
    }

    public void onDataBindingClick(View v){
        DataBindingActivity.start(this);
    }
}
