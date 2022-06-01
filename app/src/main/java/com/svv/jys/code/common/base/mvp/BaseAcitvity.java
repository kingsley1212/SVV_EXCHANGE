package com.svv.jys.code.common.base.mvp;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.constant.BROConstant;
import com.svv.jys.code.common.constant.IETConstant;
import com.svv.jys.code.common.constant.NORConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.utils.AppLanguageUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.selfview.TitleTextWindow;
import com.svv.jys.code.module.server.chatserver.act.InChatActivity;


/**
 * Created by hankkin on 2017/3/31.
 */

public abstract class BaseAcitvity extends AppCompatActivity {
    public boolean isShow;
    public boolean styleControl = true;
    public boolean exitControlFlag = true;
    public Bundle savedInstanceState;
    private AlertDialog showNewsMessage;
    public boolean showNewsMessageBool = true;
    private ShowNewsMessageClick showNewsMessageClick = new ShowNewsMessageClick() {
        @Override
        public void doSureClick(String orderid) {
            try {
                Bundle bundle=new Bundle();
                bundle.putString("id",orderid);
                gotoActivity(InChatActivity.class,false,bundle);
//                OrderDetailAct.startOtcOrderDetail(BaseAcitvity.this, orderid);
            } catch (Exception e) {

            }
        }
    };

    public interface ShowNewsMessageClick {
        void doSureClick(String orderid);
    }






    public void setShowNewsMessageClick(ShowNewsMessageClick showNewsMessageClick) {
        this.showNewsMessageClick = showNewsMessageClick;
    }

    private BroadcastReceiver exitReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case BROConstant.EXIT_APP_ACTION:
                    if (exitControlFlag) {
                        finish();
                    }
                    break;
                case BROConstant.CLOSE_TRAGETACT_ACTION:
                    try {
                        if (exitControlFlag) {
                            String act_name = intent.getStringExtra(IETConstant.CLOSE_TRAGETACT_KEY);
                            String act = this.getClass().getSimpleName();
                            if (act_name.contains(act)) {
                                finish();
                            }
                        }
                    } catch (Exception e) {

                    }
                    break;
                case BROConstant.SHOW_NEW_MSG_ACTION:
                    if (isShow && showNewsMessageBool) {
                        if (showNewsMessage == null || !showNewsMessage.isShowing()) {
                            final String orderid = intent.getStringExtra(IETConstant.OTC_ORDER_ID);
                            final String content = intent.getStringExtra("content");

                            if (ToolUtils.isNull(orderid)) {
                                return;
                            }
//                            AlertDialog.Builder builder = new AlertDialog.Builder(BaseAcitvity.this);
//                            showNewsMessage = builder.setMessage(getString(R.string.newmessage_tx))
//                                    .setPositiveButton(getString(R.string.newmessage_tip1), new DialogInterface
//                                            .OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialogInterface, int i) {
//                                            if (showNewsMessageClick != null) {
//                                                showNewsMessageClick.doSureClick(orderid);
//                                            }
//                                        }
//                                    }).setNegativeButton(getString(R.string.newmessage_tip2), new DialogInterface
//                                            .OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialogInterface, int i) {
//                                            showNewsMessage.dismiss();
//                                        }
//                                    }).create();
//                            showNewsMessage.show();
                            TitleTextWindow titleTextWindow=new TitleTextWindow(BaseAcitvity.this,content,orderid);
                            titleTextWindow.show();
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(AppLanguageUtils.attachBaseContext(newBase, ToolUtils.getAppLanguage(newBase)));
    }


    /**
     * 基本初始化工作放在这个方法 如 P类
     */
    public abstract void baseInitialization();

    /**
     * 设置布局文件
     */
    public abstract int setR_Layout();

    /**
     * 控件初始化工作放在这个方法
     */
    public abstract void viewInitialization();

    /**
     * 业务逻辑放在这个方法 如获取网络数据
     */
    public abstract void doBusiness();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        baseInitialization();
        super.onCreate(savedInstanceState);
        if (styleControl) {
            setThemeModel(false);
        }
        registrtExitBro();
        setContentView(setR_Layout());
        viewInitialization();
        this.savedInstanceState=savedInstanceState;
        doBusiness();

    }

    /**
     * 注册退出程序
     */
    protected void registrtExitBro() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROConstant.EXIT_APP_ACTION);
        filter.addAction(BROConstant.CLOSE_TRAGETACT_ACTION);
        filter.addAction(BROConstant.SHOW_NEW_MSG_ACTION);
        registerReceiver(exitReceiver, filter);
    }


    public View $(int resid) {
        return findViewById(resid);
    }

    /**
     * 打开一个Activity 默认 不关闭当前activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null) intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    //返回监听
    public void setBackPress() {
        try {
            View backView = findViewById(R.id.leftImg_ly);
            backView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {

        }
    }


    //设置title
    public void setTitleTx(String title_tx) {
        try {
            TextView title = findViewById(R.id.title);
            title.setText(title_tx);
        } catch (Exception e) {

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(exitReceiver!=null) {
            unregisterReceiver(exitReceiver);
        }
        showNewsMessageClick = null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isShow = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isShow = false;
    }


    public void setThemeModel(boolean isReCreate) {
        String mode = ACache.get(this).getAsString(
                ACEConstant.ACE_THEMEMODE);
        if (ToolUtils.isNull(mode)) {
            mode = NORConstant.DAY_THEME;
        }
        switch (mode) {
            case NORConstant.DAY_THEME:
                setTheme(R.style.ThemeDay);
                break;
            case NORConstant.NIGHT_THEME:
                setTheme(R.style.ThemeNight);
                break;
            default:
                break;
        }
        if (isReCreate) {
            this.recreate();
        }
    }

}
