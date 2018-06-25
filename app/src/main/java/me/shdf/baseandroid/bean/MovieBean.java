package me.shdf.baseandroid.bean;

import java.io.Serializable;

/**
 * Created by shdf on 2018/5/30.
 * wechat：zcm656025633
 * exp：
 **/
public class MovieBean implements Serializable {
    private String username;
    private String nickename;
    public MovieBean(String userName, String nickName){
        this.username = userName;
        this.nickename = nickName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickename() {
        return nickename;
    }

    public void setNickename(String nickename) {
        this.nickename = nickename;
    }

    @Override
    public String toString() {
        return "MovieBean{" +
                "username='" + username + '\'' +
                ", nickename='" + nickename + '\'' +
                '}';
    }
}
