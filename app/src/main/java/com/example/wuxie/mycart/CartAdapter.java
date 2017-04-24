package com.example.wuxie.mycart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.wuxie.R;
import com.example.wuxie.base.BaseExpanAdapter;
import com.example.wuxie.databinding.ItemCartChildrenBinding;

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
    // 全选按钮的状态要改变

    // 保留商品数量
    // item点击事件／普通状态时点击去商品页，编辑状态时选中列表

    private static final String TAG = "CartAdapter";

    private List<Long> mEditIds,mSelectIds,mAllList;

    private boolean isEdit = false;

    public CartAdapter(Context c, List<MyCartActivity.ShopModel> list) {
        super(c, list);

        mEditIds = new ArrayList<>();
        mSelectIds = new ArrayList<>();
        mAllList = new ArrayList<>();
        for (MyCartActivity.ShopModel model: super.getList()){
            mAllList.addAll(model.getChildrenIds());
        }
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
        ItemCartChildrenBinding binding ;
        if (convertView == null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.item_cart_children,null,false);
        }else{
            binding = DataBindingUtil.getBinding(convertView);
        }

        List<Long> list = isEdit ? mEditIds : mSelectIds;

//        long childId = getChildId(groupPosition, childPosition);
//        Log.d(TAG, "getChildView: childId : " + childId);
        MyCartActivity.ShopModel.ProduModel child = getChild(groupPosition, childPosition);
        child.setCheck(list.contains(child.getChildId()));
        binding.setPresenter(new Persenter());
        binding.setGoods(child);

        convertView = binding.getRoot();
        return convertView;
    }

    public void updateAll(boolean isSelect){

        List list = isEdit ? mEditIds : mSelectIds ;

        // 默认只有编辑可全选

        if (isSelect){
            list.clear();
            list.addAll(mAllList);
        } else{
            // cancel select all
            list.clear();
        }

        Log.d(TAG, "updateAll: sele : "+mSelectIds);
        Log.d(TAG, "updateAll: edit : "+ mEditIds);
        Log.d(TAG, "updateAll: all  : "+ mAllList);

        notifyDataSetInvalidated();
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


    public class Persenter{
        public void onCheckBoxClick(View button, MyCartActivity.ShopModel.ProduModel model){
            CompoundButton btn = (CompoundButton)button;
            Log.d(TAG, "onCheckedChanged: "+ btn.isChecked());

//                // list 也是引用类型
            List<Long> list = isEdit? mEditIds : mSelectIds;
            long childId = model.getChildId();
            Log.d(TAG, "onCheckBoxClick: "+childId);

            if (btn.isChecked()){
                list.add(childId);
            } else {
                list.remove(childId);
            }
        }
    }
}
