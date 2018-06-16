package me.shdf.baseandroid.ui.main.presenter;

import android.content.Context;

import me.shdf.baseandroid.ui.main.contract.MainContract;

/**
 * Created by ${shdf} on 17/4/12.
 * wechat：zcm656025633
 * exp：
 **/

public class MainPresenter extends MainContract.Presenter {
    private MainContract.View mContext;

    @Override
    public void attachView(MainContract.View context) {
        mContext = context;
    }

    @Override
    public void dettachView() {

    }

    @Override
    public void getData(Context context) {
//        RetrofitUtil.getApiService(context).getDouNews(0,10).enqueue(new Callback<DouBean>() {
//            @Override
//            public void onResponse(Call<DouBean> call, Response<DouBean> response) {
//                List<DouBean.SubjectsBean> beans = response.body().getSubjects();
//                mContext.loadData(beans);
//            }
//
//            @Override
//            public void onFailure(Call<DouBean> call, Throwable t) {
//
//            }
//        });

    }
}
