package com.svv.jys.code.module.business.tradechat.base.mychat;

import android.util.SparseArray;

import com.alibaba.fastjson.JSONArray;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.Date;

public class MyDataParse {
    private ArrayList<MinutesBean> datas = new ArrayList<>();
    private ArrayList<KLineBean> kDatas = new ArrayList<>();
    private float baseValue;
    private float permaxmin;
    private float volmax;
    private SparseArray<String> dayLabels;
    private int decreasingColor;
    private int increasingColor;
    private String stockExchange;
    private SparseArray<String> xValuesLabel = new SparseArray<>();
    private int firstDay = 10;

//    public void parseMinutes(JSONObject object) {
//        JSONArray jsonArray = object.optJSONObject("data").optJSONObject(code).optJSONObject("data").optJSONArray
// ("data");
//        String date = object.optJSONObject("data").optJSONObject(code).optJSONObject("data").optString("date");
//        if (date.length() == 0) {
//            return;
//        }
///*数据解析依照自己需求来定，如果服务器直接返回百分比数据，则不需要客户端进行计算*/
//        baseValue = (float) object.optJSONObject("data").optJSONObject(code).optJSONObject("qt").optJSONArray(code)
// .optDouble(4);
//        int count = jsonArray.length();
//        for (int i = 0; i < count; i++) {
//            String[] t = jsonArray.optString(i).split(" ");/*  "0930 9.50 4707",*/
//            MinutesBean minutesData = new MinutesBean();
//            minutesData.time = t[0].substring(0, 2) + ":" + t[0].substring(2);
//            minutesData.cjprice = Float.parseFloat(t[1]);
//            if (i != 0) {
//                String[] pre_t = jsonArray.optString(i - 1).split(" ");
//                minutesData.cjnum = Integer.parseInt(t[2]) - Integer.parseInt(pre_t[2]);
//                minutesData.total = minutesData.cjnum * minutesData.cjprice + datas.get(i - 1).total;
//                minutesData.avprice = (minutesData.total) / Integer.parseInt(t[2]);
//            } else {
//                minutesData.cjnum = Integer.parseInt(t[2]);
//                minutesData.avprice = minutesData.cjprice;
//                minutesData.total = minutesData.cjnum * minutesData.cjprice;
//            }
//            minutesData.cha = minutesData.cjprice - baseValue;
//            minutesData.per = (minutesData.cha / baseValue);
//            double cha = minutesData.cjprice - baseValue;
//            if (Math.abs(cha) > permaxmin) {
//                permaxmin = (float) Math.abs(cha);
//            }
//            volmax = Math.max(minutesData.cjnum, volmax);
//            datas.add(minutesData);
//        }
//
//        if (permaxmin == 0) {
//            permaxmin = baseValue * 0.02f;
//        }
//    }

    public void parseKLine(String obj) {
        ArrayList<KLineBean> kLineBeans = new ArrayList<>();
//                JSONArray list = JSONArray.parseArray(JSONObject.parseObject(ConstantTest.KLINEURL).getJSONObject
//         ("data").getJSONObject("sz002081")
//                .getString
//                ("day"));
        JSONArray list = JSONArray.parseArray(obj);
        if (list != null) {
            int count = list.size();
            for (int i = 0; i < count; i++) {

//                JSONArray dayData = list.getJSONArray(i);
//                KLineBean kLineData = new KLineBean();
//                kLineBeans.add(kLineData);
//                kLineData.date = dayData.getString(0);
//                kLineData.open = (float) dayData.getFloat(1);
//                kLineData.close = (float) dayData.getFloat(2);
//                kLineData.high = (float) dayData.getFloat(3);
//                kLineData.low = (float) dayData.getFloat(4);
//                kLineData.vol = (float) dayData.getFloat(5);
//                volmax = Math.max(kLineData.vol, volmax);
//                xValuesLabel.put(i, kLineData.date);

                JSONArray dayData = list.getJSONArray(i);
                KLineBean kLineData = new KLineBean();
                kLineBeans.add(kLineData);
                Date date = new Date(ToolUtils.String2Long(dayData.getString(0)));
                kLineData.date = ToolUtils.date2String(date, "MM-dd HH:mm"); //时间
                kLineData.open = dayData.getFloat(1);//开盘价
                kLineData.high = dayData.getFloat(2);//今日最高价
                kLineData.low = dayData.getFloat(3);//今日最低价
                kLineData.close = dayData.getFloat(4);//收盘价
                kLineData.vol = dayData.getFloat(5);//成交量
                volmax = Math.max(kLineData.vol, volmax);
                xValuesLabel.put(i, kLineData.date);
            }
        }
        kDatas.addAll(kLineBeans);
    }

    public float getMin() {
        return baseValue - permaxmin;
    }

    public float getMax() {
        return baseValue + permaxmin;
    }

    public float getPercentMax() {
        return permaxmin / baseValue;
    }

    public float getPercentMin() {
        return -getPercentMax();
    }


    public float getVolmax() {
        return volmax;
    }


    public ArrayList<MinutesBean> getDatas() {
        return datas;
    }

    public ArrayList<KLineBean> getKLineDatas() {
        return kDatas;
    }

    public SparseArray<String> getXValuesLabel() {
        return xValuesLabel;
    }
}
