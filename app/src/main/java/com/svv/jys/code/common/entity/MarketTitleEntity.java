package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/18.
 */

public class MarketTitleEntity extends BaseEntity{

    private List<String> coin_name;
    private ArrayList<MarketListEntity> collect = new ArrayList<>();
    private List<ArrayList<MarketListEntity>> market = new ArrayList<>();
    private String coin;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public List<String> getCoin_name() {
        return coin_name;
    }

    public void setCoin_name(List<String> coin_name) {
        this.coin_name = coin_name;
    }

    public ArrayList<MarketListEntity> getCollect() {
        return collect;
    }

    public void setCollect(ArrayList<MarketListEntity> collect) {
        this.collect = collect;
        if(this.collect == null){
            this.collect = new ArrayList<>();
        }
    }

    public List<ArrayList<MarketListEntity>> getMarket() {
        return market;
    }

    public void setMarket(List<ArrayList<MarketListEntity>> market) {
        this.market = market;
    }

}
