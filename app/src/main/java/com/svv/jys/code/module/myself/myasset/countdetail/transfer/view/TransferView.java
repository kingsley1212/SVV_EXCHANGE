package com.svv.jys.code.module.myself.myasset.countdetail.transfer.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;
import com.svv.jys.code.common.entity.UserCoinInfo;

/**
 * Created by js on 2018/5/25.
 */

public interface TransferView extends BaseView {
    void transferSuccess();
    void setUserCoinInfo(UserCoinInfo userCoinInfo);
    void getLtUserinfo(LtUserCoinInfoentity entity);
}
