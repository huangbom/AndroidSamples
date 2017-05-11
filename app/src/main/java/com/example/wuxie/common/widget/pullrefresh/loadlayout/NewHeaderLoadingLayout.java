package com.example.wuxie.common.widget.pullrefresh.loadlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.wuxie.R;


/**
 * Created by bigtom on 16/6/13.
 */
public class NewHeaderLoadingLayout extends LoadingLayout
{
    /**Header的容器*/
    private RelativeLayout mHeaderContainer;

    /**进度条*/
    private ProgressBar mProgressBar;


    /**
     * 构造方法
     *
     * @param context context
     */
    public NewHeaderLoadingLayout(Context context)
    {
        super(context);
        init(context);
    }

    /**
     * 构造方法
     *
     * @param context context
     * @param attrs attrs
     */
    public NewHeaderLoadingLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context context
     */
    private void init(Context context)
    {
        mHeaderContainer = (RelativeLayout)findViewById(R.id.pull_to_refresh_header_content);
        mProgressBar = (ProgressBar)findViewById(R.id.pull_to_refresh_header_progressbar);


    }

    @Override
    public void setLastUpdatedLabel(CharSequence label)
    {
        // 如果最后更新的时间的文本是空的话，隐藏前面的标题
    }

    @Override
    public int getContentSize()
    {
        if (null != mHeaderContainer)
        {
            return mHeaderContainer.getHeight();
        }

        return (int)(getResources().getDisplayMetrics().density * 60);
    }

    @Override
    protected View createLoadingView(Context context, AttributeSet attrs)
    {
        View container = LayoutInflater.from(context).inflate(R.layout.new_pull_to_refresh_header, null);
        return container;
    }

    @Override
    protected void onStateChanged(State curState, State oldState)
    {

        mProgressBar.setVisibility(View.INVISIBLE);

        super.onStateChanged(curState, oldState);
    }

    @Override
    protected void onReset()
    {

    }

    @Override
    protected void onPullToRefresh()
    {
        if (State.RELEASE_TO_REFRESH == getPreState())
        {
        }
    }

    @Override
    protected void onReleaseToRefresh()
    {
    }

    @Override
    protected void onRefreshing()
    {
        mProgressBar.setVisibility(View.VISIBLE);
    }
}