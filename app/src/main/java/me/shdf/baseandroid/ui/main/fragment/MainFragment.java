package me.shdf.baseandroid.ui.main.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.abslistview.CommonAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.shdf.baseandroid.R;
import me.shdf.baseandroid.base.base.BaseFragment;
import me.shdf.baseandroid.bean.RecommendBean;
import me.shdf.baseandroid.ui.main.activity.ParentActivity;
import me.shdf.baseandroid.wigdet.banner.NetworkImageHolderView;



/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {

    @BindView(R.id.lv_recommend)
    ListView lv_recommend;
    @BindView(R.id.convenientBanner)
    ConvenientBanner banner;
    @BindView(R.id.cb_lunch)
    ConvenientBanner banner_lunch;
    @BindView(R.id.ll_jzh)
    LinearLayout ll_jzh;
    private CommonAdapter<RecommendBean> mRecommendBeanCommonAdapter;
    //假数据
    private List<RecommendBean> mRecommendBean;

    private String[] images = {
            "http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://img13.poco.cn/mypoco/myphoto/20120828/15/55689209201208281549023849547194135_001.jpg"
    };
    List<String> urls;


    //常用功能栏图片数组

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void initListener(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    public void initData() {
        mRecommendBean = new ArrayList<>();
        RecommendBean bean  =new RecommendBean();
        RecommendBean bean1 =new RecommendBean();
        bean1.setContent("清明放假通知");
        bean1.setImg_flag(R.mipmap.h_fjtz);
        bean1.setImg_url("http://img0.imgtn.bdimg.com/it/u=819395340,3218052234&fm=23&gp=0.jpg");
        mRecommendBean.add(bean1);
        RecommendBean bean2  =new RecommendBean();
        bean2.setContent("附近有兴趣班推荐额");
        bean2.setImg_flag(R.mipmap.h_fjtj);
        bean2.setImg_url("http://img0.imgtn.bdimg.com/it/u=819395340,3218052234&fm=23&gp=0.jpg");
        mRecommendBean.add(bean2);
        mRecommendBeanCommonAdapter = new CommonAdapter<RecommendBean>(getActivity(),R.layout.recommend_item,mRecommendBean) {
            @Override
            public void convert(ViewHolder holder, RecommendBean recommendBean) {
                holder.getView(R.id.iv_flag_rem).setBackgroundResource(recommendBean.getImg_flag());
                ((TextView)holder.getView(R.id.tv_rem)).setText(recommendBean.getContent());
                ImageView imageView = (ImageView) holder.getView(R.id.iv_rem);
                Glide.with(MainFragment.this).load(recommendBean.getImg_url()).into(imageView);
            }
        };
        lv_recommend.setAdapter(mRecommendBeanCommonAdapter);

        //banner
        urls = Arrays.asList(images);
        banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {

            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },urls).setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused}).setCanLoop(true);

        banner_lunch.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new NetworkImageHolderView();
            }
        },urls);
        //
        ll_jzh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ParentActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        banner.startTurning(2000);
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopTurning();
    }
}