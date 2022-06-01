package com.svv.jys.code.module.server.dataserver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.net.SSLSocketClient;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.entity.FSocketRes;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.TradeListEntity;
import com.svv.jys.code.common.entity.TransactionEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.app.BaseConfigure;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.business.tradechat.base.mychat.MyDataParse;
import com.svv.jys.code.module.business.tradechat.base2.adapter.DataHelper;
import com.svv.jys.code.module.business.tradechat.base2.adapter.KLineEntity;
import com.svv.jys.code.module.net.u.NET_URL;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;
import com.svv.jys.code.module.server.dataserver.event.TradeJsonEvent;
import com.svv.jys.code.module.server.dataserver.event.TradeListEvent;
import com.svv.jys.code.module.server.dataserver.event.TransactionListEvent;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/1/5.
 */
public class DataService extends Service {

    public static final String TAG = "DataService";
    public String notificationId = "data_service_id";
    public String notificationName = "data_name";
    public static final String METHOD_TRADELIST = "TRADELIST";// 交易页面的系统委托
    public static final String RES_TRADELIST = "TRADELIST";

    public static final String METHOD_MYTRADE = "MYTRADE";//交易页面的当前委托
    public static final String RES_MYTRADE = "MYTRADE";

    public static final String METHOD_MARKETBYAREA = "MARKETBYAREA";//行情页面数据
    public static final String RES_MARKETBYAREA = "MARKETBYAREA";

    public String defult_deptArea = "";

    private WebSocketClient mWebSocketClient;//唯一
    private DataServiceBinder mBinder;
    private int RECONNECT_TIME = 3000;

    private static final Intent SERVICE_INTENT = new Intent();

    static {
        SERVICE_INTENT.setComponent(new ComponentName(BaseConfigure.getInstance().packageName, BaseConfigure
                .getInstance().dataServer_url));
    }

    public static Intent getIntent() {
        return SERVICE_INTENT;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, DataService.class);
//        intent.putExtra(LOGIN_UID, uid);
        return intent;
    }

    public boolean isRunning = false;
    public Handler socketHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            if (!isRunning) {
                initSocket();
            }
            isRunning = false;
            socketHandler.sendMessageDelayed(socketHandler.obtainMessage(), 5 * 1000);
            return false;
        }
    });


    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notificationChannel = new NotificationChannel(notificationId,
                        notificationName, NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.setShowBadge(true);
                notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.createNotificationChannel(notificationChannel);
            }
            Notification notification = new Notification.Builder(this).setChannelId(notificationId)
                    .getNotification();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            startForeground(705682, notification);
        }
        mBinder = new DataServiceBinder();
        socketHandler.sendMessage(socketHandler.obtainMessage());
        Log.i(TAG, " 服务创建");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "服务获取数据启动");
//        if (mWebSocketClient == null) {
//            initSocket();
//        } else {
//            mWebSocketClient.close();
//            initSocket();
//        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // Binder
    public class DataServiceBinder extends Binder {
        public DataService getService() {
            return DataService.this;
        }
    }


    //
    public void initSocket() {
        try {
            URI uri;
            String socketurl = NET_URL.getInstance().getDATA_SERVER(MAppliaction.getApp());
            try {
                uri = new URI(socketurl);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return;
            }
            if (mWebSocketClient != null && mWebSocketClient.isConnecting()) {
                return;
            }
            mWebSocketClient = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.i(TAG, "onOpen: SOCKET 已启动");
                    subscribeAllMarketList();
                    if (!ToolUtils.isNull(defult_deptArea)) {
                        subscribeDept(defult_deptArea);
                    }
                }

                @Override
                public void onMessage(String s) {
                    isRunning = true;
                    FSocketRes res = new FSocketRes();
                    res.fromJSONAuto(s);
                    Log.i(TAG, "onMessage: " + s);

                    switch (res.type) {
                        case "ping":
                            DataService.this.sendPing();
                            break;
                        case "market_list":
                            saveAllMarketList(res.data);
                            break;
                        case "trade_dept":
                            saveDepthList(res.data);
                            break;
                        case "trade_json":
                            saveTradeJson2(res.data);
                            break;
                        case "trade":
                            JsonToTrade(res.data);
                            break;
                        case "trade_log":
                            saveLogList(res.data);
                            break;
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.i(TAG, "onClose: SOCKET 已关闭");
                }

                @Override
                public void onError(Exception e) {
                    Log.i(TAG, "onClose: SOCKET 已关闭");
                }
            };

            //信任所有证书
            try {
                if(socketurl.contains("wss:")||socketurl.contains("https:")) {
                    mWebSocketClient.setSocket(SSLSocketClient.getSSLSocketFactory().createSocket());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mWebSocketClient.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public interface GetMarketByAreaResponse {
//        void getMarketByAreaRes();
//    }
//    private Map<String, GetMarketByAreaResponse> getMarketByAreaResponseMap = new HashMap<>();
//
//    public void getMarketByArea(final String area, final String tag, final GetMarketByAreaResponse getMarketByArea) {
//        if (mWebSocketClient != null && mSocket.connected()) {
//            Observable.just(area).observeOn(Schedulers.io()).map(new Func1<String, Boolean>() {
//                @Override
//                public Boolean call(String area) {
//                    try {
//                        mSocket.emit(METHOD_MARKETBYAREA, area);
//                        getMarketByAreaResponseMap.put(tag, getMarketByArea);
//                        return true;
//                    } catch (Exception e) {
//                        return false;
//                    }
//                }
//            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
//                @Override
//                public void call(Boolean flag) {
//                }
//            });
//        }
//    }


    public void sendPing() {
        try {
            if (mWebSocketClient != null) {
                mWebSocketClient.send("ping");
            }
        } catch (Exception e) {

        }
    }


    //订阅所有市场数据
    public void subscribeAllMarketList() {
        try {
            if (mWebSocketClient != null) {
                JSONObject json = new JSONObject();
                json.put("event", "addChannel");
                json.put("channel", "market_list");
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }

    //市场数据转化数据
    public void saveAllMarketList(final String data) {
        Observable.just(data).observeOn(Schedulers.io()).map(new Func1<String, List<MarketListEntity>>() {
            @Override
            public List<MarketListEntity> call(String data) {
                try {
                    List<MarketListEntity> marketListEntities = MarketListEntity.fromJSONSocket(data);
                    return marketListEntities;
                } catch (Exception e) {
                    return new ArrayList<MarketListEntity>();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<MarketListEntity>>() {
            @Override
            public void call(List<MarketListEntity> list) {
                MarketListEvent marketListEvent = new MarketListEvent(data, list);
                EventBus.getDefault().post(marketListEvent);
            }
        });


    }

    //市场数据转化数据
    public void saveDepthList(String data) {
        Observable.just(data).observeOn(Schedulers.io()).map(new Func1<String, TradeListEntity>() {
            @Override
            public TradeListEntity call(String data) {
                try {
                    TradeListEntity tradeListEntity = new TradeListEntity();
                    tradeListEntity.fromJSONAuto(data);
                    return tradeListEntity;
                } catch (Exception e) {
                    return new TradeListEntity();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<TradeListEntity>() {
            @Override
            public void call(TradeListEntity tradeListEntity) {
                TradeListEvent tradeListEvent = new TradeListEvent(tradeListEntity);
                EventBus.getDefault().post(tradeListEvent);
            }
        });


    }

    //市场数据转化数据
    public void saveLogList(String data) {
        Observable.just(data).observeOn(Schedulers.io()).map(new Func1<String,  List<TransactionEntity>>() {
            @Override
            public  List<TransactionEntity> call(String data) {
                try {
                    List<TransactionEntity> transactionEntities = JSON.parseArray(data, TransactionEntity.class);

                    return transactionEntities;
                } catch (Exception e) {
                    return new ArrayList<>();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1< List<TransactionEntity>>() {
            @Override
            public void call( List<TransactionEntity> list) {
                TransactionListEvent event = new TransactionListEvent(list);
                EventBus.getDefault().post(event);
            }
        });


    }

    //订阅委托
    public void subscribeEntrust (String uid,String market) {
        try {
            if (mWebSocketClient != null) {
                if (ToolUtils.isNull(uid)||ToolUtils.isNull(market)) {
                    return;
                }
                JSONObject json = new JSONObject();
                json.put("event", "trade");
                json.put("market",market);
                json.put("uid",uid);
                json.put("status", "0");
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }

    //订阅是深度信息
    public void subscribeDept(String area) {
        try {
            if (mWebSocketClient != null) {
                if (ToolUtils.isNull(area)) {
                    return;
                }
                defult_deptArea = area;
                JSONObject json = new JSONObject();
                json.put("event", "addChannel");
                json.put("channel", "trade_dept:" + area);
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }

    //取消是定于深度信息
    public void leaveDept(String area) {
        try {
            if (mWebSocketClient != null) {
                if (ToolUtils.isNull(area)) {
                    return;
                }
                defult_deptArea = "";
                JSONObject json = new JSONObject();
                json.put("event", "leaveChannel");
                json.put("channel", "trade_dept:" + area);
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }

    //订阅是深度信息
    public void subscribeLog(String area) {
        try {
            if (mWebSocketClient != null) {
                if (ToolUtils.isNull(area)) {
                    return;
                }
                defult_deptArea = area;
                JSONObject json = new JSONObject();
                json.put("event", "addChannel");
                json.put("channel", "trade_log:" + area);
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }

    //取消是定于深度信息
    public void leaveLog(String area) {
        try {
            if (mWebSocketClient != null) {
                if (ToolUtils.isNull(area)) {
                    return;
                }
                defult_deptArea = "";
                JSONObject json = new JSONObject();
                json.put("event", "leaveChannel");
                json.put("channel", "trade_log:" + area);
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }

    public void leaveKLineTrade(String market, String time) {
        try {
            if (mWebSocketClient != null) {
                if (ToolUtils.isNull(market)) {
                    return;
                }
                JSONObject json = new JSONObject();
                json.put("event", "leaveChannel");
                json.put("channel", "trade_json:" + market + ":" + time);
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }


    //订阅K线图数据
    public void subscribeKLineTrade(String market, String time) {
        try {
            if (mWebSocketClient != null) {
                if (ToolUtils.isNull(market)) {
                    return;
                }
                JSONObject json = new JSONObject();
                json.put("event", "addChannel");
                json.put("channel", "trade_json:" + market + ":" + time);
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }

    //K线图数据转化数据
    public void saveTradeJson(String data) {
        Observable.just(data).observeOn(Schedulers.io()).map(new Func1<String, MyDataParse>() {
            @Override
            public MyDataParse call(String data) {
                try {
                    MyDataParse dataParse = new MyDataParse();
                    dataParse.parseKLine(data);
                    return dataParse;
                } catch (Exception e) {
                    return new MyDataParse();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<MyDataParse>() {
            @Override
            public void call(MyDataParse dataParse) {
                TradeJsonEvent tradeJsonEvent = new TradeJsonEvent(dataParse);
                EventBus.getDefault().post(tradeJsonEvent);
            }
        });
    }


    //K线图数据转化数据
    public void saveTradeJson2(String data) {
        Observable.just(data).observeOn(Schedulers.io()).map(new Func1<String, List<KLineEntity>>() {
            @Override
            public List<KLineEntity> call(String data) {
                List<KLineEntity> kLineBeans = new ArrayList<>();
                List<KLineEntity> skLineBeans = new ArrayList<>();
                try {
                    JSONArray list = JSONArray.parseArray(data);
                    if (list != null) {
                        int count = list.size();
                        for (int i = 0; i < count; i++) {
                            JSONArray dayData = list.getJSONArray(i);
                            KLineEntity kLineData = new KLineEntity();
                            kLineBeans.add(kLineData);
                            Date date = new Date(ToolUtils.String2Long(dayData.getString(0)));
                            kLineData.Date = ToolUtils.date2String(date, "yyyy/MM/dd"); //时间
                            kLineData.Open = dayData.getFloat(1);//开盘价
                            kLineData.High = dayData.getFloat(2);//今日最高价
                            kLineData.Low = dayData.getFloat(3);//今日最低价
                            kLineData.Close = dayData.getFloat(4);//收盘价
                            kLineData.Volume = dayData.getFloat(5);//成交量
                        }
                    }

                    Log.e("TAG", kLineBeans.toString());
                    skLineBeans.addAll(kLineBeans);
                    DataHelper.calculate(skLineBeans);
                    return skLineBeans;
                } catch (Exception e) {
                    return skLineBeans;
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<KLineEntity>>() {
            @Override
            public void call(List<KLineEntity> kLineEntities) {
                TradeJsonEvent tradeJsonEvent = new TradeJsonEvent(kLineEntities);
                EventBus.getDefault().post(tradeJsonEvent);
            }
        });
    }

    //委托
    public void JsonToTrade(String data) {
        Observable.just(data).observeOn(Schedulers.io()).map(new Func1<String, EntrustEntity>() {
            @Override
            public EntrustEntity call(String data) {
                EntrustEntity entrustEntity=new EntrustEntity();
                List<EntrustEntity.RowsBean> rowsBeans = new ArrayList<>();
                try {
                    rowsBeans = JSON.parseArray(JSON.parseObject(data).getString("list"),EntrustEntity.RowsBean.class);
                    entrustEntity.setRows(rowsBeans);
                    EntrustEntity.socketEntrust(data,entrustEntity);
                    return entrustEntity;
                } catch (Exception e) {
                    return entrustEntity;
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<EntrustEntity>() {
            @Override
            public void call( EntrustEntity entities) {
//                EntrustEvent event = new EntrustEvent(entities);
                EventBus.getDefault().post(entities);
            }
        });
    }
}
