package com.example.wuxie.common.correct.impl;

import android.text.TextUtils;

import com.example.wuxie.common.correct.InputRequest;


/**
 * on 16/11/16.
 * @author 一灯大师
 */
public class InputDoubleImpl implements InputRequest {
    @Override
    public boolean isCorrectFormat(String s) {

        if (TextUtils.isEmpty(s.trim()))
            return false;
        try {
            return Double.valueOf(s) > 0;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
