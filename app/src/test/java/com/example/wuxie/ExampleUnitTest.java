package com.example.wuxie;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);

//        List<Integer> list1 = new ArrayList<>();
//        List<Integer> list2 = list1;
        List<Integer> test = list1;
//        test.add(1);
        list1.add(3);
        list1.add(4);
        list1.add(8);
        list1.add(5);
        list1.add(0);
//        list2.add(4);

        test.remove(Integer.valueOf(3));
        test.remove(Integer.valueOf(2));

        System.out.println(list1);
        System.out.println(list2);
        System.out.println(test);
    }
}