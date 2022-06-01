package com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.IndentityImgEntity;

public interface AddZfbOrWxView extends BaseView {
    void succese(IndentityImgEntity entity);
    void addSuccese();
}
