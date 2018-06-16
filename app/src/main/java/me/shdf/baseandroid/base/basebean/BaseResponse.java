package me.shdf.baseandroid.base.basebean;

/**
 * Created by ${shdf} on 17/4/12.
 * wechat：zcm656025633
 * exp：后台返回数据的结构体
 **/

public class BaseResponse<T> {

    private String retmsg;
    private int retcode;
    private T data;

    //todo 代表请求成功
    public boolean isSuccess(){
        return retcode == 200;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "retmsg='" + retmsg + '\'' +
                ", retcode=" + retcode +
                ", data=" + data +
                '}';
    }
}
