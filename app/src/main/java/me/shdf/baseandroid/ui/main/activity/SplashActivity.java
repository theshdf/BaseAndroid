package me.shdf.baseandroid.ui.main.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseActivity;
import me.shdf.baseandroid.util.SPUtils;


public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                //判断引导页
                boolean showTutorial = (boolean) SPUtils.get(SplashActivity.this, "FirstEnter", true);
               if (false) {
                    SPUtils.put(SplashActivity.this, "FirstEnter", false);
                }
                else {
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        }
    };

    @Override
    protected void beforeView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void obtainIntent() {
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        //
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            handler.sendEmptyMessage(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
