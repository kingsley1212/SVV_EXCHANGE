package com.svv.jys.code.module.myself.myasset.otc.otctransfer.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.OtcCoinEntity;

import java.util.List;

/**
 * Created by js on 2018/6/7.
 */

public interface OtcTransferView extends BaseView {
    void setOtcCoin(List<OtcCoinEntity> list);
    void transferSuccese();
}
