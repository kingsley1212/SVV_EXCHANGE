package com.svv.jys.code.module.myself.usercenter.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.NewIdentifyEntity;
import com.svv.jys.code.common.entity.VerifyEntity;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface IUserCenterView extends BaseView {
    void setVerifyData(VerifyEntity entity);

    void getidentifyInfo(NewIdentifyEntity newIdentifyEntity);
}
