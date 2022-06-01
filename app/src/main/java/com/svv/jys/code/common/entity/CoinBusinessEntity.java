package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class CoinBusinessEntity extends BaseEntity {

    /**
     * market_info : {"id":"8","name":"usdt_btc","round":"8","fee_buy":"1.00","fee_sell":"1.00","buy_min":"0.00000100","buy_max":"1000000.00000000","sell_min":"0.00000100","sell_max":"1000000.00000000","trade_min":"0.00000100","trade_max":"1000000.00000000","invit_buy":"0","invit_sell":"0","invit_1":"1.00","invit_2":"1.00","invit_3":"1.00","zhang":"0","die":"0","is_trade":"1","begintrade":"00:00","endtrade":"00:00","status":"1","jiaoyiqu":"btc","hou_price":"3.73260000","tendency":"","new_price":"3.76020000","buy_price":"5.98682500","sell_price":"0.00000000","min_price":"3.72500000","max_price":"4.00000000","volume":"638.76622800","change":"0.74","api_min":"0.00000000","api_max":"0.00000000","sort":"255","add_time":"1553755258","endtime":"0","faxingjia":"2.23650000","beginfee":"00:00","endfee":"00:00","is_fee":"0","buy_name":"usdt","sell_name":"btc","hou_price_time":"1554281153","fee_link":"","debit_rate":"0.0000","debit_lt":"0","debit_buy_min":"0.00000000","debit_sell_min":"0.00000000","debit_exp_rate":"0.00","volume_day":"33.53210000","is_trade_ac":"0","usdt_price":4.2358}
     * user_coin : {"user_id":"1","b_address":"","e_address":"","btc":"110856.05088467","btc_freeze":"107.87308957","btc_address":"896882da2c5d270b8d4c615fa1715198","btc_s":"0","eth":"988.92000000","eth_freeze":"11.00000000","eth_address":"","eth_s":"0","usdt":"9968.09467026","usdt_freeze":"0.00000000","usdt_address":"1c27162e231532d288d90edb2b298024","usdt_s":"0"}
     */

    private MarketListEntity market_info;

    private FUserInfoEntity user;

    public MarketListEntity getMarket_info() {
        return market_info;
    }

    public void setMarket_info(MarketListEntity market_info) {
        this.market_info = market_info;
    }

    public FUserInfoEntity getUser() {
        return user;
    }

    public void setUser(FUserInfoEntity user) {
        this.user = user;
    }


}
