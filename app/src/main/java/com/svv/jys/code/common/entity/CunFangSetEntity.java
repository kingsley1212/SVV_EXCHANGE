package com.svv.jys.code.common.entity;


import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/8/10.
 */

public class CunFangSetEntity extends BaseEntity {


    /**
     * day_money : 3327.13214234
     * return_money : 3327.13214234
     * list : [{"percent":"[\"3\",\"1\",\"2\"]","coin":"btc","r_coin":"usdt"},{"percent":"[\"0.3\",\"0.1\",\"0.2\"]","coin":"eth","r_coin":"usdt"}]
     * revolution : [{"name":"1年","value":1},{"name":"2年","value":2},{"name":"无限期","value":0}]
     */

    private String day_money;
    private String return_money;
    private List<ListBean> list;
    private List<RevolutionBean> revolution;

    public String getDay_money() {
        return day_money;
    }

    public void setDay_money(String day_money) {
        this.day_money = day_money;
    }

    public String getReturn_money() {
        return return_money;
    }

    public void setReturn_money(String return_money) {
        this.return_money = return_money;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<RevolutionBean> getRevolution() {
        return revolution;
    }

    public void setRevolution(List<RevolutionBean> revolution) {
        this.revolution = revolution;
    }

    public static class ListBean extends BaseEntity{
        /**
         * percent : ["3","1","2"]
         * coin : btc
         * r_coin : usdt
         */

        private String percent;
        private String coin;
        private String r_coin;

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getR_coin() {
            return r_coin;
        }

        public void setR_coin(String r_coin) {
            this.r_coin = r_coin;
        }
    }

    public static class RevolutionBean extends BaseEntity{
        /**
         * name : 1年
         * value : 1
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}

