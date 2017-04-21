package com.example.wuxie.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

/**
 * Created by huangyaoshi on 2017/4/21.
 */

public abstract class BaseExpanAdapter<G extends IExpandGroup,C extends IExpandChild> extends BaseExpandableListAdapter {

    private static final String TAG = "BaseExpanAdapter";

    protected Context mContext;
    protected List<G> mList;

    public BaseExpanAdapter(Context c,List<G> list) {
        mContext = c;
        mList = list;
    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return getGroup(groupPosition).getChildrenList().size();
    }

    @Override
    public G getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        return (C) getGroup(groupPosition).getChildrenList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return getGroup(groupPosition).getGroupId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        long childId = getChild(groupPosition, childPosition).getChildId();
//        Log.d(TAG, "getChildId: "+childId);

        return childId;
    }


    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    public static <T extends View> T getAdapterView(View convertView, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            convertView.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
