package com.example.wuxie.mycart;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

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

//    private List<Long> mEditIds,mSelectIds,mAllList;
    private List<MyCartActivity.ShopModel.ProduModel> mEditGoods,mSelectGoods,mAllList;

    private boolean mIsEdit = false;

    public CartAdapter(Context c, List<MyCartActivity.ShopModel> list) {
        super(c, list);

        mEditGoods = new ArrayList<>();
        mSelectGoods = new ArrayList<>();
        mAllList = new ArrayList<>();
        for (MyCartActivity.ShopModel model: super.getList()){
            mAllList.addAll(model.getChildrenList());
        }

        // Invalid of Invalid
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
        convertView = binding.getRoot();

//        if (convertView == null ){
//            convertView = View.inflate(mContext,R.layout.item_cart_children,null);
//        }

        binding.eAet.setVisibility(mIsEdit ?  View.VISIBLE : View.GONE);
        binding.eBtnInvalid.setVisibility(mIsEdit ?  View.VISIBLE : View.GONE);
        binding.eIvArrow.setVisibility(mIsEdit ?  View.VISIBLE : View.GONE);

        binding.sTvNumber.setVisibility(mIsEdit ?  View.GONE : View.VISIBLE);

        binding.textView2.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);


        List<MyCartActivity.ShopModel.ProduModel> list = mIsEdit ? mEditGoods : mSelectGoods;


        MyCartActivity.ShopModel.ProduModel child = getChild(groupPosition, childPosition);
        child.setCheck(list.contains(child));
        binding.setPresenter(new Persenter());
        binding.setGoods(child);

        return convertView;
    }


    public void setEditMode(boolean isEdit){
        this.mIsEdit = isEdit;
        notifyDataSetInvalidated();
    }


    public void updateAll(boolean isSelect){

        List list = mIsEdit ? mEditGoods : mSelectGoods ;

        // 默认只有编辑可全选

        if (isSelect){
            list.clear();
            list.addAll(mAllList);
        } else{
            // cancel select all
            list.clear();
        }

        Log.d(TAG, "updateAll: sele : "+ mSelectGoods);
        Log.d(TAG, "updateAll: edit : "+ mEditGoods);
        Log.d(TAG, "updateAll: all  : "+ mAllList);

        notifyDataSetInvalidated();
    }

    public void selectGroup(int groupPosition){
        List<MyCartActivity.ShopModel.ProduModel> childrenList = getGroup(groupPosition).getChildrenList();

        if (getGoods().containsAll(childrenList)){
            getGoods().removeAll(childrenList);
        } else {
            getGoods().removeAll(childrenList);
            getGoods().addAll(childrenList);
        }

//        for (MyCartActivity.ShopModel.ProduModel model :
//            getGroup(groupPosition).getChildrenList()) {
//            if (! goods.contains(model)){
//                goods.add(model);
//            }
//        }

        notifyDataSetInvalidated();
    }


    public List<Long> getGoodsIds(){
        List<Long> listIds = new ArrayList<>();


        for (MyCartActivity.ShopModel.ProduModel model : getGoods()){
            listIds.add(model.getChildId());
        }

        return listIds;
    }

    public List<MyCartActivity.ShopModel.ProduModel> getGoods(){
        Log.d(TAG, "getGoods: isEdit " + mIsEdit);
        return mIsEdit ? mEditGoods : mSelectGoods;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public class Persenter{
        public void onCheckBoxClick(CompoundButton btn, MyCartActivity.ShopModel.ProduModel model){
//            CompoundButton btn = (CompoundButton)button;
            Log.d(TAG, "onCheckedChanged: "+ btn.isChecked());

            // list 也是引用类型
            List<MyCartActivity.ShopModel.ProduModel> list = getGoods();

            long childId = model.getChildId();
            Log.d(TAG, "onCheckBoxClick: "+childId);

            if (btn.isChecked()){
                list.add(model);
            } else {
                list.remove(model);
            }
        }

        public void onExpressClick(View view){

            Log.d(TAG, "onExpressClick: " + mIsEdit);
        }
        
        public void addNumber(EditText et,MyCartActivity.ShopModel.ProduModel v){
            int s = Integer.valueOf(et.getText().toString());   // 这里要做一下一场处理
            if (s >= Integer.MAX_VALUE){
                Log.d(TAG, "addNumber: max");
                return;
            }

            s++;

            et.setText(String.valueOf(s));

            Log.d(TAG, "addNumber: "+ v.getNumber() );
        }


        public void reduceNumber(EditText et,MyCartActivity.ShopModel.ProduModel v){
            int s = Integer.valueOf(et.getText().toString());   // 这里要做一下一场处理
            if (s <= 0){
                Log.d(TAG, "addNumber: min");
                return;
            }

            s--;

            et.setText(String.valueOf(s));

            Log.d(TAG, "addNumber: "+ v.getNumber() );
        }
    }
}
