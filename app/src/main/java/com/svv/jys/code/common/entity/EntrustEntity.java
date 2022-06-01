package com.svv.jys.code.common.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by js on 2018/5/25.
 */

public class EntrustEntity extends BaseEntity {
    /**
     * limit : 10
     * total : 2
     * offset : 0
     * rows : [{"endtime":"0","memo":"","buy":"btc","status":"0","round":"8","type":"2","mum":"1.50000000",
     * "trade_type":"0","fee_percent":"0.00","fee":"0.00000000","id":"504","num":"1.00000000","price":"1.50000000",
     * "market":"eth_btc","deal":"0.00000000","sell":"eth","user_id":"1","add_time":"1527148993","account_type":"0"},
     * {"endtime":"0","memo":"","buy":"btc","status":"0","round":"8","type":"1","mum":"1.00000000","trade_type":"0",
     * "fee_percent":"0.00","fee":"0.00000000","id":"503","num":"1.00000000","price":"1.00000000","market":"eth_btc",
     * "deal":"0.00000000","sell":"eth","user_id":"1","add_time":"1527148978","account_type":"0"}]
     */

    private int limit;
    private String total;
    private int offset;
    private List<RowsBean> rows;

    public Map<String,String> getUser_coin() {
        return user_coin;
    }

    public void setUser_coin(Map<String,String> user_coin) {
        this.user_coin = user_coin;
    }

    public Map<String,String> user_coin;

    public static class UserCoin{
        public String getCoin() {
            return coin;
        }

        public void setCoin(String coin) {
            this.coin = coin;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        private String coin;
        private String num;
    }



    public static void socketEntrust(String data,EntrustEntity entrustEntity){
        JSONObject json = JSONObject.parseObject(data);
     /*   List<UserCoin> userCoins=new ArrayList<>();
        for (Map.Entry<String, Object> e : user_coin.entrySet()) {
            UserCoin userCoin =new UserCoin();
            userCoin.setCoin(e.getKey());
            userCoin.setNum(e.getValue().toString());
            userCoins.add(userCoin);
        }*/
        entrustEntity.setUser_coin(JSON.parseObject(json.getString("user_coin"),Map.class));
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean extends BaseEntity {
        /**
         * endtime : 0
         * memo :
         * buy : btc
         * status : 0
         * round : 8
         * type : 2
         * mum : 1.50000000
         * trade_type : 0
         * fee_percent : 0.00
         * fee : 0.00000000
         * id : 504
         * num : 1.00000000
         * price : 1.50000000
         * market : eth_btc
         * deal : 0.00000000
         * sell : eth
         * user_id : 1
         * add_time : 1527148993
         * account_type : 0
         */
        private String status_name;

        private String deal_total;


        public String getDeal_total() {
            return deal_total;
        }

        public void setDeal_total(String deal_total) {
            this.deal_total = deal_total;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }
        public String type_name;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        private String endtime;
        private String memo;
        private String buy;
        private String status;
        private String round;
        private String type;
        private String mum;
        private String trade_type;
        private String fee_percent;
        private String fee;
        private String id;
        private String num;
        private String price;
        private String market;
        private String deal;
        private String sell;
        private String user_id;
        private String add_time;
        private String account_type;

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getBuy() {
            return buy;
        }

        public void setBuy(String buy) {
            this.buy = buy;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRound() {
            return round;
        }

        public void setRound(String round) {
            this.round = round;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMum() {
            return mum;
        }

        public void setMum(String mum) {
            this.mum = mum;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }

        public String getFee_percent() {
            return fee_percent;
        }

        public void setFee_percent(String fee_percent) {
            this.fee_percent = fee_percent;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMarket() {
            return market;
        }

        public void setMarket(String market) {
            this.market = market;
        }

        public String getDeal() {
            return deal;
        }

        public void setDeal(String deal) {
            this.deal = deal;
        }

        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getAccount_type() {
            return account_type;
        }

        public void setAccount_type(String account_type) {
            this.account_type = account_type;
        }
    }
}
