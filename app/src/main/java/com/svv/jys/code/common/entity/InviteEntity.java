package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class InviteEntity extends BaseEntity {

    /**
     * invite_num : 4
     * setting : {"title":"邀请好友注册，轻松获得交易返佣","title2":"当日即得手续费30%返佣福利","image_title":"币贝网邀请返佣","image_title2":"","invite_desc":" "}
     * invite_value : 0.00000000
     */

    private String invite_num;
    private SettingBean setting;
    private String invite_value;

    public String getInvite_num() {
        return invite_num;
    }

    public void setInvite_num(String invite_num) {
        this.invite_num = invite_num;
    }

    public SettingBean getSetting() {
        return setting;
    }

    public void setSetting(SettingBean setting) {
        this.setting = setting;
    }

    public String getInvite_value() {
        return invite_value;
    }

    public void setInvite_value(String invite_value) {
        this.invite_value = invite_value;
    }

    public static class SettingBean {
        /**
         * title : 邀请好友注册，轻松获得交易返佣
         * title2 : 当日即得手续费30%返佣福利
         * image_title : 币贝网邀请返佣
         * image_title2 :
         * invite_desc :
         */

        private String title;
        private String title2;
        private String image_title;
        private String image_title2;
        private String invite_desc;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle2() {
            return title2;
        }

        public void setTitle2(String title2) {
            this.title2 = title2;
        }

        public String getImage_title() {
            return image_title;
        }

        public void setImage_title(String image_title) {
            this.image_title = image_title;
        }

        public String getImage_title2() {
            return image_title2;
        }

        public void setImage_title2(String image_title2) {
            this.image_title2 = image_title2;
        }

        public String getInvite_desc() {
            return invite_desc;
        }

        public void setInvite_desc(String invite_desc) {
            this.invite_desc = invite_desc;
        }
    }
}
