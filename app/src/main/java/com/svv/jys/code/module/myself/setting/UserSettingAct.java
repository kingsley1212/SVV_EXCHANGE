package com.svv.jys.code.module.myself.setting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpChatSocketActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.utils.AppLanguageUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.common.view.popup.PopupNewSelectorView;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.main.MainAct;
import com.svv.jys.code.module.myself.coinshow.CoinShowAct;
import com.svv.jys.code.module.myself.login.userlogin.UserLoginAct;
import com.svv.jys.code.module.myself.setting.model.IUserSettingModel;
import com.svv.jys.code.module.myself.setting.presenter.UserSettingPresenter;
import com.svv.jys.code.module.myself.setting.view.IUserSettingView;
import com.svv.jys.code.module.net.exception.NeedLoginException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UserSettingAct extends MvpChatSocketActivity<IUserSettingView, IUserSettingModel, UserSettingPresenter> implements
        IUserSettingView {
    private TextView language_tv, price_way_tv, tv_version;
    private List<String> langs;
    private PopupNewSelectorView popup;
    public LinearLayout ll_version;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public UserSettingPresenter initPresenter() {
        return new UserSettingPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_usersetting;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.setting));
        language_tv = (TextView) $(R.id.language_tv);
        price_way_tv = (TextView) $(R.id.price_way_tv);
        ll_version = findViewById(R.id.ll_version);
        tv_version = findViewById(R.id.tv_version);
        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            String currentVersioncode = info.versionName;
            tv_version.setText(currentVersioncode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        ll_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getAppVersionData();
            }
        });
        langs = new ArrayList<>();
        langs.add("简体中文");
        langs.add("繁體中文");
        langs.add("English");
        $(R.id.language_ly).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLanguangeType();
            }
        });
        switch (ACache.get(this).getAsString(ACEConstant.CURRENTLANGUAGE_ID)) {
            case ACEConstant.LANGUAGE_SIMPLIFIED_CHINESE:
                language_tv.setText(langs.get(0));
                break;
            case ACEConstant.LANGUAGE_TRADITIONAL_CHINESE:
                language_tv.setText(langs.get(1));
                break;
            case ACEConstant.LANGUAGE_ENGLISH:
                language_tv.setText(langs.get(2));
                break;
        }

        $(R.id.logout_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupDialogView popupView = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                    @Override
                    public void leftBtn() {

                    }

                    @Override
                    public void rightBtn() {
                        presenter.Logout();
                        mChatService.unbindChat();
                    }
                });
                popupView.showPop(view, getString(R.string.mysele_out), getString(R.string.myselffragment_conten), getString
                        (R.string
                                .myselffragment_left_text), getString(R.string.myselffragment_right_text));
            }
        });

        $(R.id.currency_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(CoinShowAct.class);
            }
        });


    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        price_way_tv.setText(ToolUtils.getCurrency(getMContext()));
        try {
            ToolUtils.checkLogin(getMContext(), false);
        } catch (NeedLoginException e) {
            $(R.id.logout_tv).setVisibility(View.GONE);
        }
    }


    /**
     * 更改应用语言
     *
     * @param newLanguage
     */
    public void changeAppLanguage(String newLanguage) {
        ACache.get(this).put(ACEConstant.CURRENTLANGUAGE_ID, newLanguage);
        AppLanguageUtils.changeAppLanguage(this, newLanguage);
        AppLanguageUtils.changeAppLanguage(MAppliaction.getApp(), newLanguage);
        //重新启动Activity
        Intent intent = new Intent(this, MainAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * 切换语言
     */
    private void showLanguangeType() {
        if (popup == null) {
            popup = new PopupNewSelectorView(getMContext(), langs);
            popup.setDa(true);
            popup.setValue(language_tv.getText().toString());
            popup.setOnClickItem(new PopupNewSelectorView.OnClickItem() {
                @Override
                public void OnSelect(int position, String value) {
                    switch (position) {
                        case 0:
                            changeAppLanguage(ACEConstant.LANGUAGE_SIMPLIFIED_CHINESE);
                            break;
                        case 1:
                            changeAppLanguage(ACEConstant.LANGUAGE_TRADITIONAL_CHINESE);
                            break;
                        case 2:
                            changeAppLanguage(ACEConstant.LANGUAGE_ENGLISH);
                            break;
                    }
                }
            });
        }
        popup.showPop(language_tv);

    }


    @Override
    public void getUserInfoComplete() {

    }

    @Override
    public void successLoginout() {
        ToolUtils.logout(getMContext());
        gotoActivity(UserLoginAct.class);
    }

    @Override
    public void downloadApp(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void cSConttectSeccuss() {

    }

    @Override
    public void cSConttectFail() {

    }
}
