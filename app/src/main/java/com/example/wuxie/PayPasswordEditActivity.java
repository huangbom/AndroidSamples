package com.example.wuxie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.wuxie.base.NewBaseActivity;

public class PayPasswordEditActivity extends NewBaseActivity {

    public static final String TAG = "PayPasswordEditActivity";

    public static void start(Context context) {
        Intent intent = new Intent(context, PayPasswordEditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_password_edit);

//        PayEditView et =  $(R.id.pay_edit);
//        et.setOnPayInputCompleteListener(new PayEditView.onPayInputCompleteListener() {
//            @Override
//            public void onInputCompleted(String string) {
//                showToast(string);
//            }
//        });
    }
}
