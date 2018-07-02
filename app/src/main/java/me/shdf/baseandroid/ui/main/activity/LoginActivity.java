package me.shdf.baseandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;
import butterknife.BindView;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseActivity;
import me.shdf.baseandroid.bean.LoginBean;
import me.shdf.baseandroid.http.BaseObserver2;
import me.shdf.baseandroid.http.RetrofitUtil;
import me.shdf.baseandroid.http.RxSchedulers;

/**
 * Created by shdf on 2018/6/29.
 * wechat：zcm656025633
 * exp：
 **/
public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_progress)
    ProgressBar mLoginProgress;
    @BindView(R.id.username)
    AutoCompleteTextView mUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.sign_in_button)
    Button mSignInButton;
    @BindView(R.id.login_form)
    ScrollView mLoginForm;

    @Override
    protected void beforeView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void obtainIntent() {

    }

    @Override
    protected void initView(Bundle saveInstanceState) {

    }

    @Override
    protected void initListener() {
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = mUsername.getText().toString();
                String upass = mPassword.getText().toString();
                if(TextUtils.isEmpty(uname)||TextUtils.isEmpty(upass)){
                    Toast.makeText(LoginActivity.this,"用户名密码不为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                RetrofitUtil.getApiService().loginUser(uname,upass)
                        .compose(RxSchedulers.<LoginBean>compose())
                        .compose(LoginActivity.this.<LoginBean>bindToLifecycle())
                        .subscribe(new BaseObserver2<LoginBean>() {
                            @Override
                            protected void onHandleSuccess(LoginBean userBean) {
                                //String title = userBean.getName();
                                if(userBean != null){
                                    if("登陆成功".equals(userBean.getMessage())) {
                                        Toast.makeText(LoginActivity.this, userBean.getMessage(),
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(LoginActivity.this, userBean.getMessage(),
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

    }

    @Override
    protected void initData() {

    }
}
