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
public class UpdateBookActivity extends BaseActivity {
    @BindView(R.id.et_bookname)
    EditText mEtBookname;
    @BindView(R.id.btn_bookname)
    Button mBtnBookname;
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
        return R.layout.acitivity_update_book;
    }

    @Override
    protected void obtainIntent() {
        mIntent = getIntent();
        sId = mIntent.getStringExtra("sid");
        bId = mIntent.getStringExtra("bid");
        bName = mIntent.getStringExtra("bname");
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        mEtBookname.setText(bName);
        if(!TextUtils.isEmpty(bId)){
            mBtnBookname.setText("修改");
        }
    }

    @Override
    protected void initListener() {
        //todo 增加用户
        mBtnBookname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bName = mEtBookname.getText().toString();
                if (TextUtils.isEmpty(bName)) {
                    Toast.makeText(UpdateBookActivity.this, "书名不为空",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(bId)){
                    //todo 执行增加操作
                    addBook();
                }
                else{
                    updateBook();
                }
            }
        });
    }

    public void addBook(){
        RetrofitUtil.getApiService().addBook(bName, sId)
                .compose(RxSchedulers.<TestBean>compose())
                .compose(UpdateBookActivity.this.<TestBean>bindToLifecycle())
                .subscribe(new BaseObserver2<TestBean>() {
                    @Override
                    protected void onHandleSuccess(TestBean userBean) {
                        //String title = userBean.getName();
                        if (userBean != null) {
                            if (userBean.isMessage()) {
                                Toast.makeText(UpdateBookActivity.this, "添加课程成功",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UpdateBookActivity.this, "添加课程失败",
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

    public void updateBook(){
        RetrofitUtil.getApiService()
                .updateBook(bName, sId)
                .compose(RxSchedulers.<TestBean>compose())
                .compose(UpdateBookActivity.this.<TestBean>bindToLifecycle())
                .subscribe(new BaseObserver2<TestBean>() {
                    @Override
                    protected void onHandleSuccess(TestBean testBean) {
                        if (testBean.isMessage()) {
                            Toast.makeText(UpdateBookActivity.this, "更新成功",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(UpdateBookActivity.this, "更新失败",
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
