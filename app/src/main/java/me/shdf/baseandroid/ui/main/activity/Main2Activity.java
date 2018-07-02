package me.shdf.baseandroid.ui.main.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import kr.co.namee.permissiongen.PermissionGen;
import me.shdf.baseandroid.BuildConfig;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseActivity;
import me.shdf.baseandroid.base.rvbaseAdapter.CommonAdapter;
import me.shdf.baseandroid.base.rvbaseAdapter.base.ViewHolder;
import me.shdf.baseandroid.bean.LoginBean;
import me.shdf.baseandroid.bean.TyjUserBean;
import me.shdf.baseandroid.db.DbManger;
import me.shdf.baseandroid.db.User;
import me.shdf.baseandroid.http.BaseObserver2;
import me.shdf.baseandroid.http.RetrofitUtil;
import me.shdf.baseandroid.http.RxSchedulers;
import me.shdf.baseandroid.util.DialogUtil;

public class Main2Activity extends BaseActivity implements DialogUtil.OnClickDialog{
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    TextView name;
    List<TyjUserBean> datas;
    private CommonAdapter<TyjUserBean> mAdapter;
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
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TyjUserBean bean = new TyjUserBean();
            bean.setUid("uid"+i);
            bean.setUname("uname"+i);
            bean.setSid("sid"+i);
            bean.setSname("sname"+i);
            bean.setBid("bid"+i);
            bean.setBname("bname"+i);
            datas.add(bean);
        }
        mAdapter = new CommonAdapter<TyjUserBean>(this,R.layout.user_iterm,datas) {
            @Override
            protected void convert(ViewHolder holder, TyjUserBean tyjUserBean, int position) {
                TextView uname = holder.getView(R.id.uname);
                TextView sname = holder.getView(R.id.sname);

                TextView bname = holder.getView(R.id.bname);

                uname.setText(tyjUserBean.getUname());

                sname.setText(tyjUserBean.getSname());

                bname.setText(tyjUserBean.getBname());

            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        RetrofitUtil.getApiService().loginUser("zcm","123")
                .compose(RxSchedulers.<LoginBean>compose())
                .compose(this.<LoginBean>bindToLifecycle())
                .subscribe(new BaseObserver2<LoginBean>() {
                    @Override
                    protected void onHandleSuccess(LoginBean userBean) {
                        //String title = userBean.getName();
                        if(userBean != null){

                        }
                    }
                    @Override
                    protected void onHandleError(String msg) {
                        Log.d("TAG",msg);
                    }
                });

        User u = new User();
        u.setAge(10);
        u.setName("zcm");
        DbManger.getDao().insertOrReplace(u);
        List<User> users = DbManger.getDao().loadAll();
       for (User u1: users) {
            Toast.makeText(this,(DbManger.getDao() == DbManger.getDao())+"",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void initListener() {

    }
    
    @Override
    protected void initData() {
        //todo 判断版本号
        /*final int localVersionCode = getVersionCode();
        RetrofitUtil
                .getApiService()
                .getAppVersion()
                .compose(RxSchedulers.<BaseResponse<UpdateAppBean>>compose())
                .compose(this.<BaseResponse<UpdateAppBean>>bindToLifecycle())
                .subscribe(new BaseObserver<UpdateAppBean>() {
                    @Override
                    protected void onHandleSuccess(UpdateAppBean updateAppBean) {
                        int serverCode = updateAppBean.getVersioncode();
                        if(serverCode >localVersionCode){
                            //todo sho dialog to tell dailog
                            DialogUtil.createDialog(Main2Activity.this,
                                    "版本升级",
                                    "发现新版本，请进行升级",
                                    "确定",
                                    "取消");

                        }
                        else{
                            T.show(Main2Activity.this," 当前已经是最新版本",1);
                        }
                    }
                    @Override
                    protected void onHandleError(String msg) {

                    }
                });*/
    }
    public int getVersionCode() {
        PackageManager pm = getPackageManager();
        PackageInfo mPackageInfo = null;
        int versionCode = 0;
        try {
            mPackageInfo = pm.getPackageInfo(getPackageName(),0);
            versionCode = mPackageInfo.versionCode;
            String versionName = mPackageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

     ProgressDialog pd;    //进度条对话框
    private void loadNewVersionProgress() {
        final   String uri="http://www.apk.anzhi.com/data3/apk/201703/14/4636d7fce23c9460587d602b9dc20714_88002100.apk";

        pd = new  ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread(){
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(uri, pd);
                    sleep(3000);

                } catch (Exception e) {
                    //下载apk失败
                 //   Toast.makeText(getApplicationContext(), "下载新版本失败", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }}.start();
    }


    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", file);
       // intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }

    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public File getFileFromServer(String uri, final ProgressDialog pd) throws Exception{
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            URL url = new URL(uri);
            HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time= System.currentTimeMillis();//当前时间的毫秒数
            final File file = new File(Environment.getExternalStorageDirectory(), time+"updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len ;
            int total=0;
            while((len =bis.read(buffer))!=-1){
                fos.write(buffer, 0, len);
                total+= len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            Main2Activity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                }
            });
            return file;
        }
        else{
            return null;
        }
    }

    @Override
    public void onConfim() {
        PermissionGen.with(this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .request();
        loadNewVersionProgress();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(pd != null && pd.isShowing())
            pd.dismiss();
    }
}
