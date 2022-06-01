package com.svv.jys.code.module.business.tradechat.base3.adapter;

import com.svv.jys.code.module.business.tradechat.base2.entity.IKLine;

/**
 * K线实体
 * Created by tifezh on 2016/5/16.
 */

public class KLineEntity implements IKLine {
    @Override
    public String toString() {
        return "KLineEntity{" +
                "Date='" + Date + '\'' +
                ", Open=" + Open +
                ", High=" + High +
                ", Low=" + Low +
                ", Close=" + Close +
                ", Volume=" + Volume +
                ", MA5Price=" + MA5Price +
                ", MA10Price=" + MA10Price +
                ", MA20Price=" + MA20Price +
                ", dea=" + dea +
                ", dif=" + dif +
                ", macd=" + macd +
                ", k=" + k +
                ", d=" + d +
                ", j=" + j +
                ", rsi1=" + rsi1 +
                ", rsi2=" + rsi2 +
                ", rsi3=" + rsi3 +
                ", up=" + up +
                ", mb=" + mb +
                ", dn=" + dn +
                ", MA5Volume=" + MA5Volume +
                ", MA10Volume=" + MA10Volume +
                '}';
    }

    public String getDatetime() {
        return Date;
    }

    @Override
    public float getOpenPrice() {
        return Open;
    }

    @Override
    public float getHighPrice() {
        return High;
    }

    @Override
    public float getLowPrice() {
        return Low;
    }

    @Override
    public float getClosePrice() {
        return Close;
    }

    @Override
    public float getMA5Price() {
        return MA5Price;
    }

    @Override
    public float getMA10Price() {
        return MA10Price;
    }

    @Override
    public float getMA20Price() {
        return MA20Price;
    }

    @Override
    public float getDea() {
        return dea;
    }

    @Override
    public float getDif() {
        return dif;
    }

    @Override
    public float getMacd() {
        return macd;
    }

    @Override
    public float getK() {
        return k;
    }

    @Override
    public float getD() {
        return d;
    }

    @Override
    public float getJ() {
        return j;
    }

    @Override
    public float getRsi1() {
        return rsi1;
    }

    @Override
    public float getRsi2() {
        return rsi2;
    }

    @Override
    public float getRsi3() {
        return rsi3;
    }

    @Override
    public float getUp() {
        return up;
    }

    @Override
    public float getMb() {
        return mb;
    }

    @Override
    public float getDn() {
        return dn;
    }

    @Override
    public float getVolume() {
        return Volume;
    }

    @Override
    public float getMA5Volume() {
        return MA5Volume;
    }

    @Override
    public float getMA10Volume() {
        return MA10Volume;
    }

    public String Date;
    public float Open;
    public float High;
    public float Low;
    public float Close;
    public float Volume;

    public float MA5Price;

    public float MA10Price;

    public float MA20Price;

    public float dea;

    public float dif;

    public float macd;

    public float k;

    public float d;

    public float j;

    public float rsi1;

    public float rsi2;

    public float rsi3;

    public float up;

    public float mb;

    public float dn;

    public float MA5Volume;

    public float MA10Volume;

}
