package com.svv.jys.code.module.myself.myasset.transfer.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.OtcTransferEntity;

import java.util.List;

/**
 * Created by js on 2018/6/7.
 */

public interface TransferRecordView extends BaseView {

    void loadMoreFinish(List<OtcTransferEntity> list);

    void refreshFinish(List<OtcTransferEntity> list);
}
