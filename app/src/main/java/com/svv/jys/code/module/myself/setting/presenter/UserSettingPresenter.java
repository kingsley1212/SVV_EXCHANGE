package com.svv.jys.code.module.myself.setting.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.FVersionEntity;
import com.svv.jys.code.common.utils.NetStateUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupVersionSelectView;
import com.svv.jys.code.module.myself.setting.UserSettingAct;
import com.svv.jys.code.module.myself.setting.model.IUserSettingModel;
import com.svv.jys.code.module.myself.setting.model.impl.UserSettingModelModelImpl;
import com.svv.jys.code.module.myself.setting.view.IUserSettingView;
import com.svv.jys.code.module.net.req.GET_APPVERSION_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UserSettingPresenter extends BasePresent<IUserSettingView, IUserSettingModel> {
    public UserSettingPresenter() {
        model = new UserSettingModelModelImpl();
    }

    public void Logout(){
        model.rx_logout()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {

                return baseResponse.datas;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                view.getUserInfoComplete();
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
                view.getUserInfoComplete();
            }

            @Override
            public void onNext(String s) {
                dismissLoading(view.getMContext());
                view.successLoginout();
            }
        });
    }
    public void getAppVersionData() {
        GET_APPVERSION_REQ req = new GET_APPVERSION_REQ();
        model.rx_getAppVersionData(req)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, FVersionEntity>() {
            @Override
            public FVersionEntity call(BaseResponse baseResponse) {
                FVersionEntity entity = new FVersionEntity();
                entity.fromJSONAuto(baseResponse.datas);
                entity.versionCode = entity.versionName;
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FVersionEntity>() {
                    @Override
                    public void onCompleted() {
                        dismissLoading(view.getMContext());
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                    }

                    @Override
                    public void onNext(final FVersionEntity entity) {
                        judgeVersion(entity);
                    }
                });
    }

    private void judgeVersion(FVersionEntity versionEntity) {
        try {

            String versionCode = ToolUtils.isNull(versionEntity.versionCode) ? "" : versionEntity.versionCode;
            String versionChangelog = versionEntity.changeLog;
            final String installUrl = versionEntity.downloadLink;

            // 得到当前已安装的app版本信息
            String currentVersioncode = "";
            PackageManager manager = view.getMContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(view.getMContext().getPackageName(), 0);
            currentVersioncode = info.versionName + "";
            currentVersioncode = currentVersioncode.trim();


            // 比较两者
            if (ToolUtils.String2Float(currentVersioncode) == ToolUtils.String2Float(versionCode)) {
                //若从服务器得到的版本号等于当前版app版本号，则不弹出更新，直接进入
                T.showShort(view.getMContext(),view.getMContext().getResources().getString(R.string.new_version));
                return;
            }

            // 当从服务器得到的版本号不等于当前版本号，则展示弹框
            PopupVersionSelectView popupVersionSelectView = new PopupVersionSelectView((Activity) view.getMContext(), new
                    PopupVersionSelectView.ISelectUpdate() {

                        @Override
                        public void disagreeUpdate() {
                            //“否”直接进入
//                            gotoActivity(MainAct.class, true);
                        }

                        @Override
                        public void agreeUpdate() {
                            if (NetStateUtils.isWifi(view.getMContext())) {
                                view.downloadApp(installUrl);
                            } else {
                                readyDownload(installUrl);
                            }

                        }
                    });
            popupVersionSelectView.setContentMsg(versionChangelog);
            popupVersionSelectView.showPop(((UserSettingAct)(view.getMContext())).ll_version);

        } catch (Exception e) {
            // 若不控制更新，则直接进入
            e.printStackTrace();
        }
    }


    private void readyDownload(final String installUrl) {
        final AlertDialog.Builder wifiBuilder = new AlertDialog.Builder(
                view.getMContext());
        wifiBuilder.setPositiveButton(
                view.getMContext().getResources().getString(
                        R.string.yes),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            DialogInterface dialog,
                            int which) {
                        view.downloadApp(installUrl);
                    }
                });
        wifiBuilder.setNegativeButton(
                view.getMContext().getResources().getString(
                        R.string.no),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(
                            DialogInterface dialog,
                            int which) {
                        wifiBuilder.create().dismiss();
                    }
                });
        wifiBuilder.setMessage(view.getMContext().getResources()
                .getString(R.string.wifitip));
        wifiBuilder.create().show();
    }
}
