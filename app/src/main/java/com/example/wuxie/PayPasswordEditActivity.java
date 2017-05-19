package com.example.wuxie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PayPasswordEditActivity extends AppCompatActivity {

    public static final String TAG = "PayPasswordEditActivity";

    public static void start(Context context) {
        Intent intent = new Intent(context, PayPasswordEditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_password_edit);
    }
}
