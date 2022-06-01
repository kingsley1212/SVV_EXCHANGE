package com.svv.jys.code.common.entity;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.R;
import com.svv.jys.code.common.base.BaseEntity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.app.MAppliaction;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by js on 2018/5/18.
 * 市场数据
 */
@DatabaseTable(tableName = "tb_fmarketlist")
public class MarketListEntity extends BaseEntity {

    @DatabaseField(id = true)
    /**本地数据库唯一标识ID**/
    private String id;
    @DatabaseField(defaultValue = "")
    private String new_price;
    @DatabaseField(defaultValue = "")
    private String change;
    @DatabaseField(defaultValue = "")
    private String round;
    @DatabaseField(defaultValue = "")
    private String hou_price_time;
    @DatabaseField(defaultValue = "")
    private String usdt_price;
    @DatabaseField(defaultValue = "")
    private String api_min;
    @DatabaseField(defaultValue = "")
    private String debit_rate;
    @DatabaseField(defaultValue = "")
    private String addtime;
    @DatabaseField(defaultValue = "")
    private String trade_min;
    @DatabaseField(defaultValue = "")
    private String zhang;
    @DatabaseField(defaultValue = "")
    private String debit_buy_min;
    @DatabaseField(defaultValue = "")
    private String invit_buy;
    @DatabaseField(defaultValue = "")
    private String buy_min;
    @DatabaseField(defaultValue = "")
    private String hou_price;
    @DatabaseField(defaultValue = "")
    private String buy_name;

    @DatabaseField(defaultValue = "")
    private String status;

    @DatabaseField(defaultValue = "")
    private String debit_exp_rate;
    @DatabaseField(defaultValue = "")
    private String invit_3;
    @DatabaseField(defaultValue = "")
    private String invit_1;
    @DatabaseField(defaultValue = "")
    private String cny_price;
    @DatabaseField(defaultValue = "")
    private String invit_2;
    @DatabaseField(defaultValue = "")
    private String sell_name;
    @DatabaseField(defaultValue = "")
    private String sell_name_big;
    @DatabaseField(defaultValue = "")
    private String api_max;
    @DatabaseField(defaultValue = "")
    private String debit_lt;
    @DatabaseField(defaultValue = "")
    private String buy_max;
    @DatabaseField(defaultValue = "")
    private String max_price;
    @DatabaseField(defaultValue = "")
    private String is_trade;
    @DatabaseField(defaultValue = "")
    private String endtime;
    @DatabaseField(defaultValue = "")
    private String sort;
    @DatabaseField(defaultValue = "")
    private String endfee;
    @DatabaseField(defaultValue = "")
    private String invit_sell;
    @DatabaseField(defaultValue = "")
    private String debit_sell_min;
    @DatabaseField(defaultValue = "")
    private String buy_price;
    @DatabaseField(defaultValue = "")
    private String sell_price;
    @DatabaseField(defaultValue = "")
    private String min_price;
    @DatabaseField(defaultValue = "")
    private String sell_max;
    @DatabaseField(defaultValue = "")
    private String sell_min;
    @DatabaseField(defaultValue = "")
    private String is_fee;
    @DatabaseField(defaultValue = "")
    private String tendency;
    @DatabaseField(defaultValue = "")
    private String name;
    @DatabaseField(defaultValue = "")
    private String die;
    @DatabaseField(defaultValue = "")
    private String fee_buy;
    @DatabaseField(defaultValue = "")
    private String endtrade;
    @DatabaseField(defaultValue = "")
    private String trade_max;
    @DatabaseField(defaultValue = "")
    private String fee_sell;
    @DatabaseField(defaultValue = "")
    private String faxingjia;
    @DatabaseField(defaultValue = "")
    private String fee_link;
    @DatabaseField(defaultValue = "")
    private String volume;
    @DatabaseField(defaultValue = "")
    private String jiaoyiqu;
    @DatabaseField(defaultValue = "")
    private String beginfee;
    @DatabaseField(defaultValue = "")
    private String begintrade;
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public String getVolume_day() {
        return volume_day;
    }

    public void setVolume_day(String volume_day) {
        this.volume_day = volume_day;
    }

    @DatabaseField(defaultValue = "")
    private String volume_day;
    public String buyName, sellName, allName;


    @Override
    public void fromJSONAuto(JSONObject jsonObject) {
        super.fromJSONAuto(jsonObject);
        if (sell_name != null) {
            this.sell_name_big = sell_name.toUpperCase();
        }
        try {
            if (name != null) {
                buyName = name.split("_")[0];
                sellName = name.split("_")[1];
                allName = name.replace("_", "/").toUpperCase();
            }
        } catch (Exception e) {
            buyName = "";
            sellName = "";
        }
    }

    public void refreshData(MarketListEntity entity) {
        setNew_price(entity.getNew_price());
        setCny_price(entity.getCny_price());
        setChange(entity.getChange());
        setMax_price(entity.getMax_price());
        setMin_price(entity.getMin_price());
        setVolume(entity.getVolume());
        setVolume_day(entity.getVolume_day());
    }


    public static List<MarketListEntity> fromJSONSocket(String data) {
        List<MarketListEntity> marketListEntities = new ArrayList<MarketListEntity>();
        try {
            JSONObject json = JSONObject.parseObject(data);
            JSONObject market = json.getJSONObject("market");
            JSONObject coin = json.getJSONObject("coin");
            for (Map.Entry<String, Object> entry : market.entrySet()) {
                MarketListEntity marketListEntity = new MarketListEntity();
                marketListEntity.fromJSONAuto((JSONObject) entry.getValue());
                try {
                    String c = coin.getJSONObject(marketListEntity.sellName).getString("usdt_price");
                    String d = marketListEntity.new_price;
                    marketListEntity.cny_price = ToolUtils.multiply(ToolUtils.multiply(c, d), ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE));
                } catch (Exception e) {
                    marketListEntity.cny_price = MAppliaction.getApp().getString(R.string.data_defult);
                }
                marketListEntities.add(marketListEntity);
            }
        } catch (Exception e) {

        }
        return marketListEntities;
    }


    public static void changeByJSONSocket(String data, List<MarketListEntity> marketListEntities) {
        try {
            JSONObject json = JSONObject.parseObject(data);
            JSONObject market = json.getJSONObject("market");
            JSONObject coin = json.getJSONObject("coin");
            for (int i = 0; i < marketListEntities.size(); i++) {
                MarketListEntity marketListEntity = marketListEntities.get(i);
                JSONObject j = market.getJSONObject(marketListEntity.getName());
                if (j != null) {
//                    "new_price":"1.50000000",
//                            "name":"btc_eth",
//                            "change":"50.00",
//                            "max_price":"0.00000000",
//                            "min_price":"0.00000000",
//                            "volume":"1.00000000",
//                            "id":"20",
//                            "jiaoyiqu":"4",
//                            "buy_name":"btc",
//                            "sell_name":"eth",
//                            "volume_day":"0.00000000"
                    marketListEntity.setNew_price(j.getString("new_price"));
                    marketListEntity.setChange(j.getString("change"));
                    marketListEntity.setMax_price(j.getString("max_price"));
                    marketListEntity.setMin_price(j.getString("min_price"));
//                    marketListEntity.setVolume(j.getString("volume"));
                    marketListEntity.setJiaoyiqu(j.getString("jiaoyiqu"));
                    marketListEntity.setBuy_name(j.getString("buy_name"));
                    marketListEntity.setSell_name(j.getString("sell_name"));
                    marketListEntity.setVolume_day(j.getString("volume_day"));
                    marketListEntity.setStatus(j.getString("status"));
                }
            }
//            for (Map.Entry<String, Object> entry : market.entrySet()) {
//                MarketListEntity marketListEntity = new MarketListEntity();
//                marketListEntity.fromJSONAuto((JSONObject) entry.getValue());
//                try {
//                    marketListEntity.cny_price = coin.getJSONObject(marketListEntity.buyName).getString("cny_price");
//                } catch (Exception e) {
//                    marketListEntity.cny_price = MAppliaction.getApp().getString(R.string.data_defult);
//                }
//                marketListEntities.add(marketListEntity);
//            }
        } catch (Exception e) {

        }
    }


    public void setNew_price(String new_price) {
        this.new_price = new_price;
    }

    public String getNew_price() {
        return this.new_price;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getChange() {
        return this.change;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getRound() {
        return this.round;
    }

    public void setHou_price_time(String hou_price_time) {
        this.hou_price_time = hou_price_time;
    }

    public String getHou_price_time() {
        return this.hou_price_time;
    }

    public void setUsdt_price(String usdt_price) {
        this.usdt_price = usdt_price;
    }

    public String getUsdt_price() {
        return this.usdt_price;
    }

    public void setApi_min(String api_min) {
        this.api_min = api_min;
    }

    public String getApi_min() {
        return this.api_min;
    }

    public void setDebit_rate(String debit_rate) {
        this.debit_rate = debit_rate;
    }

    public String getDebit_rate() {
        return this.debit_rate;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getAddtime() {
        return this.addtime;
    }

    public void setTrade_min(String trade_min) {
        this.trade_min = trade_min;
    }

    public String getTrade_min() {
        return this.trade_min;
    }

    public void setZhang(String zhang) {
        this.zhang = zhang;
    }

    public String getZhang() {
        return this.zhang;
    }

    public void setDebit_buy_min(String debit_buy_min) {
        this.debit_buy_min = debit_buy_min;
    }

    public String getDebit_buy_min() {
        return this.debit_buy_min;
    }

    public void setInvit_buy(String invit_buy) {
        this.invit_buy = invit_buy;
    }

    public String getInvit_buy() {
        return this.invit_buy;
    }

    public void setBuy_min(String buy_min) {
        this.buy_min = buy_min;
    }

    public String getBuy_min() {
        return this.buy_min;
    }

    public void setHou_price(String hou_price) {
        this.hou_price = hou_price;
    }

    public String getHou_price() {
        return this.hou_price;
    }

    public void setBuy_name(String buy_name) {
        this.buy_name = buy_name;
    }

    public String getBuy_name() {
        return this.buy_name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDebit_exp_rate(String debit_exp_rate) {
        this.debit_exp_rate = debit_exp_rate;
    }

    public String getDebit_exp_rate() {
        return this.debit_exp_rate;
    }

    public void setInvit_3(String invit_3) {
        this.invit_3 = invit_3;
    }

    public String getInvit_3() {
        return this.invit_3;
    }

    public void setInvit_1(String invit_1) {
        this.invit_1 = invit_1;
    }

    public String getInvit_1() {
        return this.invit_1;
    }

    public void setCny_price(String cny_price) {
        this.cny_price = cny_price;
    }

    public String getCny_price() {
        return this.cny_price;
    }

    public void setInvit_2(String invit_2) {
        this.invit_2 = invit_2;
    }

    public String getInvit_2() {
        return this.invit_2;
    }

    public void setSell_name(String sell_name) {
        this.sell_name = sell_name;
    }

    public String getSell_name() {
        return this.sell_name;
    }

    public void setApi_max(String api_max) {
        this.api_max = api_max;
    }

    public String getApi_max() {
        return this.api_max;
    }

    public void setDebit_lt(String debit_lt) {
        this.debit_lt = debit_lt;
    }

    public String getDebit_lt() {
        return this.debit_lt;
    }

    public void setBuy_max(String buy_max) {
        this.buy_max = buy_max;
    }

    public String getBuy_max() {
        return this.buy_max;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public String getMax_price() {
        return this.max_price;
    }

    public void setIs_trade(String is_trade) {
        this.is_trade = is_trade;
    }

    public String getIs_trade() {
        return this.is_trade;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getEndtime() {
        return this.endtime;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSort() {
        return this.sort;
    }

    public void setEndfee(String endfee) {
        this.endfee = endfee;
    }

    public String getEndfee() {
        return this.endfee;
    }

    public void setInvit_sell(String invit_sell) {
        this.invit_sell = invit_sell;
    }

    public String getInvit_sell() {
        return this.invit_sell;
    }

    public void setDebit_sell_min(String debit_sell_min) {
        this.debit_sell_min = debit_sell_min;
    }

    public String getDebit_sell_min() {
        return this.debit_sell_min;
    }

    public void setBuy_price(String buy_price) {
        this.buy_price = buy_price;
    }

    public String getBuy_price() {
        return this.buy_price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getSell_price() {
        return this.sell_price;
    }

    public void setMin_price(String min_price) {
        this.min_price = min_price;
    }

    public String getMin_price() {
        return this.min_price;
    }

    public void setSell_max(String sell_max) {
        this.sell_max = sell_max;
    }

    public String getSell_max() {
        return this.sell_max;
    }

    public void setSell_min(String sell_min) {
        this.sell_min = sell_min;
    }

    public String getSell_min() {
        return this.sell_min;
    }

    public void setIs_fee(String is_fee) {
        this.is_fee = is_fee;
    }

    public String getIs_fee() {
        return this.is_fee;
    }

    public void setTendency(String tendency) {
        this.tendency = tendency;
    }

    public String getTendency() {
        return this.tendency;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDie(String die) {
        this.die = die;
    }

    public String getDie() {
        return this.die;
    }

    public void setFee_buy(String fee_buy) {
        this.fee_buy = fee_buy;
    }

    public String getFee_buy() {
        return this.fee_buy;
    }

    public void setEndtrade(String endtrade) {
        this.endtrade = endtrade;
    }

    public String getEndtrade() {
        return this.endtrade;
    }

    public void setTrade_max(String trade_max) {
        this.trade_max = trade_max;
    }

    public String getTrade_max() {
        return this.trade_max;
    }

    public void setFee_sell(String fee_sell) {
        this.fee_sell = fee_sell;
    }

    public String getFee_sell() {
        return this.fee_sell;
    }

    public void setFaxingjia(String faxingjia) {
        this.faxingjia = faxingjia;
    }

    public String getFaxingjia() {
        return this.faxingjia;
    }

    public void setFee_link(String fee_link) {
        this.fee_link = fee_link;
    }

    public String getFee_link() {
        return this.fee_link;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setJiaoyiqu(String jiaoyiqu) {
        this.jiaoyiqu = jiaoyiqu;
    }

    public String getJiaoyiqu() {
        return this.jiaoyiqu;
    }

    public void setBeginfee(String beginfee) {
        this.beginfee = beginfee;
    }

    public String getBeginfee() {
        return this.beginfee;
    }

    public void setBegintrade(String begintrade) {
        this.begintrade = begintrade;
    }

    public String getBegintrade() {
        return this.begintrade;
    }

    public String getSell_name_big() {
        return sell_name_big;
    }

    public void setSell_name_big(String sell_name_big) {
        this.sell_name_big = sell_name_big;
    }
}
