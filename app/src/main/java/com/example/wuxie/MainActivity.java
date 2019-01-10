package com.example.wuxie;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.wuxie.base.NewBaseActivity;
import com.example.wuxie.bieguide.BieGuideActivity;
import com.example.wuxie.common.utils.ScreenUtils;
import com.example.wuxie.mycart.MyCartActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends NewBaseActivity implements AdapterView.OnItemClickListener {

    List<Bean> mList;
    MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initList();

        GridView recyclerView = $(R.id.gridview);

        mAdapter = new MainAdapter();
        recyclerView.setAdapter(mAdapter);

        recyclerView.setOnItemClickListener(this);

    }

    void initList(){
        mList = new ArrayList<>();
        mList.add(new Bean("购物车", MyCartActivity.class));
        mList.add(new Bean("bie Guide", BieGuideActivity.class));
        mList.add(new Bean("textview", TextViewActivity.class));
        mList.add(new Bean("SampleRecleView", SampleRecleViewActivity.class));
        mList.add(new Bean("HeadLinearActivity", HeadLinearActivity.class));
        mList.add(new Bean("仿微信支付框", PayPasswordEditActivity.class));
        mList.add(new Bean("bind service", BindServiceActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mActivity, mAdapter.getItem(position).clz);
        mActivity.startActivity(intent);
    }

    private class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Bean getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Button tv = (Button)convertView;
            if (convertView == null){
                tv = new Button(mActivity);
                tv.setGravity(Gravity.CENTER);
                int i = ScreenUtils.dp2Px(mActivity, 10);
                tv.setPadding(i,i,i,i);
//                tv.setBackgroundResource(R.drawable.shape_oval_black);
                tv.setLayoutParams(new AbsListView.LayoutParams(-2, ScreenUtils.dp2Px(mActivity,40)));
            }

            tv.setText(getItem(position).name);

            return tv;
        }
    };





    private static class Bean{
        public String name;
        public Class clz;

        public Bean(String name, Class clz) {
            this.name = name;
            this.clz = clz;
        }
    }
}
