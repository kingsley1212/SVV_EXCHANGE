package com.svv.jys.code.module.splash.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.FVersionEntity;
import com.svv.jys.code.common.utils.NetStateUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupVersionSelectView;
import com.svv.jys.code.module.net.req.GET_APPVERSION_REQ;
import com.svv.jys.code.module.splash.model.ISplashModel;
import com.svv.jys.code.module.splash.model.impl.SplashModelImpl;
import com.svv.jys.code.module.splash.view.ISplashView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class SplashPresenter extends BasePresent<ISplashView, ISplashModel> {

    private boolean constraint;
    public SplashPresenter() {
        model = new SplashModelImpl();
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
                        view.stopTimerToMain();
                        judgeVersion(entity);
                    }
                });
    }

    private void judgeVersion(FVersionEntity versionEntity) {
        try {
            if (!versionEntity.versionControl) {
                // ????????????????????????????????????
                delayDirectionMainAct();
                return;
            }
            String versionCode = ToolUtils.isNull(versionEntity.versionCode) ? "" : versionEntity.versionCode;
            String versionChangelog = versionEntity.changeLog;
            final long apkTotalSize = ToolUtils.String2Long(versionEntity.size);//apk????????????
            constraint = versionEntity.constraint;// ??????????????????
            final String installUrl = versionEntity.downloadLink;

            // ????????????????????????app????????????
            String currentVersioncode = "";
            PackageManager manager = view.getMContext().getPackageManager();
            PackageInfo info = null;
            info = manager.getPackageInfo(view.getMContext().getPackageName(), 0);
            if (info == null) {
                //???????????????????????????
                delayDirectionMainAct();
                return;
            } else {
                currentVersioncode = info.versionName + "";
                currentVersioncode = currentVersioncode.trim();
            }

            // ????????????
            if (ToolUtils.String2Float(currentVersioncode) >= ToolUtils.String2Float(versionCode)) {
                //????????????????????????????????????????????????app?????????????????????????????????????????????
                delayDirectionMainAct();
                return;
            }

            // ???????????????????????????????????????????????????????????????????????????
            PopupVersionSelectView popupVersionSelectView = new PopupVersionSelectView((Activity) view.getMContext(), new
                    PopupVersionSelectView.ISelectUpdate() {

                        @Override
                        public void disagreeUpdate() {
                            //?????????????????????
                            view.directToMainAct();
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
            popupVersionSelectView.showPop(view.get_a_view());
            if (constraint) {
                //??????constraint?????????true?????????????????????
                popupVersionSelectView.displayConstrantUpdate();
            }

        } catch (Exception e) {
            // ????????????????????????????????????
            delayDirectionMainAct();
            e.printStackTrace();
        }
    }

    /**
     * ?????????????????????
     */
    private void delayDirectionMainAct(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.directToMainAct();
            }
        }, view.getSurplusTime());
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
                        if(constraint){
                            //????????????????????????????????????app???
                            view.finishActivity();
                        }else{
                            //????????????????????????????????????????????????
                            view.directToMainAct();
                        }
                    }
                });
        wifiBuilder.setMessage(view.getMContext().getResources()
                .getString(R.string.wifitip));
        wifiBuilder.create().show();
    }
}
