package com.svv.jys.code.module.myself.myasset.countdetail.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.LtBorrowRecord;
import com.svv.jys.code.common.entity.LtRecordEntity;
import com.svv.jys.code.common.entity.LtTransferRecord;
import com.svv.jys.code.common.entity.LtUserCoinInfoentity;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public interface ICountDetailView extends BaseView {
    void setLtRecord(LtRecordEntity entity);
    void getLtUserinfo(LtUserCoinInfoentity entity);
    void setLtTransferRecord(LtTransferRecord entity);
    void setborrowRecord(LtBorrowRecord entity);
}
