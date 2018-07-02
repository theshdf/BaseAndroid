package me.shdf.baseandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
public class UpdateUserActivity extends BaseActivity {
    @BindView(R.id.et_add_username)
    EditText mEtAddUsername;
    @BindView(R.id.et_add_password)
    EditText mEtAddPassword;
    @BindView(R.id.btn_adduser)
    Button mBtnAdduser;
    @BindView(R.id.et_update_username)
    TextView mEtUpdateUsername;
    @BindView(R.id.et_update_password)
    EditText mEtUpdatePassword;
    @BindView(R.id.btn_update_user)
    Button mBtnUpdateUser;
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
        return R.layout.acitivity_update_user;
    }

    @Override
    protected void obtainIntent() {
        mIntent = getIntent();
        uId = mIntent.getStringExtra("uid");
        uName = mIntent.getStringExtra("uname");
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        mEtUpdateUsername.setText("用户名："+uName);
    }

    @Override
    protected void initListener() {
        //todo 增加用户
        mBtnAdduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = mEtAddUsername.getText().toString();
                String upass = mEtAddPassword.getText().toString();
                if(TextUtils.isEmpty(uname)||TextUtils.isEmpty(upass)){
                    Toast.makeText(UpdateUserActivity.this,"用户名密码不为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                RetrofitUtil.getApiService().addUser(uname,upass)
                        .compose(RxSchedulers.<TestBean>compose())
                        .compose(UpdateUserActivity.this.<TestBean>bindToLifecycle())
                        .subscribe(new BaseObserver2<TestBean>() {
                            @Override
                            protected void onHandleSuccess(TestBean userBean) {
                                //String title = userBean.getName();
                                if(userBean != null){
                                    if(userBean.isMessage()){
                                        Toast.makeText(UpdateUserActivity.this,"添加成功",
                                                Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(UpdateUserActivity.this,"添加失败",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            @Override
                            protected void onHandleError(String msg) {
                                Log.d("TAG",msg);
                            }
                        });
            }
        });

        //todo 修改用户
        mBtnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String upass = mEtUpdatePassword.getText().toString();
                if(!TextUtils.isEmpty(upass)){
                    RetrofitUtil.getApiService()
                            .updateUser(uId,upass)
                            .compose(RxSchedulers.<TestBean>compose())
                            .compose(UpdateUserActivity.this.<TestBean>bindToLifecycle())
                            .subscribe(new BaseObserver2<TestBean>() {
                                @Override
                                protected void onHandleSuccess(TestBean testBean) {
                                    if(testBean.isMessage()){
                                        Toast.makeText(UpdateUserActivity.this,"更新成功",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    else{
                                        Toast.makeText(UpdateUserActivity.this,"更新失败",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                protected void onHandleError(String msg) {

                                }
                            });
                }
                else{
                    Toast.makeText(UpdateUserActivity.this,"账户密码不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}
