package me.shdf.baseandroid.http;

import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.shdf.baseandroid.App.AppApplication;
import me.shdf.baseandroid.util.NetUtil;
import me.shdf.baseandroid.util.ViewUtil;

/**
 * Created by shdf on 2018/5/31.
 * wechat：zcm656025633
 * exp：
 **/
public class RxSchedulers {

        public static <T> ObservableTransformer<T, T> compose() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (!NetUtil.isNetworkAvailable()) {
                                    Toast.makeText(AppApplication.getContext(), "请检查网络", Toast.LENGTH_SHORT).show();
                                    disposable.dispose();
                                }
                                else{
                                    //todo 显示dialog
                                   ViewUtil.getDialogInstance(AppApplication.getmActivity()).show();
                                }
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Exception {
                                ViewUtil.getDialogInstance(AppApplication.getmActivity()).hide();
                            }
                        });
            }
        };
    }
}
