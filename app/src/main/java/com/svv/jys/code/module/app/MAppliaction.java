package com.svv.jys.code.module.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import com.svv.jys.code.common.db.orm.DatabaseHelper;
import com.svv.jys.code.common.utils.AppLanguageUtils;
import com.svv.jys.code.common.utils.CachePathUtil;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.server.chatserver.server.ChatService;
import com.svv.jys.code.module.server.dataserver.DataService;
import com.svv.jys.code.module.zxing.DisplayUtil;

import java.io.File;

/**
 * Created by lzj on 2017/8/21.
 */

public class MAppliaction extends MultiDexApplication {


    public static final int UPLOAD_STEP = 100;//多少步上传一次；

    private static MAppliaction mApplication;
    /**
     * 缓存拍照图片路径
     */
    public File takePhotoCacheDir = null;

    /**
     * 获取Application
     */
    public static MAppliaction getApp() {
        return mApplication;
    }

    public boolean socketOnline = false;

    private DatabaseHelper dbinstance;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(AppLanguageUtils.attachBaseContext(base, ToolUtils.getAppLanguage(base)));
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        baseInit();
        baseCrachInit();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(DataService.getIntent(this));
            startForegroundService(ChatService.getIntent(this));
        } else {
            startService(DataService.getIntent(this));//启动数据服务
            startService(ChatService.getIntent(this));//启动聊天服务
        }

    }

    private void baseCrachInit() {
       /* Thread.setDefaultUncaughtExceptionHandler(CrashExpection.getInstance(
                this, CachePathUtil.getCachePathFile("/crash")
                        .getAbsolutePath()));*/
    }


    private void baseInit() {
//        Thread.setDefaultUncaughtExceptionHandler(CrashExpection.getInstance(
//                this, CachePathUtil.getCachePathFile("/crash")
//                        .getAbsolutePath()));
        initTakePhotoFile();
        /**
         * 初始化尺寸工具类
         */
        initDisplayOpinion();

        /**
         //         * 初始化语言
         //         */
        onLanguageChange();
    }
    private void initDisplayOpinion() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        DisplayUtil.density = dm.density;
        DisplayUtil.densityDPI = dm.densityDpi;
        DisplayUtil.screenWidthPx = dm.widthPixels;
        DisplayUtil.screenhightPx = dm.heightPixels;
        DisplayUtil.screenWidthDip = DisplayUtil.px2dip(getApplicationContext(), dm.widthPixels);
        DisplayUtil.screenHightDip = DisplayUtil.px2dip(getApplicationContext(), dm.heightPixels);
    }

    /**
     * 图片存储初始化
     */
    public void initTakePhotoFile() {
        this.takePhotoCacheDir = CachePathUtil.getCachePathFile("/flb/sm_photo");
    }


    /**
     * Handling Configuration Changes
     * @param newConfig newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        onLanguageChange();
    }

    private void onLanguageChange() {
        String language = ToolUtils.getAppLanguage(this);
        AppLanguageUtils.changeAppLanguage(this, AppLanguageUtils.getSupportLanguage(language));
    }


}
