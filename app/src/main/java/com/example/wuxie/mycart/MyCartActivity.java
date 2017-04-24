package com.example.wuxie.mycart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;

import com.example.wuxie.R;
import com.example.wuxie.base.IExpandChild;
import com.example.wuxie.base.IExpandGroup;
import com.example.wuxie.base.NewBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MyCartActivity extends NewBaseActivity {

    private static final String TAG = "MyCartActivity";


    public static void start(Context context) {
        Intent intent = new Intent(context, MyCartActivity.class);
        context.startActivity(intent);
    }

    ExpandableListView expandableListView;
    Button mNowBuy;
    CompoundButton mCheck;

    java.util.List<ShopModel> mListGroups = new ArrayList<>();
    CartAdapter mCartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
//        expandableListView

        setTitle("购物车");
        setMenuTitle(null,"编辑");


        mNowBuy = $(R.id.btn_now_buy);
        mCheck = $(R.id.check);
        expandableListView = $(R.id.expandableListView);
        // 先初始化一个列表
        // 单个选择框可选
        // 组可选，并响应全选事件
        // 标记模式

        initMockData();

        mCartAdapter = new CartAdapter(this, mListGroups);
        expandableListView.setAdapter(mCartAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Log.d(TAG, "onGroupClick: i :" +i +"  l : "+l);
                mCartAdapter.selectGroup(i);
                return true;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Log.d(TAG, "onChildClick: "+groupPosition + ":" + childPosition);
                return true;
            }
        });

        // expand All Group
        for (int i = 0; i < mListGroups.size(); i++) {
            expandableListView.expandGroup(i);
        }
    }

    void initMockData(){

        int k = 1;
        for (int i = 0 ; i< 5; i++){
            ShopModel model = new ShopModel(i);
            model.shopTitle = "dian pu "+i;

            for (int j = 0; j < 5; j++) {
                ShopModel.ProduModel pmode = new ShopModel.ProduModel(k++);
                Log.d(TAG, "initMockData: " + (k));
                model.pList.add(pmode);
            }

            mListGroups.add(model);
        }

//        Log.d(TAG, "initMockData: "+ mListGroups);

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    boolean _isEdit = false;


    public void updateAll(View view){
        CheckBox box = (CheckBox)view;
        mCartAdapter.updateAll(box.isChecked());
//        Log.d(TAG, "nowBuy: " + mCartAdapter.getSelectIds());;
        Log.d(TAG, "nowBuy: " + mNowBuy.getText().toString());
    }


    public void nowBuy(View view){
//        if (_isEdit){
//            Log.d(TAG, "nowBuy: 删除选择");
//            Log.d(TAG, "nowBuy: " + mCartAdapter.getEditIds());
//        } else{
//            Log.d(TAG, "nowBuy: ti jiao ding dan");
//            Log.d(TAG, "nowBuy: " + mCartAdapter.getSelectIds());;
//        }

        Log.d(TAG, "nowBuy: "+ mCartAdapter.getGoodsIds());
    }

    @Override
    public void onRight2Click(View view) {
        _isEdit = !_isEdit ;
        setMenuTitle(null,_isEdit ? "完成" : "编辑");
        mNowBuy.setText(_isEdit ? "删除选择" : "提交订单");

        mCartAdapter.setEditMode(_isEdit);

        Log.d(TAG, "onRight2Click: isEdit: " + _isEdit);
    }

    public static class ShopModel implements IExpandGroup{

        public int s_id;
        public String shopTitle;
        public List<ProduModel> pList;

        public List<Long> getChildrenIds(){
            List<Long> list = new ArrayList<>();
            for (ProduModel model : getChildrenList()) {
                list.add(model.getChildId());
            }

            return list;
        }

        public ShopModel(int i){
            this.s_id = i;
            pList = new ArrayList<>();
        }

        @Override
        public long getGroupId() {
            return s_id;
        }

        @Override
        public List<ProduModel> getChildrenList() {
            return pList;
        }


        public static class ProduModel implements IExpandChild{
            public int p_id;
            public boolean isCheck;

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String number = "1";

            public boolean isEdit() {
                return isEdit;
            }

            public void setEdit(boolean edit) {
                isEdit = edit;
            }

            public boolean isEdit;


            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public ProduModel(int id) {
                this.p_id = id;
//                this.isCheck = false;
             }

            @Override
            public long getChildId() {
                return p_id;
            }

            @Override
            public String toString() {
                return "[" + p_id + " : "+ number +"]";
//                return super.toString();
            }
        }

    }
}
