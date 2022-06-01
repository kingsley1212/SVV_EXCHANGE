package com.svv.jys.code.module.server.dataserver.event;

import com.svv.jys.code.common.entity.MarketListEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lzj on 2018/5/23.
 * 市场数据event
 */
public  class MarketListEvent {

    public String resData;
    public List<MarketListEntity> marketListEntities;
    private List<List<MarketListEntity>> marketList;


    public MarketListEvent() {
    }


    public MarketListEvent(String resData, List<MarketListEntity> marketListEntities) {
        this.resData = resData;
        this.marketListEntities = marketListEntities;
    }

    public void rank() {
        Collections.sort(marketListEntities, new Comparator<MarketListEntity>() {
            @Override
            public int compare(MarketListEntity o1, MarketListEntity o2) {
//                    return Double.valueOf(o2.getChange()).compareTo(Double.valueOf(o1.getChange()));
                    return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public void setMarketList() {
        this.marketList = new ArrayList<>();
        rank();
        int size = marketListEntities.size() / 3 +1;
        for(int i = 0;i<size;i++){
            List<MarketListEntity> list = new ArrayList<>();
            if(i * 3 < marketListEntities.size()) {
                list.add(marketListEntities.get(i * 3));
            }
            if(i * 3 + 1 <marketListEntities.size()){
                list.add(marketListEntities.get(i * 3 +1));
            }
            if(i * 3 + 2 <marketListEntities.size()){
                list.add(marketListEntities.get(i * 3 + 2));
            }
            marketList.add(list);
        }
    }

    public List<List<MarketListEntity>> getMarketList() {
        return marketList;
    }

    public String getResData() {
        return resData;
    }

    public void setResData(String resData) {
        this.resData = resData;
    }

    public List<MarketListEntity> getMarketListEntities() {
        return marketListEntities;
    }

    public void setMarketListEntities(List<MarketListEntity> marketListEntities) {
        this.marketListEntities = marketListEntities;
    }
}
