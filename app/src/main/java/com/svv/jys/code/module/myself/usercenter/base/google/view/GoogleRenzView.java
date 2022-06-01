package com.svv.jys.code.module.myself.usercenter.base.google.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.GoogleInfoEntity;

/**
 * Created by js on 2018/6/22.
 */

public interface GoogleRenzView extends BaseView {
    void setGoogleInfo(GoogleInfoEntity entity);
    void googleRenzSuccese();
}
