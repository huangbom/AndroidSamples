package com.example.wuxie.common.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

import com.example.wuxie.common.filter.DecimalInputFilter;
import com.example.wuxie.common.utils.ConvertUtils;

import java.text.DecimalFormat;

/**
 * Created by huangyaoshi on 2017/5/9.
 * 失去焦点自动格式化
 * 限制大小写
 */
public class NumberEditText extends AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {
    public NumberEditText(Context context) {
        this(context,null);
    }

    public NumberEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.editTextStyle);
    }

    public NumberEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        super.setOnFocusChangeListener(this);
        super.addTextChangedListener(this);
        super.setFilters(new InputFilter[]{new DecimalInputFilter(15,3)});
    }

    private String mFormat = "#0.00";
    private double mMax = -1;
    private double mMin = -1;

    View.OnFocusChangeListener focusChangeListener;


    @Override
    public void setOnFocusChangeListener(View.OnFocusChangeListener listener){
        this.focusChangeListener = listener;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (false == hasFocus ){
            float f = ConvertUtils.valueOf(getStringText(),0f);

            if (mMin >= 0 && f < mMin){
                f = (float) mMin;
            }

            setText(getFormatText(f));
            setSelection(length());
        }


        if (null != focusChangeListener)
            focusChangeListener.onFocusChange(v,hasFocus);
    }



    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        float f = ConvertUtils.valueOf(s.toString(),0f);
        if(mMax > 0 && f > mMax){
            setText(getFormatText(mMax));
            setSelection(length());
        }
    }

    public String getFormatText(double d){
        if (mFormat == null || mFormat.isEmpty()){
            return d + "";
        }

        DecimalFormat df = new DecimalFormat(mFormat);
        String format = df.format(d);
        return format;
    }

    /**
     *"#0.00"
     */
    public NumberEditText setFormat(String fomrt){
        this.mFormat = fomrt;
        return this;
    }

    public NumberEditText setMax(double max){
        this.mMax = max;
        return this;
    }

    public NumberEditText setMin(double min){
        this.mMin = min;
        return this;
    }

    public String getStringText(){
        return getText().toString();
    }
}
