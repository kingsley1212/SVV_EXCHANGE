package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.io.Serializable;

public class VerifyEntity extends BaseEntity {

    /**
     * verify : {"mobile":1,"email":2,"google":0}
     * info : {"mobile":"137****5431","email":"sdf****sre@qq.com","google":0}
     */

    private VerifyBean verify;
    private InfoBean info;

    public VerifyBean getVerify() {
        return verify;
    }

    public void setVerify(VerifyBean verify) {
        this.verify = verify;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public static class VerifyBean implements Serializable {
        /**
         * mobile : 1
         * email : 2
         * google : 0
         */

        private String mobile;
        private String email;
        private String google;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGoogle() {
            return google;
        }

        public void setGoogle(String google) {
            this.google = google;
        }
    }

    public static class InfoBean implements Serializable {
        /**
         * mobile : 137****5431
         * email : sdf****sre@qq.com
         * google : 0
         */

        private String mobile;
        private String email;
        private String google;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGoogle() {
            return google;
        }

        public void setGoogle(String google) {
            this.google = google;
        }
    }
}
