package me.shdf.baseandroid.ui.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseFragment;
import me.shdf.baseandroid.bean.ParentAllBean;

/**
 * Created by shdf on 17/4/14.
 * wechat：zcm656025633
 * exp：
 **/

public class FamilyEducationFragmnent extends BaseFragment {
    @BindView(R.id.parentAll_listView)
    ListView mListView;
    private CommonAdapter mAdapter;
    private List<ParentAllBean> beans;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_family_education;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(view);
    }

    @Override
    public void initListener(View view) {

    }

    @Override
    public void initData() {
        mPage = getArguments().getInt(ARG_PAGE);
        beans = new ArrayList<>();
        mAdapter = new CommonAdapter<ParentAllBean>(getActivity(), R.layout.parentall_item, beans) {
            @Override
            public void convert(ViewHolder holder, ParentAllBean bean) {
                holder.setText(R.id.tv_content, bean.getContent());
            }
        };
    }

    public static FamilyEducationFragmnent newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FamilyEducationFragmnent pageFragment = new FamilyEducationFragmnent();
        pageFragment.setArguments(args);
        return pageFragment;
    }
}
