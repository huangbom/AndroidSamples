package com.example.wuxie.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.wuxie.R;

/**
 * Created by huangyaoshi on 2017/4/20.
 */
public class AddReduceEditText extends LinearLayout {

    private static final String TAG = "AddReduceEditText";

    public AddReduceEditText(Context context) {
        super(context);
        initView();
    }

    public AddReduceEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    View mAddView,mReduceView;
    EditText mEditText;

    int mMax = Integer.MAX_VALUE,mMin = 0;

    void initView(){
        View inflate = View.inflate(getContext(), R.layout.add_reduce_et_view, this);
        mAddView = inflate.findViewById(R.id.iv_add);
        mReduceView = inflate.findViewById(R.id.iv_reduce);
        mEditText = (EditText)inflate.findViewById(R.id.et_number);

        mAddView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = mEditText.getText().toString();
                    int value = Integer.valueOf(text);
                    if (value >= mMax){
                        Log.d(TAG, "onClick: value max:"+value);
                        return;
                    }

                    value++;

                    mEditText.setText(value+"");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        mReduceView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = mEditText.getText().toString();
                    int value = Integer.valueOf(text);
                    if (value <= mMin){
                        Log.d(TAG, "onClick: value min:"+value);
                        return;
                    }

                    value--;

                    mEditText.setText(value+"");
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public String getValue(){
        return mEditText.getText().toString();
    }


//
//    <LinearLayout
//    android:id="@+id/llGoodLeft"
//    android:layout_width="wrap_content"
//    android:layout_height="wrap_content"
//    android:layout_alignParentLeft="true"
//    android:layout_centerVertical="true"
//    android:layout_toLeftOf="@+id/tvDel"
//            >
//
//            <ImageView
//    android:id="@+id/ivAdd"
//    android:layout_width="0dp"
//    android:layout_height="wrap_content"
//    android:layout_weight="1"
//    android:src="@drawable/ic_add"/>
//
//            <TextView
//    android:id="@+id/tvNum2"
//    android:layout_width="0dp"
//    android:layout_height="wrap_content"
//    android:layout_weight="1"
//    android:gravity="center_horizontal|center_vertical"
//    android:text="1"/>
//
//            <ImageView
//    android:id="@+id/ivReduce"
//    android:layout_width="0dp"
//    android:layout_height="wrap_content"
//    android:layout_weight="1"
//    android:src="@drawable/ic_reduce"/>
//
//        </LinearLayout>
}
