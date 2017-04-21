package com.example.wuxie.mycart;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.wuxie.R;
import com.example.wuxie.base.BaseExpanAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangyaoshi on 2017/4/20.
 */
public class CartAdapter extends BaseExpanAdapter<MyCartActivity.ShopModel,MyCartActivity.ShopModel.ProduModel> {


    // 两个列表，一个选择界面的，一个编辑状态的
    // 获取所有选择列表
    // 获取所有删除列表
    // 切换两种状态，看状态是否有保留
    // 做编辑操作　

    // item点击事件／普通状态时点击去商品页，编辑状态时选中列表

    private static final String TAG = "CartAdapter";

    List<Long> mEditIds,mSelectIds,mAllList;

    boolean isEdit = false;

    public CartAdapter(Context c, List<MyCartActivity.ShopModel> list) {
        super(c, list);

        mEditIds = new ArrayList<>();
        mSelectIds = new ArrayList<>();
        mAllList = new ArrayList<>();

//        for (MyCartActivity.ShopModel mode : list) {
//            mAllList.addAll(mode.getChildrenList());
//        }

    }

    public void setEditMode(boolean isEdit){
        this.isEdit = isEdit;
        notifyDataSetInvalidated();
    }


    @Override
    public MyCartActivity.ShopModel.ProduModel getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition).pList.get(childPosition);
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.item_cart_group,null);
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        ItemCartChildrenBinding binding ;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate( R.layout.item_cart_children, null, false);
        }

        List<Long> list = isEdit ? mEditIds : mSelectIds;

        CheckBox checkBox = getAdapterView(convertView, R.id.check);
        long childId = getChildId(groupPosition, childPosition);
//        Log.d(TAG, String.format("getChildView:%d groupPosition:%d childPosition:%d",groupPosition,childPosition,childId));
//        Log.d(TAG, "getChildView: list "+list);
        checkBox.setChecked(list.contains(childId));

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompoundButton btn = (CompoundButton)v;
                Log.d(TAG, "onCheckedChanged: "+ btn.isChecked());

//                // list 也是引用类型
                List<Long> list = isEdit? mEditIds : mSelectIds;

                if (btn.isChecked()){
                    list.add(getChildId(groupPosition,childPosition));
                } else {
                    list.remove(getChildId(groupPosition,childPosition));
                }

                getChild(groupPosition,childPosition).setCheck(btn.isChecked());

            }
        });
        getAdapterView(convertView,R.id.tv_express).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit){
                    Log.d(TAG, "onClick: tvExpress");
                }
            }
        });

//        binding.tvExpress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isEdit){
//                    Log.d(TAG, "onClick: tvExpress");
//                }
//            }
//        });
//
//        binding.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Log.d(TAG, "onCheckedChanged: "+isChecked);
//
//                // list 也是引用类型
//                List<Long> list = isEdit? mEditIds : mSelectIds;
//
//                if (isChecked){
//                    list.add(getGroupId(groupPosition));
//                } else {
//                    list.remove(getGroupId(groupPosition));
////                    mSelectIds.remove(group)
//                }
//
//                getChild(groupPosition,childPosition).setCheck(isChecked);
//            }
//        });

//        binding.setIsEdit(isEdit);
//        binding.executePendingBindings();

        return convertView;
    }

    public void updateAll(boolean isSelect){
        if (isSelect){

        }
    }

    public List<Long> getSelectIds(){
        return mSelectIds;
    }

    public List<Long> getEditIds(){
        return mEditIds;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
