package com.example.wuxie.common.utils;

/**
 * Created by huangyaoshi on 2017/5/4.
 */

public class ConvertUtils {
    public static float valueOf(String f,float callfail){
        try{
            return Float.valueOf(f);
        }catch (Exception e){
            return callfail;
        }
    }

    public static int valueOf(String i,int callfail){
        try{
            return Integer.valueOf(i);
        }catch (Exception e){
            return callfail;
        }
    }
}
