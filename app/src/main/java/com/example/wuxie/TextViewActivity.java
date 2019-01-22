package com.example.wuxie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.wuxie.base.NewBaseActivity;
import com.example.wuxie.common.widget.NumberEditText;

public class TextViewActivity extends NewBaseActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        NumberEditText net = $(R.id.net);
        net.setMax(10000.0);
        net.setMin(100.0);
        net.setFormat("#0.00");
                Chronometer ch = $(R.id.chronometer);
                ch.setFormat("HH:mm:ss");
                ch.setBase(SystemClock.elapsedRealtime() + 1000);
                ch.start();
    }

}
