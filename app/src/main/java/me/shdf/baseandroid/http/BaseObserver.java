package me.shdf.baseandroid.http;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.shdf.baseandroid.app.AppApplication;
import me.shdf.baseandroid.base.basebean.BaseResponse;

/**
 * Created by shdf on 2018/5/31.
 * wechat：zcm656025633
 * exp：
 * 1。 封装一个加载动画
 * 2。 生命周期的管理
 **/
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private static final String TAG = "BaseObserver";
    private Context mContext;

   protected BaseObserver() {
        this.mContext = AppApplication.getContext();
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    //todo 正常请求到数据之后的问题
    @Override
    public void onNext(BaseResponse<T> value) {
        if (value.isSuccess()) {
            T t = value.getData();
            onHandleSuccess(t);
        } else {
            onHandleError(value.getRetmsg());
        }
    }

    //todo 在请求的过程中出现问题
    @Override
    public void onError(Throwable e) {
       //todo 分类集中常见的异常
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(mContext, "网络异常", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {

        } else if (e instanceof UnknownHostException) {

        }else if(e instanceof ClassCastException){

        }
        else {
            String error = e.getMessage();
        }

        //todo 隐藏进度条
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
        //todo 隐藏进度条
    }


    protected abstract void onHandleSuccess(T t);

    //todo 服务器请求到数据的code不正确
    protected abstract  void onHandleError(String msg);
}
