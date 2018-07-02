package me.shdf.baseandroid.base.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import me.shdf.baseandroid.R;
import me.shdf.lib.util.ActivityControlUtil;
import me.shdf.lib.util.StatusBarCompat;


/**
 * Created by zcm on 2016/4/1.
 * qq:656025633
 */
public abstract  class BaseActivity extends RxAppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeView();
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor();
        setContentView(getLayoutId());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            setTranslucentStatus(true);
//
        ButterKnife.bind(this);
        ActivityControlUtil.addActivity(this);
        obtainIntent();
        initView(savedInstanceState);
        initListener();
        initData();
        //添加集合

    }
    //setting title and so on
    protected abstract void beforeView();
    //get layout id
    protected abstract  int getLayoutId();
    //get intent
    protected abstract void obtainIntent();
    //get view
    protected abstract void initView(Bundle saveInstanceState);
    //set listener
    protected abstract void initListener();
    //set data
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出activity
        ActivityControlUtil.removeActivity(this);

    }
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void SetStatusBarColor(){
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }
    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color){
        StatusBarCompat.setStatusBarColor(this,color);
    }
    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }

}
