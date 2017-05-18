package com.example.wuxie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.wuxie.base.NewBaseActivity;

public class HeadLinearActivity extends NewBaseActivity {

    public static final String TAG = "HeadLinearActivity";

    public static void start(Context context) {
        Intent intent = new Intent(context, HeadLinearActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_linear);
    }
}
