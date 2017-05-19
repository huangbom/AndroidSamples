package com.example.wuxie.common.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.example.wuxie.R;
import com.example.wuxie.common.correct.SimpleTextWatcher;

/**
 * Created by huangyaoshi on 2017/5/19.
 */

public class PayEditView extends FrameLayout {
    public static final String TAG = "PayEditView";

    public PayEditView(@NonNull Context context) {
        this(context,null);
    }

    public PayEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PayEditView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    GridView mGridView;
    BaseAdapter mGridViewAdapter;
    EditText mEt;
//    LinkedList<String> mList;
    int mCount;
    int length = 6;


    onPayInputCompleteListener mListener;

    int itemLayoutId;

    void init(){
        View.inflate(getContext(), R.layout.pay_edit_text, this);

        itemLayoutId = R.layout.pay_et_item;
//        mList = new LinkedList<>();

        mGridView = (GridView) findViewById(R.id.pay_grid);
        mGridViewAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (null == convertView){
                    convertView = View.inflate(getContext(),itemLayoutId,null);
                }

//                Log.d(TAG, "getView: p:" + position +",count:" + mCount);
                View image = convertView.findViewById(R.id.image);
                image.setVisibility(mCount > position ? View.VISIBLE : View.GONE);

                return convertView;
            }
        };

        mGridView.setAdapter(mGridViewAdapter);

        mEt = (EditText) findViewById(R.id.pay_et);
//        mEt.set
        mEt.addTextChangedListener(new SimpleTextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, String.format("onTextChanged: count :%d,str : %s",count,s));

                mCount = s.length();

                mGridViewAdapter.notifyDataSetChanged();

                if (mCount >= 6 && mListener != null){
                    mListener.onInputCompleted(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public onPayInputCompleteListener getOnPayInputCompleteListener() {
        return mListener;
    }

    public void setOnPayInputCompleteListener(onPayInputCompleteListener mListener) {
        this.mListener = mListener;
    }

    public String getPayText(){
        return mEt.getText().toString();
    }

    public int getItemLayoutId() {
        return itemLayoutId;
    }

    public void setItemLayoutId(int itemLayoutId) {
        this.itemLayoutId = itemLayoutId;
    }

    public interface onPayInputCompleteListener{
        void onInputCompleted(String string);
    }

}
