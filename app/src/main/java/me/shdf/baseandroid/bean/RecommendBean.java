package me.shdf.baseandroid.bean;

import java.io.Serializable;

/**
 * Created by shdf on 17/4/15.
 * wechat：zcm656025633
 * exp：
 **/

public class RecommendBean implements Serializable {
    private String content;
    private String img_url;
    private int  img_flag;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getImg_flag() {
        return img_flag;
    }

    public void setImg_flag(int img_flag) {
        this.img_flag = img_flag;
    }

    @Override
    public String toString() {
        return "RecommendBean{" +
                "content='" + content + '\'' +
                ", img_url='" + img_url + '\'' +
                ", img_flag=" + img_flag +
                '}';
    }
}
