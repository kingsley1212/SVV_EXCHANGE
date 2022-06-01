package com.svv.jys.code.module.fabi.screen.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.module.fabi.screen.model.ScreenModel;
import com.svv.jys.code.module.fabi.screen.model.impl.ScreenModelImpl;
import com.svv.jys.code.module.fabi.screen.view.ScreenView;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;

/**
 * Created by js on 2018/5/17.
 */

public class ScreenPresenter extends BasePresent<ScreenView, ScreenModel> {
    public AdvSettingEntity entity;
    public GET_OTC_ADV_REQ req;
    public ScreenPresenter() {
        model = new ScreenModelImpl();
    }


}
