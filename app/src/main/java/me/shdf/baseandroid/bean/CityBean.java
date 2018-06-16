package me.shdf.baseandroid.bean;

import java.util.List;

/**
 * Created by shdf on 17/5/21.
 * wechat：zcm656025633
 * exp：
 **/

public class CityBean {

    /**
     * resultCode : 1
     * resultMessage : 共1条数据
     * data : [{"educationId":1111,"educationName":"杭州英孚","educationPosition":"城西银泰","educationUrl":"http://sdfddfdfg","educationPropaganda":"dsfd","educationLogo":"http://....jpg","educationType":1,"educationAbstract":null}]
     */

    private int resultCode;
    private String resultMessage;
    private List<DataBean> data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * educationId : 1111
         * educationName : 杭州英孚
         * educationPosition : 城西银泰
         * educationUrl : http://sdfddfdfg
         * educationPropaganda : dsfd
         * educationLogo : http://....jpg
         * educationType : 1
         * educationAbstract : null
         */

        private int educationId;
        private String educationName;
        private String educationPosition;
        private String educationUrl;
        private String educationPropaganda;
        private String educationLogo;
        private int educationType;
        private Object educationAbstract;

        public int getEducationId() {
            return educationId;
        }

        public void setEducationId(int educationId) {
            this.educationId = educationId;
        }

        public String getEducationName() {
            return educationName;
        }

        public void setEducationName(String educationName) {
            this.educationName = educationName;
        }

        public String getEducationPosition() {
            return educationPosition;
        }

        public void setEducationPosition(String educationPosition) {
            this.educationPosition = educationPosition;
        }

        public String getEducationUrl() {
            return educationUrl;
        }

        public void setEducationUrl(String educationUrl) {
            this.educationUrl = educationUrl;
        }

        public String getEducationPropaganda() {
            return educationPropaganda;
        }

        public void setEducationPropaganda(String educationPropaganda) {
            this.educationPropaganda = educationPropaganda;
        }

        public String getEducationLogo() {
            return educationLogo;
        }

        public void setEducationLogo(String educationLogo) {
            this.educationLogo = educationLogo;
        }

        public int getEducationType() {
            return educationType;
        }

        public void setEducationType(int educationType) {
            this.educationType = educationType;
        }

        public Object getEducationAbstract() {
            return educationAbstract;
        }

        public void setEducationAbstract(Object educationAbstract) {
            this.educationAbstract = educationAbstract;
        }
    }
}
