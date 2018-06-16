package me.shdf.baseandroid.base.base;

/**
 * Created by ${shdf} on 17/4/11.
 * wechat：zcm656025633
 * exp：
 **/

public abstract class BasePresenter<T> {

    public abstract void attachView(T context);
    public abstract void dettachView();

}
