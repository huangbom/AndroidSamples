package com.example.wuxie.common.widget.pullrefresh;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by huangyaoshi on 2017/5/5.
 */

public class PullToRefreshRecycleView extends PullToRefreshBase<RecyclerView> {
    public PullToRefreshRecycleView(Context context) {
        this(context, null);
    }

    public PullToRefreshRecycleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullToRefreshRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setPullLoadEnabled(false);
    }

    private RecyclerView mRecycleView;

    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView cleView = new RecyclerView(context);
        mRecycleView = cleView;
//        if (mRecycleView.getParent() != null){
//            ViewGroup parent = (ViewGroup) mRecycleView.getParent();
//            parent.removeView(mRecycleView);
//        }
//        gridView.addOnScrollListener(this);

        return cleView;
    }

    public void setHasMoreData(boolean hasMoreData)
    {
        getFooterLoadingLayout().setState(hasMoreData ? ILoadingLayout.State.RESET : ILoadingLayout.State.NO_MORE_DATA);
//        setPullLoadEnabled(hasMoreData);
//        if (null != mFooterLayout)
//        {
//            if (!hasMoreData)
//            {
//                mFooterLayout.setState(State.NO_MORE_DATA);
//            }
//        }
    }

    @Override
    protected boolean isReadyForPullUp() {
        return isLastItemVisible();
    }

    @Override
    protected boolean isReadyForPullDown() {
        return isFirstItemVisible();
    }


    /**
     * 判断第一个child是否完全显示出来
     *
     * @return true完全显示出来，否则false
     */
    private boolean isFirstItemVisible() {
        RecyclerView.Adapter adapter = mRecycleView.getAdapter();

        if (null == adapter || adapter.getItemCount() == 0) {
            return true;
        }

        int mostTop = (mRecycleView.getChildCount() > 0) ? mRecycleView.getChildAt(0).getTop() : 0;

        Log.d(TAG, "isFirstItemVisible: top -- " + mostTop);

        if (mostTop >= 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断最后一个child是否完全显示出来
     *
     * @return true完全显示出来，否则false
     */
    private boolean isLastItemVisible() {
        RecyclerView.LayoutManager layoutManager = mRecycleView.getLayoutManager();

        if (null == layoutManager || layoutManager.getChildCount() <= 0) {
            return false;
        }

        int lastVisibleItemPosition;
        if (layoutManager instanceof GridLayoutManager) {
            lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
            ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
            lastVisibleItemPosition = findMax(into);
        } else {
            lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }

        Log.d(TAG, "isLastItemVisible: layoutManager.getItemCount() -- " + layoutManager.getItemCount());
        Log.d(TAG, "isLastItemVisible: layoutManager.getChildCount()) -- " + layoutManager.getChildCount());

        if (lastVisibleItemPosition >= layoutManager.getItemCount() - 1 && layoutManager.getItemCount() > layoutManager.getChildCount()) {
            return true;
        }

        return false;
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

}
