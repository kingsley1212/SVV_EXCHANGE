package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

public class WelfareRecordEntity extends BaseEntity {

    /**
     * total : 3
     * rows : [{"hold_num":"0.00000000","user_name":"13512345678","status_name":"未审核","end_time":"0","hold_coin":"btc","reward_time":"--","user_id":"1","gain_coin":"btc","welfare_id":"6","name":"dfd","id":"9","gain_num":"0.00000000","add_time":"1554340122","status":"0"},{"hold_num":"100.00000000","user_name":"13512345678","status_name":"已拒绝","end_time":"1554262665","hold_coin":"btc","reward_time":"--","user_id":"1","gain_coin":"btc","welfare_id":"14","name":"我的奖励金1000元","id":"3","gain_num":"100.00000000","add_time":"1554262579","status":"3"},{"hold_num":"1000.00000000","user_name":"13512345678","status_name":"未审核","end_time":"0","hold_coin":"btc","reward_time":"--","user_id":"1","gain_coin":"btc","welfare_id":"1","name":"BTC大派送","id":"1","gain_num":"10000.00000000","add_time":"1553648241","status":"0"}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * hold_num : 0.00000000
         * user_name : 13512345678
         * status_name : 未审核
         * end_time : 0
         * hold_coin : btc
         * reward_time : --
         * user_id : 1
         * gain_coin : btc
         * welfare_id : 6
         * name : dfd
         * id : 9
         * gain_num : 0.00000000
         * add_time : 1554340122
         * status : 0
         */

        private String hold_num;
        private String user_name;
        private String status_name;
        private String end_time;
        private String hold_coin;
        private String reward_time;
        private String user_id;
        private String gain_coin;
        private String welfare_id;
        private String name;
        private String id;
        private String gain_num;
        private String add_time;
        private String status;

        public String getHold_num() {
            return hold_num;
        }

        public void setHold_num(String hold_num) {
            this.hold_num = hold_num;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getHold_coin() {
            return hold_coin;
        }

        public void setHold_coin(String hold_coin) {
            this.hold_coin = hold_coin;
        }

        public String getReward_time() {
            return reward_time;
        }

        public void setReward_time(String reward_time) {
            this.reward_time = reward_time;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getGain_coin() {
            return gain_coin;
        }

        public void setGain_coin(String gain_coin) {
            this.gain_coin = gain_coin;
        }

        public String getWelfare_id() {
            return welfare_id;
        }

        public void setWelfare_id(String welfare_id) {
            this.welfare_id = welfare_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGain_num() {
            return gain_num;
        }

        public void setGain_num(String gain_num) {
            this.gain_num = gain_num;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
