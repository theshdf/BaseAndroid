package me.shdf.baseandroid.ui.main.contract;

import android.content.Context;

import java.util.List;

import me.shdf.baseandroid.base.base.BasePresenter;
import me.shdf.baseandroid.base.base.BaseView;
import me.shdf.baseandroid.bean.DouBean;


/**
 * Created by ${shdf} on 17/4/12.
 * wechat：zcm656025633
 * exp：
 **/

public interface MainContract {
     interface View extends BaseView {
         void loadData(List<DouBean.SubjectsBean> beanList);

    }

     abstract class Presenter extends BasePresenter<View> {
         public abstract  void getData(Context context);
     }
}
