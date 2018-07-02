package me.shdf.baseandroid.ui.main.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseActivity;
import me.shdf.baseandroid.base.rvbaseAdapter.CommonAdapter;
import me.shdf.baseandroid.base.rvbaseAdapter.MultiItemTypeAdapter;
import me.shdf.baseandroid.base.rvbaseAdapter.base.ViewHolder;
import me.shdf.baseandroid.bean.TyjUserBean;
import me.shdf.baseandroid.http.BaseObserver2;
import me.shdf.baseandroid.http.RetrofitUtil;
import me.shdf.baseandroid.http.RxSchedulers;
import me.shdf.baseandroid.util.T;

/**
 * Created by shdf on 2018/6/29.
 * wechat：zcm656025633
 * exp：
 **/
public class MainActivity extends BaseActivity{
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    private CommonAdapter<TyjUserBean> mAdapter;
    private List<TyjUserBean> datas;
    private Intent mIntent;
    @Override
    protected void beforeView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void obtainIntent() {

    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        mAdapter = new CommonAdapter<TyjUserBean>(this,R.layout.user_iterm,datas) {
            @Override
            protected void convert(ViewHolder holder, final TyjUserBean tyjUserBean, int position) {
                TextView uname = holder.getView(R.id.uname);
                TextView sname = holder.getView(R.id.sname);
                TextView bname = holder.getView(R.id.bname);
                uname.setText(tyjUserBean.getUname());
                if(TextUtils.isEmpty(tyjUserBean.getSname()))
                    sname.setText(" 暂无课程");
                else
                    sname.setText(tyjUserBean.getSname());
                if(TextUtils.isEmpty(tyjUserBean.getBname()))
                    bname.setText("暂无书籍");
                else
                    bname.setText(tyjUserBean.getBname());
                uname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mIntent = new Intent(MainActivity.this,UpdateUserActivity.class);
                        mIntent.putExtra("uid",tyjUserBean.getUid());
                        mIntent.putExtra("uname",tyjUserBean.getUname());
                        startActivity(mIntent);
                    }
                });
                sname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mIntent = new Intent(MainActivity.this,UpdateSubActivity.class);
                        mIntent.putExtra("uid",tyjUserBean.  getUid());
                        mIntent.putExtra("sid",tyjUserBean.  getSid());
                        mIntent.putExtra("sname",tyjUserBean.getSname());
                        startActivity(mIntent);
                    }
                });
                bname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty((tyjUserBean.getSid()))){
                            Toast.makeText(MainActivity.this, "课程不存在，请先添加课程",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mIntent = new Intent(MainActivity.this,UpdateBookActivity.class);
                        mIntent.putExtra("sid",tyjUserBean.getSid());
                        mIntent.putExtra("bid",tyjUserBean.getBid());
                        mIntent.putExtra("bname",tyjUserBean.getBname());
                        startActivity(mIntent);
                    }
                });
              /*  sname.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        //todo 是否删除
                        DialogUtil dialogUtil = new DialogUtil();
                        dialogUtil.createDialog(MainActivity.this,"课程",
                                "是否删除当前课程？"
                        );
                        return false;
                    }
                });*/
            }
        };
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("title")
                        .setMessage("message")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                T.show(MainActivity.this," 当前已经是最新版本",1);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
            }
            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {

                return false;
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getData() {
        RetrofitUtil.getApiService().queryUser()
                .compose(RxSchedulers.<List<TyjUserBean>>compose())
                .compose(this.<List<TyjUserBean>>bindToLifecycle())
                .subscribe(new BaseObserver2<List<TyjUserBean>>() {
                    @Override
                    protected void onHandleSuccess(List<TyjUserBean> userBean) {
                        //String title = userBean.getName();
                        if(userBean != null){
                            if(datas != null)
                                datas.clear();
                            datas.addAll(userBean);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    protected void onHandleError(String msg) {
                        Log.d("TAG",msg);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
