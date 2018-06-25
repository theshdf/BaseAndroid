package me.shdf.baseandroid.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseActivity;
import me.shdf.baseandroid.ui.main.fragment.InformationFragment;
import me.shdf.baseandroid.ui.main.fragment.MainFragment;
import me.shdf.baseandroid.ui.main.fragment.PersonalFragment;
import me.shdf.lib.util.ActivityControlUtil;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.button_one)
    RadioButton mButtonOne;
    @BindView(R.id.button_two)
    RadioButton mButtonTwo;
    @BindView(R.id.button_three)
    RadioButton mButtonThree;
    //@Bind(R.id.button_four)
    RadioButton mButtonFour;
    @BindView(R.id.button_group)
    RadioGroup mButtonGroup;
    private boolean firstEnter = true;
    private MainFragment mMainFragment = null;
    private InformationFragment mSecondFragment;
    private PersonalFragment mThirdFragment;
    private FragmentManager mManager;
    private FragmentTransaction mTransaction;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    private boolean isExit = false;

    @Override
    protected void beforeView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void obtainIntent() {

    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        ButterKnife.bind(this);
        mMainFragment = new MainFragment();
        mSecondFragment = new InformationFragment();
        mThirdFragment = new PersonalFragment();
        //保存状态
        if (saveInstanceState != null) {
            if(mMainFragment != null) {
                mMainFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(mMainFragment.getClass().getName());
                mSecondFragment = (InformationFragment) getSupportFragmentManager().findFragmentByTag(mSecondFragment.getClass().getName());
                mThirdFragment = (PersonalFragment) getSupportFragmentManager().findFragmentByTag(mThirdFragment.getClass().getName());
                getSupportFragmentManager().beginTransaction().hide(mSecondFragment).hide(mThirdFragment).show(mMainFragment).commit();
            }
        } else {
            //添加fragment到content

            //设置第一次显示的fragment
            mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction
                    .add(R.id.content,mMainFragment,mMainFragment.getClass().getName())
                    .add(R.id.content,mSecondFragment,mSecondFragment.getClass().getName())
                    .add(R.id.content,mThirdFragment,mThirdFragment.getClass().getName())
                    .hide(mSecondFragment).hide(mThirdFragment).commit();
        }
    }

    @Override
    protected void initListener() {
        //初始化监听设置
        mButtonOne.setOnClickListener(this);
        mButtonTwo.setOnClickListener(this);
        mButtonThree.setOnClickListener(this);
      //  mButtonFour.setOnClickListener(this);
        mButtonGroup.check(R.id.button_one);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (firstEnter) {
            Intent intent = new Intent(HomeActivity.this, SplashActivity.class);
            startActivity(intent);
            firstEnter = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void initData() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //退出应用
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            isExit = true;
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        handler.sendEmptyMessageDelayed(10, 2000);
        if (isExit == true) {
            //exit
            ActivityControlUtil.removeAll();
        } else {
            Toast.makeText(this, "再按一次进行退出", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        mManager = getSupportFragmentManager();
        int id = v.getId();
        switch (id) {
            case R.id.button_one:
                if(!mMainFragment.isAdded()){
                    mManager.beginTransaction().hide(mSecondFragment).hide(mThirdFragment).add(R.id.content,mMainFragment).commit();
                }
                else{
                    mManager.beginTransaction().hide(mSecondFragment).hide(mThirdFragment).show(mMainFragment).commit();
                }
                break;
            case R.id.button_two:
                if(!mMainFragment.isAdded()){
                    mManager.beginTransaction().hide(mMainFragment).hide(mThirdFragment).add(R.id.content,mSecondFragment).commit();
                }
                else{
                    mManager.beginTransaction().hide(mMainFragment).hide(mThirdFragment).show(mSecondFragment).commit();
                }
                break;
            case R.id.button_three:
                if(!mMainFragment.isAdded()){
                    mManager.beginTransaction().hide(mSecondFragment).hide(mMainFragment).add(R.id.content,mThirdFragment).commit();
                }
                else{
                    mManager.beginTransaction().hide(mSecondFragment).hide(mMainFragment).show(mThirdFragment).commit();
                }
                break;
        }
    }

}
