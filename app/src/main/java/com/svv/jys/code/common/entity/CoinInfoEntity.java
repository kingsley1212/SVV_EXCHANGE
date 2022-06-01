package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public class CoinInfoEntity extends BaseEntity{


    /**
     * name : btc
     * able : 0.00000000
     * freeze : 0.00000000
     * logo : http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG
     * address : 581cc302a7bb1b13b414e0e807f7d65b
     * memo :
     * total : 3
     * usdt_price : 0.00000000
     * rows : [{"name":"btc","able":"0.00000000","freeze":"0.00000000","logo":"http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG","address":"581cc302a7bb1b13b414e0e807f7d65b","memo":"","total":"0.00000000","usdt_price":"0.00000000"}]
     * sum_total : 0.00000000
     * is_outfee : 1
     * tips :
     * out_fee : 10.00000000
     * out_min : 1.00000000
     * coin : btc
     */

    private String name;
    private String able;
    private String freeze;
    private String logo;
    private String address;
    private String memo;
    private String total;
    private String usdt_price;
    private String sum_total;
    private String is_outfee;
    private String tips;
    private String out_fee;
    private String out_min;
    private String coin;
    private List<RowsBean> rows;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAble() {
        return able;
    }

    public void setAble(String able) {
        this.able = able;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUsdt_price() {
        return usdt_price;
    }

    public void setUsdt_price(String usdt_price) {
        this.usdt_price = usdt_price;
    }

    public String getSum_total() {
        return sum_total;
    }

    public void setSum_total(String sum_total) {
        this.sum_total = sum_total;
    }

    public String getIs_outfee() {
        return is_outfee;
    }

    public void setIs_outfee(String is_outfee) {
        this.is_outfee = is_outfee;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getOut_fee() {
        return out_fee;
    }

    public void setOut_fee(String out_fee) {
        this.out_fee = out_fee;
    }

    public String getOut_min() {
        return out_min;
    }

    public void setOut_min(String out_min) {
        this.out_min = out_min;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        /**
         * name : btc
         * able : 0.00000000
         * freeze : 0.00000000
         * logo : http://192.168.1.118/yaf_digital_v1/public/static/upload/coin_logo/201902/262b2bd127aa4e2a1bb8199205c9c3e0.PNG
         * address : 581cc302a7bb1b13b414e0e807f7d65b
         * memo :
         * total : 0.00000000
         * usdt_price : 0.00000000
         */

        private String name;
        private String able;
        private String freeze;
        private String logo;
        private String address;
        private String memo;
        private String total;
        private String usdt_price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAble() {
            return able;
        }

        public void setAble(String able) {
            this.able = able;
        }

        public String getFreeze() {
            return freeze;
        }

        public void setFreeze(String freeze) {
            this.freeze = freeze;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getUsdt_price() {
            return usdt_price;
        }

        public void setUsdt_price(String usdt_price) {
            this.usdt_price = usdt_price;
        }
    }
}
