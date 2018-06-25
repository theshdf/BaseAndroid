package me.shdf.baseandroid.app;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import me.shdf.baseandroid.util.CrashModel;
import me.shdf.baseandroid.util.SpiderMan;
import me.shdf.lib.util.ActivityControlUtil;

/**
 * Created by ${shdf} on 17/4/11.
 * wechat：zcm656025633
 * exp：
 **/

public class AppApplication extends Application {
    static Context mAppApplication ;
    static Context activity;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppApplication = getApplicationContext();
        //初始化stetho
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
        SpiderMan.getInstance()
                .init(this)
                .setEnable(true)
                .showCrashMessage(true)
                .setOnCrashListener(new SpiderMan.OnCrashListener() {
            @Override
            public void onCrash(Thread t, Throwable ex, CrashModel model) {
                //todo  log 异常信息
            }
        });
    }
    public static Context getContext() {
        return mAppApplication;
    }
    public static Context getmActivity(){
        return ActivityControlUtil.getLastActivity();
    }

    public static void setActivity(Context context) {
        activity = context;
    }

}
