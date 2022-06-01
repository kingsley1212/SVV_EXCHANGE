package com.svv.jys.code.common.base.mvp;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.svv.jys.code.module.server.chatserver.server.ChatService;

/**
 * Created by lzj on 2018/5/22.
 */

public abstract class MvpChatSocketActivity<V extends BaseView, M, P extends BasePresent<V, M>> extends MvpActivity<V
        , M, P> {

    public ChatService mChatService;
    private boolean mDataServiceBind;

    public abstract void cSConttectSeccuss();

    public abstract void cSConttectFail();

    private ServiceConnection coreServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mChatService = null;
            cSConttectFail();
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mChatService = ((ChatService.ChatServiceBinder) service).getService();
            cSConttectSeccuss();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataServiceBind = bindService(ChatService.getIntent(), coreServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDataServiceBind) {
            unbindService(coreServiceConnection);
        }
    }
}
