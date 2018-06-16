package me.shdf.baseandroid.base.base;

/**
 * Created by ${shdf} on 17/4/11.
 * wechat：zcm656025633
 * exp：
 **/

public  interface BaseView {
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
