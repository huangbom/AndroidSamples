package com.example.wuxie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.wuxie.base.NewBaseActivity;

public class BaoBenProductDetailActivity extends NewBaseActivity {

    public static final String TAG = "BaoBenProductDetailActivity";

    public static void start(Context context) {
        Intent intent = new Intent(context, BaoBenProductDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_ben_product_detail);
    }
}
