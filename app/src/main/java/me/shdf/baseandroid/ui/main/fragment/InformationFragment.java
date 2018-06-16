package me.shdf.baseandroid.ui.main.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseFragment;
import me.shdf.baseandroid.wigdet.CustomViewPager;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformationFragment extends BaseFragment {
    @BindView(R.id.mViewPager)
    ViewPager mMViewPager;

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    public void initView(View view) {
         ButterKnife.bind(this,view);
         CustomViewPager customViewPager = new CustomViewPager(getActivity(),mMViewPager);
    }

    @Override
    public void initListener(View view) {

    }

    @Override
    public void initData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
