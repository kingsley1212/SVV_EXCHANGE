package com.svv.jys.code.module.app;

import android.content.Context;

/**
 * Created by lzj on 2018/8/23.
 */

public class BaseConfigure {

    private static BaseConfigure baseConfigure;

    public String packageName;
    public String dataServer_url;
    public String chatServer_url;
    //文件过滤器路径
    public String fileProvider_url;

    public BaseConfigure(Context mContext) {
        this.packageName = mContext.getPackageName();
        this.dataServer_url = packageName + ".code.module.server.dataserver.DataService";
        this.chatServer_url = packageName + ".code.module.server.chatserver.server.ChatService";
        this.fileProvider_url = packageName + ".fileprovider";
    }

    public static BaseConfigure getInstance() {
        if (baseConfigure == null) {
            baseConfigure = new BaseConfigure(MAppliaction.getApp());
        }
        return baseConfigure;
    }

}
