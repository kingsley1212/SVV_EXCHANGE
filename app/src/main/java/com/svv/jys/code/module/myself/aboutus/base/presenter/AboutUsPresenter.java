package com.svv.jys.code.module.myself.aboutus.base.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.module.myself.aboutus.base.model.IAboutUsModel;
import com.svv.jys.code.module.myself.aboutus.base.model.impl.AboutUsModelImpl;
import com.svv.jys.code.module.myself.aboutus.base.view.IAboutUsView;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class AboutUsPresenter extends BasePresent<IAboutUsView, IAboutUsModel> {
    public AboutUsPresenter() {
        model = new AboutUsModelImpl();
    }
}
