package com.example.wuxie.common.correct;

public interface InputRequest {
    /**
     * @param s 字符串
     * @return 正确返回true,错误返回false
     */
    boolean isCorrectFormat(String s);
}
