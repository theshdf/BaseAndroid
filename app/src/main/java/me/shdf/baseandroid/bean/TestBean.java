package me.shdf.baseandroid.bean;

import java.io.Serializable;

/**
 * Created by shdf on 2018/6/28.
 * wechat：zcm656025633
 * exp：
 **/
public class TestBean implements Serializable{
    boolean message;

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }
}
