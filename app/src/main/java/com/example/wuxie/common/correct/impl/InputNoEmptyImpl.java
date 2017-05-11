package com.example.wuxie.common.correct.impl;

import android.text.TextUtils;

import com.example.wuxie.common.correct.InputRequest;


/**
 * on 16/11/16.return true if not null
 * @author 一灯大师
 */
public class InputNoEmptyImpl implements InputRequest {
    @Override
    public boolean isCorrectFormat(String s) {
        return ! TextUtils.isEmpty(s);
    }
}
