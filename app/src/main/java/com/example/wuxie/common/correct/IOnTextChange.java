package com.example.wuxie.common.correct;

import android.text.TextWatcher;

/**
 * Created by huangyaoshi on 2017/5/9.
 */
public interface IOnTextChange {

    void addTextChangedListener(TextWatcher watcher);

    /** 格式化正确,返回true,否则false*/
    boolean isCorrectFormat();
}
