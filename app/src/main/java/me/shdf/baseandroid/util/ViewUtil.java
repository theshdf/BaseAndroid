package me.shdf.baseandroid.util;

import android.app.ProgressDialog;
import android.content.Context;

import me.shdf.baseandroid.wigdet.CustomProgressDialog;

/**
 * Created by shdf on 2018/6/16.
 * wechat：zcm656025633
 * exp：
 **/
public class ViewUtil {
    private static CustomProgressDialog dialog;

    private ViewUtil() {
    }

    public static CustomProgressDialog getDialogInstance(Context context) {
        if (dialog == null) {
            dialog = new CustomProgressDialog(context);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("正在加载...");
        }
        return dialog;
    }

}
