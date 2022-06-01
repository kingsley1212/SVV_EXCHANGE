package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/6/22.
 */

public class GoogleInfoEntity extends BaseEntity {
    /**
     * google_logo : https://chart.googleapis.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth%3A%2F%2Ftotp%2F13688885555%3Fsecret%3D5V4Y4NAIKUXAFKAQ
     * id : 1
     * google_secret : 5V4Y4NAIKUXAFKAQ
     * user_id : 1
     */

    private String google_logo;
    private String id;
    private String google_secret;
    private String user_id;

    public String getGoogle_logo() {
        return google_logo;
    }

    public void setGoogle_logo(String google_logo) {
        this.google_logo = google_logo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoogle_secret() {
        return google_secret;
    }

    public void setGoogle_secret(String google_secret) {
        this.google_secret = google_secret;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
