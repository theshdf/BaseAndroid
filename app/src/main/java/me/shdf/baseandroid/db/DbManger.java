package me.shdf.baseandroid.db;

import org.greenrobot.greendao.database.Database;

import me.shdf.baseandroid.app.AppApplication;

/**
 * Created by shdf on 2018/6/17.
 * wechat：zcm656025633
 * exp：
 **/
public class DbManger {
    private static  DaoSession mSession;
    static {
        DaoMaster.DevOpenHelper help = new DaoMaster.DevOpenHelper(AppApplication.getContext(),"db");
        Database database = help.getWritableDb();
        DaoMaster master = new DaoMaster(database);
        mSession= master.newSession();
    }


    public static  UserDao getDao(){
        UserDao dao =mSession.getUserDao();
        return  dao;
    }
}
