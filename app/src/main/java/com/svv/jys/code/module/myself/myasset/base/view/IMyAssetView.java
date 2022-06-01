package com.svv.jys.code.module.myself.myasset.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.entity.LtAssatEntity;
import com.svv.jys.code.common.entity.OtcAssatEntity;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface IMyAssetView extends BaseView {

    void setAssatTransaction(HomeAssatEntity entity);
    void setLtAssat(LtAssatEntity entity);
    void setOtcAssat(OtcAssatEntity otcAssat);
}
