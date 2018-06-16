package me.shdf.baseandroid.wigdet;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import me.shdf.baseandroid.R;


/**
 * Created by zcm on 2016/4/6.
 * qq:656025633
 */
public class RefreshLvLayout extends RefreshLayout<ListView> {

    /**
     * ListView的加载中footer
     */
    protected View mListViewFooter;

    public RefreshLvLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshLvLayout(Context context) {
        this(context, null);
        mListViewFooter = LayoutInflater.from(context).inflate(R.layout.common_listview_foot,
                this, false);
    }

    /*
     * 设置ListView的adapter之前先添加footer,
     * 避免footer的添加在设置adapter之后出现的转换到HeaderViewListAdapter的异常
     * @see
     * com.uit.pullrefresh.swipe.RefreshLayout#setAdapter(android.widget.ListAdapter
     * )
     */
    @Override
    public void setAdapter(ListAdapter adapter) {
        if (mAbsListView.getFooterViewsCount() == 0) {
            mAbsListView.addFooterView(mListViewFooter);
        }
        super.setAdapter(adapter);
        // 添加只是为了在ListView的setAdapter方法时将Adapter包装成HeaderViewListAdapter。因此并不需要footer，因此添加后再移除,
        mAbsListView.removeFooterView(mListViewFooter);
    }

    @Override
    public void setLoading(boolean loading) {
        super.setLoading(loading);
        if (isLoading && mAbsListView.getFooterViewsCount() == 0) {
            mAbsListView.addFooterView(mListViewFooter);
        } else {
            if (mAbsListView.getAdapter() instanceof HeaderViewListAdapter) {
                mAbsListView.removeFooterView(mListViewFooter);
            } else {
                mListViewFooter.setVisibility(View.GONE);
            }
            mYDown = 0;
            mLastY = 0;
        }
    }
}