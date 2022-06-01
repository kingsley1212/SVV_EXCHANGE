package com.svv.jys.code.module.splash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.module.main.MainAct;
import com.svv.jys.code.module.splash.model.ISplashModel;
import com.svv.jys.code.module.splash.presenter.SplashPresenter;
import com.svv.jys.code.module.splash.view.ISplashView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class SplashAct extends MvpActivity<ISplashView, ISplashModel, SplashPresenter> implements ISplashView {
    private RelativeLayout splash_layout;
    //    private ImageView toMainAct;
    private TextView countDown;

    private Timer timer;
    private TimerTask timerTask;

    private int countDownNum = 5;
    private boolean hasDone, stopTimerTaskToMainAct;


    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    @Override
    public void baseInitialization() {

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        countDown.setText(countDownNum + "");
                        if (countDownNum < 0) {
                            timer.cancel();
                            countDown.setVisibility(View.INVISIBLE);
                            if (!stopTimerTaskToMainAct) {
                                directToMainAct();
                            }
                        }
                    }
                });
                countDownNum--;
            }
        };
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_splash;
    }

    @Override
    public void viewInitialization() {
        splash_layout = (RelativeLayout) $(R.id.splash_layout);
        countDown = (TextView) $(R.id.countDown);
    }

    @Override
    public void doBusiness() {
        presenter.getAppVersionData();
        startAnimation();
    }

    private void startAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        splash_layout.setAnimation(alphaAnimation);
        alphaAnimation.setDuration(3000);
        alphaAnimation.start();
        timer.schedule(timerTask, 0, 1000);
    }


    @Override
    public void onBackPressed() {
        timer.cancel();
        super.onBackPressed();
    }

    @Override
    public void directToMainAct() {
        if (!hasDone) {
            hasDone = true;
            gotoActivity(MainAct.class, true);
        }
    }

    @Override
    public View get_a_view() {
        return countDown;
    }

    @Override
    public void downloadApp(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(url));
        startActivity(intent);
        T.showLong(this, getResources().getString(R.string.splashact_select_explore));
        /*final PopupDownloadView downloadView = new PopupDownloadView(this);
        downloadView.showPop(get_a_view());

        DownloadUtil.getInstance().download(url, Environment.getExternalStorageDirectory().getAbsolutePath(), new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(String path) {
                final File file = new File(path);
                downloadView.downLoadSuccessSetMsg(getString(R.string.app_download_path), new PopupDownloadView.DoAction() {
                    @Override
                    public void openPath() {
                        if (PermissionUtils.checkAlbumStroagePermission(getMContext())) {
                            if (file != null && file.exists()) {
                                ToolUtils.openFile(getMContext(), file);
                            } else {
                                downloadView.dismiss();
                                finish();
                            }
                        }
                        downloadView.dismiss();
                        finish();
                    }
                });
            }

            @Override
            public void onDownloading(int progress) {
//                T.showShort(getMContext(), "进度：" + progress);
                downloadView.setContentMsg(progress);
            }

            @Override
            public void onDownloadFailed() {
                downloadView.dismiss();
                T.showShort(getMContext(), getString(R.string.app_download_error));
            }
        });*/
    }

    @Override
    public void stopTimerToMain() {
        stopTimerTaskToMainAct = true;
    }

    @Override
    public int getSurplusTime() {
        return countDownNum * 1000;
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
