package me.shdf.baseandroid.ui.main.activity;

import android.os.Bundle;
import android.widget.TextView;

import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseActivity;
import me.shdf.baseandroid.base.basebean.BaseResponse;
import me.shdf.baseandroid.bean.UserBean;
import me.shdf.baseandroid.http.BaseObserver;
import me.shdf.baseandroid.http.RetrofitUtil;
import me.shdf.baseandroid.http.RxSchedulers;

public class Main2Activity extends BaseActivity {
    TextView name;
    @Override
    protected void beforeView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }
    @Override
    protected void obtainIntent() {
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        name = findViewById(R.id.tv_name);
        RetrofitUtil.getApiService().getUserMessage()
                .compose(RxSchedulers.<BaseResponse<UserBean>>compose())
                .compose(this.<BaseResponse<UserBean>>bindToLifecycle())
                .subscribe(new BaseObserver<UserBean>() {
                    @Override
                    protected void onHandleSuccess(UserBean userBean) {
                        String title = userBean.getName();
                        name.setText(title);
                    }
                    @Override
                    protected void onHandleError(String msg) {

                    }
                });

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
    }
}
