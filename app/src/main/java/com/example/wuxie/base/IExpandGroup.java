package com.example.wuxie.base;

import com.example.wuxie.mycart.MyCartActivity;

import java.util.List;

/**
 * Created by huangyaoshi on 2017/4/21.
 */

public interface IExpandGroup {
    long getGroupId();

    List<MyCartActivity.ShopModel.ProduModel> getChildrenList();
}
