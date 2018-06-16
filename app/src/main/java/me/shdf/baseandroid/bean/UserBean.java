package me.shdf.baseandroid.bean;

import java.io.Serializable;

/**
 * Created by shdf on 2018/6/5.
 * wechat：zcm656025633
 * exp：
 **/
public class UserBean implements Serializable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
