package com.svv.jys.code.common.entity;

import android.support.annotation.NonNull;

import com.svv.jys.code.common.base.BaseEntity;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public class TradeListEntity extends BaseEntity {

    public static final String nil = "---";

    private List<Trade> sell;

    private List<Trade> buy;

    public void setSell(List<Trade> sell) {
        this.sell = sell;
    }

    public List<Trade> getSell() {
        List<Trade> trades = new ArrayList<>();
        for (Trade t : sell) {
            trades.add(t);
        }
        return trades;
    }

    public void setBuy(List<Trade> buy) {
        this.buy = buy;
    }

    public List<Trade> getBuy() {
        List<Trade> trades = new ArrayList<>();
        for (Trade t : buy) {
            trades.add(t);
        }
        return trades;
    }

    public static class Trade extends BaseEntity implements Comparable<Trade> {
        public String id;

        public String num;

        public String percent;

        public String price;

        public String floats;

        public String ints;

        public void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getNum() {
            return this.num;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }

        public String getPercent() {
            return this.percent;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice() {
            return this.price;
        }

        public void setFloats(String floats) {
            this.floats = floats;
        }

        public String getFloats() {
            return this.floats;
        }

        public void setInts(String ints) {
            this.ints = ints;
        }

        public String getInts() {
            return this.ints;
        }

        @Override
        public int compareTo(@NonNull Trade trade) {
            double my = ToolUtils.String2Double(price);
            double other = ToolUtils.String2Double(trade.price);
            if (my > other) {
                return 1;
            } else if (my == other) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
