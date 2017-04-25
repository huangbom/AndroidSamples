package com.example.wuxie.bieguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.wuxie.R;

public class BieGuide2Activity extends Activity {

    private static final String TAG = "BieGuide2Activity";

    public static void start(Context context) {
        Intent intent = new Intent(context, BieGuide2Activity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide_activity2);


        final View target = findViewById(R.id.text2);


        Log.d(TAG, "addRelativeView:getX "+target.getX());
        Log.d(TAG, "addRelativeView:getY "+target.getY());
        Log.d(TAG, "addRelativeView:getTop "+target.getTop());
        Log.d(TAG, "addRelativeView:getLeft "+target.getLeft());
        Log.d(TAG, "addRelativeView:getBottom "+target.getBottom());
        Log.d(TAG, "addRelativeView:getBottom "+target.getBottom());
        Log.d(TAG, "addRelativeView:getBottom------------------------------------------- ");
//        Log.d(TAG, "addRelativeView:rectF.left "+rectF.left);
//        Log.d(TAG, "addRelativeView:rectF.top "+rectF.top);

        target.post(new Runnable() {
            @Override
            public void run() {




                Log.d(TAG, "addRelativeView:getX "+target.getX());
                Log.d(TAG, "addRelativeView:getY "+target.getY());
                Log.d(TAG, "addRelativeView:getTop "+target.getTop());
                Log.d(TAG, "addRelativeView:getLeft "+target.getLeft());
                Log.d(TAG, "addRelativeView:getBottom "+target.getBottom());
            }
        });

        imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.guide_blue_love);

//        new NewbieGuideManager(this, NewbieGuideManager.TYPE_LIST).addView(findViewById(R.id.text3), HoleBean.TYPE_CIRCLE).show();
    }

    ImageView imageView ;

    public void  hello1(View view){
        new NewbieGuideManager(this, NewbieGuideManager.TYPE_LIST)
                .addView(findViewById(R.id.text2), HoleBean.TYPE_CIRCLE)
                .addView(findViewById(R.id.text2),imageView,0,0)
                .show();

    }


    public void  hello2(View view){
        new NewbieGuideManager(this, NewbieGuideManager.TYPE_LIST)
                .addView(findViewById(R.id.text), HoleBean.TYPE_OVAL)
                .addView(findViewById(R.id.text),imageView,0,0)
                .show();

    }


    public void  hello3(View view){
        new NewbieGuideManager(this, NewbieGuideManager.TYPE_LIST)
                .addView(findViewById(R.id.image), HoleBean.TYPE_ROUDRECT)
                .addView(findViewById(R.id.image),imageView,0,-0.2f)
                .show();

    }

//
//    new NewbieGuideManager(MainActivity.this, NewbieGuideManager.TYPE_LIST).addView(view.getChildAt(0)
//                                    .findViewById(R.id.logo), HoleBean.TYPE_RECTANGLE).show();
}
