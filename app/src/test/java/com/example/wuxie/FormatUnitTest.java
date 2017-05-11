package com.example.wuxie;

import org.junit.Test;

import java.text.DecimalFormat;

/**
 * Created by huangyaoshi on 2017/5/9.
 */

public class FormatUnitTest {

    @Test
    public void test_formatnumber(){
        DecimalFormat df = new DecimalFormat("#0.000");
        String format = df.format(324.10);
        System.out.println(format);
        System.out.println(df.format(12.0));
        System.out.println(df.format(12));
        float f = 0;
        double d = 0;
        System.out.println(f);
        System.out.println(d);
    }
}
