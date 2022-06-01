package com.svv.jys.code.common.base.mvp;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;


/**
 * Created by lzj on 2018/5/22.
 */

public abstract class MvpDataAndChatSocketActivity<V extends BaseView, M, P extends BasePresent<V, M>> extends
        MvpDataSocketActivity<V
        , M, P> {

//    public ChatService mChatService;
    private boolean mChatServiceBind;

    public abstract void chatConttectSeccuss();

    public abstract void chatConttectFail();

    private ServiceConnection chatServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
//            mChatService = null;
            chatConttectFail();
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            mChatService = ((ChatService.ChatServiceBinder) service).getService();
//            chatConttectSeccuss();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        try {
//            mChatServiceBind = bindService(ChatService.getIntent(), chatServiceConnection, Context.BIND_AUTO_CREATE);
//        } catch (Exception e) {
//            mChatServiceBind = false;
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mChatServiceBind) {
            unbindService(chatServiceConnection);
        }
    }
}
