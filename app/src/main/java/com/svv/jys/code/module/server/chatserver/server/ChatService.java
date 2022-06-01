package com.svv.jys.code.module.server.chatserver.server;

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
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.R;
import com.svv.jys.code.common.base.net.SSLSocketClient;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.constant.BROConstant;
import com.svv.jys.code.common.constant.IETConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.FSocketRes;
import com.svv.jys.code.module.app.BaseConfigure;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.net.u.NET_URL;
import com.svv.jys.code.module.server.chatserver.bean.FChatMessageEntity;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/1/5.
 */
public class ChatService extends Service {

    public static final String TAG = "ChatService";
    public String notificationId = "chat_service_id";
    public String notificationName = "chat_name";
    private WebSocketClient mWebSocketClient;//唯一
    private ChatServiceBinder mBinder;

    private static final Intent SERVICE_INTENT = new Intent();

    static {
        SERVICE_INTENT.setComponent(new ComponentName(BaseConfigure.getInstance().packageName, BaseConfigure
                .getInstance().chatServer_url));
    }

    public static Intent getIntent() {
        return SERVICE_INTENT;
    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, ChatService.class);
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
            socketHandler.sendMessageDelayed(socketHandler.obtainMessage(), 15 * 1000);
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
            startForeground(856974, notification);
        }
        mBinder = new ChatServiceBinder();
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
        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // Binder
    public class ChatServiceBinder extends Binder {
        public ChatService getService() {
            return ChatService.this;
        }
    }


    //
    public void initSocket() {
        try {
            URI uri;
            String socketurl = NET_URL.getInstance().getCHAT_SERVER(MAppliaction.getApp());
            try {
                uri = new URI(NET_URL.getInstance().getCHAT_SERVER(MAppliaction.getApp()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return;
            }
            if (mWebSocketClient != null && mWebSocketClient.isConnecting()) {
//                sendPing();
                return;
            }
//            if (mWebSocketClient != null) {
//                sendPing();
//                return;
//            }
            mWebSocketClient = new WebSocketClient(uri) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.i(TAG, "onOpen: SOCKET 已启动");
                    subscribeChat();
                }

                @Override
                public void onMessage(String s) {
                    isRunning = true;
                    FSocketRes res = new FSocketRes();
                    res.fromJSONAuto(s);
                    Log.i(TAG, "onMessage: " + s);
                    if ("ping".equals(res.type)) {
                        ChatService.this.sendPing();
                    } else if (!TextUtils.isEmpty(res.type)) {
                        saveMsg(res.data);
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.i(TAG, "onClose: SOCKET 已关闭");
                }

                @Override
                public void onError(Exception e) {
                    Log.i(TAG, "onError: SOCKET 已关闭");
                }
            };
            //信任所有证书
            try {

                if (socketurl.contains("wss:") || socketurl.contains("https:")) {
                    mWebSocketClient.setSocket(SSLSocketClient.getSSLSocketFactory().createSocket());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            mWebSocketClient.connect();
//            connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接websocket
     */
    private void connect() {
        new Thread() {
            @Override
            public void run() {
                try {
                    //connectBlocking多出一个等待操作，会先连接再发送，否则未连接发送会报错
                    mWebSocketClient.connectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

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

    public void subscribeChat() {
        try {
            if (mWebSocketClient != null) {
                JSONObject json = new JSONObject();
                json.put("event", "addUid");
                json.put("uid", ACache.get(this).getAsString(ACEConstant.ACE_USERINFO_USERID));
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }


    public void unbindChat() {
        try {
            if (mWebSocketClient != null) {
                JSONObject json = new JSONObject();
                json.put("event", "unbindUid");
                json.put("uid", ACache.get(this).getAsString(ACEConstant.ACE_USERINFO_USERID));
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }


    public void getMsg(String oid) {
        try {
            if (mWebSocketClient != null) {
                JSONObject json = new JSONObject();
                json.put("event", "getMsg");
                json.put("oid", oid);
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }

    public void saveMsg(String data) {
        Observable.just(data).observeOn(Schedulers.io()).map(new Func1<String, List<FChatMessageEntity>>() {
            @Override
            public List<FChatMessageEntity> call(String data) {
                try {
                    JSONArray jsonArray = new JSONArray(data);
                    return FChatMessageEntity.from_JARRAY(jsonArray);
                } catch (Exception e) {
                    return new ArrayList<>();
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<FChatMessageEntity>>() {
            @Override
            public void call(List<FChatMessageEntity> list) {
                EventBus.getDefault().post(list);
                if (list.size() != 0) {
                    sendBroadcast(new Intent(BROConstant.REFRESH_UNREADMESSAGE_ACTION));//通知页面刷新
                    Intent newmsg = new Intent(BROConstant.SHOW_NEW_MSG_ACTION);
                    newmsg.putExtra(IETConstant.OTC_ORDER_ID, list.get(0).order_id);
                    newmsg.putExtra("content", list.get(0).media_type == 1 ? list.get(0).content : getString(R.string.chat_img));
                    sendBroadcast(newmsg);//新消息弹框通知
                }

            }
        });
    }

    public void sendMsg(String uid, String tid, String content, String oid) {
        try {
            if (mWebSocketClient != null) {
                JSONObject json = new JSONObject();
                json.put("event", "sendMsg");
                json.put("uid", uid);
                json.put("tid", tid);
                json.put("content", content);
                json.put("oid", oid);
                mWebSocketClient.send(json.toJSONString());
            }
        } catch (Exception e) {

        }
    }


    //    -------------------------------------websocket心跳检测------------------------------------------------
    private static final long HEART_BEAT_RATE = 10 * 1000;//每隔10秒进行一次对长连接的心跳检测
    private Handler mHandler = new Handler();

    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            Log.e("JWebSocketClientService", "心跳包检测websocket连接状态");
            if (mWebSocketClient != null) {
                if (mWebSocketClient.isClosed()) {
                    reconnectWs();
                }
            } else {
                //如果client已为空，重新初始化连接
                mWebSocketClient = null;
                initSocket();
            }
            //每隔一定的时间，对长连接进行一次心跳检测
            mHandler.postDelayed(this, HEART_BEAT_RATE);
        }
    };

    /**
     * 开启重连
     */
    private void reconnectWs() {
        mHandler.removeCallbacks(heartBeatRunnable);
        new Thread() {
            @Override
            public void run() {
                try {
                    Log.e("JWebSocketClientService", "开启重连");
                    mWebSocketClient.reconnectBlocking();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        closeConnect();
        super.onDestroy();
    }

    /**
     * 断开连接
     */
    private void closeConnect() {
        try {
            if (null != mWebSocketClient) {
                mWebSocketClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mWebSocketClient = null;
        }
    }


}
