package me.shdf.baseandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseActivity;
import me.shdf.baseandroid.bean.TestBean;
import me.shdf.baseandroid.http.BaseObserver2;
import me.shdf.baseandroid.http.RetrofitUtil;
import me.shdf.baseandroid.http.RxSchedulers;

/**
 * Created by shdf on 2018/6/29.
 * wechat：zcm656025633
 * exp：
 **/
public class UpdateSubActivity extends BaseActivity {
    Button mBtnUpdateUser;
    @BindView(R.id.et_add_sub)
    EditText mEtAddSub;
    @BindView(R.id.btn_add_sub)
    Button mBtnAddSub;
    private Intent mIntent;
    private String uId;
    private String uName;
    private String sId;
    private String sName;
    private String bId;
    private String bName;

    @Override
    protected void beforeView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.acitivity_update_sub;
    }

    @Override
    protected void obtainIntent() {
        mIntent = getIntent();
        uId = mIntent.getStringExtra("uid");
        sId = mIntent.getStringExtra("sid");
        sName = mIntent.getStringExtra("sname");
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        mEtAddSub.setText(sName);
        if(!TextUtils.isEmpty(sId)){
            mBtnAddSub.setText("修改");
        }
    }

    @Override
    protected void initListener() {
        //todo 增加用户
        mBtnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sName = mEtAddSub.getText().toString();
                if (TextUtils.isEmpty(sName)) {
                    Toast.makeText(UpdateSubActivity.this, "用户名密码不为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(sId)){
                    //todo 执行增加操作
                    addSub();
                }
                else{
                    updateSub();
                }
            }
        });
    }

    public void addSub(){
        RetrofitUtil.getApiService().addSub(sName, uId)
                .compose(RxSchedulers.<TestBean>compose())
                .compose(UpdateSubActivity.this.<TestBean>bindToLifecycle())
                .subscribe(new BaseObserver2<TestBean>() {
                    @Override
                    protected void onHandleSuccess(TestBean userBean) {
                        //String title = userBean.getName();
                        if (userBean != null) {
                            if (userBean.isMessage()) {
                                Toast.makeText(UpdateSubActivity.this, "添加课程成功",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UpdateSubActivity.this, "添加课程失败",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        Log.d("TAG", msg);
                    }
                });
    }

    public void updateSub(){
        RetrofitUtil.getApiService()
                .updateSub(sName, sId)
                .compose(RxSchedulers.<TestBean>compose())
                .compose(UpdateSubActivity.this.<TestBean>bindToLifecycle())
                .subscribe(new BaseObserver2<TestBean>() {
                    @Override
                    protected void onHandleSuccess(TestBean testBean) {
                        if (testBean.isMessage()) {
                            Toast.makeText(UpdateSubActivity.this, "更新成功",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(UpdateSubActivity.this, "更新失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    protected void onHandleError(String msg) {

                    }
                });
    }


    @Override
    protected void initData() {

    }
}
