package com.example.wuxie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wuxie.base.NewBaseActivity;
import com.example.wuxie.common.widget.pullrefresh.PullToRefreshBase;
import com.example.wuxie.common.widget.pullrefresh.PullToRefreshRecycleView;

/**
 * Created by huangzhixiong on 16/12/23.
 */

public class SampleRecleViewActivity extends NewBaseActivity {

    public static final String TAG = "SampleRecleViewActivity";

    public static void start(Context context) {
        Intent starter = new Intent(context, SampleRecleViewActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = View.inflate(this,R.layout.sample_recycleview,null);
        setContentView(root);
        root.findViewById(R.id.linear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear(v);
            }
        });

        root.findViewById(R.id.grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grid(v);
            }
        });

        root.findViewById(R.id.stagger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stagger(v);
            }
        });

        viewById = (PullToRefreshRecycleView) root.findViewById(R.id.recyclerview);

//        ((CollapsingToolbarLayout) root.findViewById(R.id.collapsing_toolbar_layout)).setTitle("Screen Title");
        rv = viewById.getRefreshableView();
//        rv.addItemDecoration();
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new  RecyclerView.Adapter<ViewHolder>() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
                return new ViewHolder(View.inflate(mActivity,R.layout.sample_recleview_list_item, null));
            }

            @Override
            public void onBindViewHolder(ViewHolder viewHolder, int position) {
                viewHolder.text1.setText("Bacon" + position);
                viewHolder.text2.setText(position + "Bacon ipsum dolor amet pork belly meatball kevin spare ribs. Frankfurter swine corned beef meatloaf, strip steak.");
            }

            @Override
            public int getItemCount() {
                return size;
            }
        };

        rv.setAdapter(adapter);

        viewById.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
                Log.i(TAG,"onPullDownToRefresh");

                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        size = 15;
                        sef = true;
                        viewById.setHasMoreData(true);
                        adapter.notifyDataSetChanged();
                        refreshView.onPullDownRefreshComplete();
                    }
                },2000);

            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
                Log.i(TAG,"onPullUpToRefresh");
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshView.onPullUpRefreshComplete();
                        if (sef) {
                            size += 5;
                            adapter.notifyDataSetChanged();
                            sef = false;
                        }else {
                            viewById.setHasMoreData(false);
                        }

                    }
                },2000);
            }
        });

        viewById.setPullLoadEnabled(true);
        viewById.setHasMoreData(true);


    }

    View root;

    PullToRefreshRecycleView viewById;
    RecyclerView rv;
    RecyclerView.Adapter adapter;
    int size = 15;
    boolean sef = true;


    public void stagger(View view){
        rv.setLayoutManager(new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL));
    }

    public void grid(View view){
        rv.setLayoutManager(new GridLayoutManager(mActivity,3));

    }

    public void linear(View view){
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        TextView text2;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(android.R.id.text1);
            text2 = (TextView) itemView.findViewById(android.R.id.text2);
        }
    }
}
