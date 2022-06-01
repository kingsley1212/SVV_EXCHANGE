package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

/**
 * Created by js on 2018/5/24.
 */

public class ArticleEntity extends BaseEntity{

        private String content;
        private String title;
        private String add_time;
        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }
        public String getAdd_time() {
            return add_time;
        }
}
