package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class ApplyEntity extends BaseEntity {

    /**
     * protocol : {"id":"4","title":"如何申请成为商家","content":"","add_time":"1489981748","state":"1"}
     */

    private ProtocolBean protocol;

    public ProtocolBean getProtocol() {
        return protocol;
    }

    public void setProtocol(ProtocolBean protocol) {
        this.protocol = protocol;
    }

    public static class ProtocolBean {
        /**
         * id : 4
         * title : 如何申请成为商家
         * content :
         * add_time : 1489981748
         * state : 1
         */

        private String id;
        private String title;
        private String content;
        private String add_time;
        private String state;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
