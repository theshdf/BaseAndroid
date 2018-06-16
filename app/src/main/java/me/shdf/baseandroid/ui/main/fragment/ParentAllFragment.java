package me.shdf.baseandroid.ui.main.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
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
 * Created by shdf on 17/4/13.
 * wechat：zcm656025633
 * exp：家长荟
 **/

public class ParentAllFragment extends BaseFragment {

    @BindView(R.id.parentAll_listView)
    ListView mListView;
    private CommonAdapter mAdapter;
    private List<ParentAllBean> beans;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_parentall;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this,view);

    }

    @Override
    public void initListener(View view) {

    }

    @Override
    public void initData() {
        mPage = getArguments().getInt(ARG_PAGE);
        beans = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            ParentAllBean bean = new ParentAllBean();
            bean.setContent("我是"+i);
            bean.setImage_url("http://img0.imgtn.bdimg.com/it/u=349708593,2859752577&fm=23&gp=0.jpg");
            beans.add(bean);

        }
        mAdapter = new CommonAdapter<ParentAllBean>(getActivity(),R.layout.parentall_item,beans) {
            @Override
            public void convert(ViewHolder holder, ParentAllBean bean) {
                holder.setText(R.id.tv_content,bean.getContent());
                View view = holder.getView(R.id.img_content);
                Glide.with(getActivity()).load(bean.getImage_url()).into((ImageView) view);
            }
        };
        mListView.setAdapter(mAdapter);
    }
    public static ParentAllFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ParentAllFragment pageFragment = new ParentAllFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }
}
