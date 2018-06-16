package me.shdf.baseandroid.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import me.shdf.baseandroid.ui.main.fragment.FamilyEducationFragmnent;
import me.shdf.baseandroid.ui.main.fragment.MiddleToHighFragment;
import me.shdf.baseandroid.ui.main.fragment.ParentAllFragment;
import me.shdf.baseandroid.ui.main.fragment.QualityEducationFragment;
import me.shdf.baseandroid.ui.main.fragment.SchoolTrendFragment;

/**
 * Created by shdf on 17/4/13.
 * wechat：zcm656025633
 * exp：
 **/

public class ParentAdapter extends FragmentPagerAdapter {
    private static final int  PAGE_COUNT = 5;
    private Context mContext;

    public ParentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case 0:
                fragment = ParentAllFragment.newInstance(0);
                break;
            case 1:
                fragment = MiddleToHighFragment.newInstance(1);
                break;
            case 2:
                fragment = FamilyEducationFragmnent.newInstance(2);
                break;
            case 3:
                fragment = QualityEducationFragment.newInstance(3);
                break;
            case 4:
                fragment = SchoolTrendFragment.newInstance(4);
                break;
            default:
                fragment = ParentAllFragment.newInstance(0);
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "全部";
                 break;
            case 1:
                title = "初升高";
                break;
            case 2:
                title = "家庭教育";
                break;
            case 3:
                title = "素质教育";
                break;
            case 4:
                title = "校园动态";
                break;
        }
        return title;
    }
}
