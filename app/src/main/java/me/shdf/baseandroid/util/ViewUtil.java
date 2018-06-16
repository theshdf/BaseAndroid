package me.shdf.baseandroid.util;

import android.content.Context;

import me.shdf.baseandroid.wigdet.CustomProgressDialog;

/**
 * Created by shdf on 2018/6/16.
 * wechat：zcm656025633
 * exp：
 **/
public class ViewUtil {
    private static CustomProgressDialog dialog;
    private ViewUtil(){}
    public static CustomProgressDialog getDialogInstance(Context context) {
        if(dialog == null)
            dialog = new CustomProgressDialog(context);
        return dialog;
    }
}
