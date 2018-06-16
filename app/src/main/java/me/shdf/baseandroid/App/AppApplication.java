package me.shdf.baseandroid.App;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

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
