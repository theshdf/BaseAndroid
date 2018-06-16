package me.shdf.baseandroid.ui.main.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseActivity;
import me.shdf.baseandroid.ui.adapter.CommonPagerAdapter;


public class TutorialActivity extends BaseActivity {
    @BindView(R.id.mViewPager)
    ViewPager mViewPager;
    private List<View> views;

    @Override
    protected void beforeView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tutorial;
    }

    @Override
    protected void obtainIntent() {

    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        views = new ArrayList<>();
        //
        for (int i = 0; i < 4; i++) {
            ImageView view1 = new ImageView(this);
            view1.setBackgroundResource(R.mipmap.tutorial);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            view1.setLayoutParams(params);
            if (i == 3) {
                //最后一页
                FrameLayout frame = new FrameLayout(this);
                FrameLayout.LayoutParams frameLayout = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                frame.setLayoutParams(frameLayout);
                Button button = new Button(this);
                button.setText("进入主页面");
                FrameLayout.LayoutParams buttonparams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                button.setLayoutParams(buttonparams);
                buttonparams.setMargins(10, 10, 10, 120);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //直接关闭即可
                        finish();
                    }
                });
                frame.addView(view1);
                frame.addView(button);
                views.add(frame);
            } else {
                views.add(view1);
            }

        }
        mViewPager.setAdapter(new CommonPagerAdapter(views));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    Toast.makeText(TutorialActivity.this,"0",Toast.LENGTH_LONG).show();
                } else if (position == 1) {
                    Toast.makeText(TutorialActivity.this,"1",Toast.LENGTH_LONG).show();
                }
                else  if(position == 2){
                    Toast.makeText(TutorialActivity.this,"2",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
