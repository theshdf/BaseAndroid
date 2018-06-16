package me.shdf.baseandroid.bean;

import java.io.Serializable;

/**
 * Created by shdf on 17/4/13.
 * wechat：zcm656025633
 * exp：
 **/

public class ParentAllBean implements Serializable {
    private String content;
    private String image_url;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
