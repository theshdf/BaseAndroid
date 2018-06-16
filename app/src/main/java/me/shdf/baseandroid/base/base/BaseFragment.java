package me.shdf.baseandroid.base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;


public abstract class BaseFragment extends RxFragment {
    //用于保存状态
    private View view;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(getContentLayoutId(), container, false);
            initView(view);
            initListener(view);
            initData();
        }
        ViewGroup parent = (ViewGroup)view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }

        return view;
    }

    //获取布局的id
    public abstract int getContentLayoutId();
    public abstract void initView(View view);
    public abstract void initListener(View view);
    public abstract  void initData();
}