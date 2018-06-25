package me.shdf.baseandroid.bean;

import java.io.Serializable;

/**
 * Created by shdf on 2018/6/19.
 * wechat：zcm656025633
 * exp：
 **/
public class UpdateAppBean implements Serializable{
    int version;

    public int getVersioncode() {
        return version;
    }

    public void setVersioncode(int version) {
        this.version = version;
    }
}
