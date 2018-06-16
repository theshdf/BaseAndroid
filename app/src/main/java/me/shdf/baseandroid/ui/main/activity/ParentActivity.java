package me.shdf.baseandroid.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseActivity;
import me.shdf.baseandroid.ui.adapter.ParentAdapter;


/**
 * Created by shdf on 17/4/13.
 * wechat：zcm656025633
 * exp：
 **/

public class ParentActivity extends BaseActivity {

    private ParentAdapter mParentAdapter;
    @BindView(R.id.parent_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.parent_tablayout)
    TabLayout mTabLayout;

    @Override
    protected void beforeView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_parent;
    }

    @Override
    protected void obtainIntent() {

    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        ButterKnife.bind(this);

    }

    @Override
    protected void initListener() {
    }

    @Override
    protected void initData() {
        mParentAdapter = new ParentAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(mParentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }
}
