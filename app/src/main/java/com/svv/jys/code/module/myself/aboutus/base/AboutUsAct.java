package com.svv.jys.code.module.myself.aboutus.base;

import android.content.Context;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.module.myself.aboutus.base.model.IAboutUsModel;
import com.svv.jys.code.module.myself.aboutus.base.presenter.AboutUsPresenter;
import com.svv.jys.code.module.myself.aboutus.base.view.IAboutUsView;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class AboutUsAct extends MvpActivity<IAboutUsView, IAboutUsModel, AboutUsPresenter> implements IAboutUsView {
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public AboutUsPresenter initPresenter() {
        return new AboutUsPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_aboutus;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.aboutus_title));

    }

    @Override
    public void doBusiness() {

    }
}
