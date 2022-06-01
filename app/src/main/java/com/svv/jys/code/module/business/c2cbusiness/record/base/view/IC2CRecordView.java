package com.svv.jys.code.module.business.c2cbusiness.record.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.C2cRecordEntity;

import java.util.List;


/**
 * Created by 74099 on 2018/7/11.
 */

public interface IC2CRecordView extends BaseView {
    /**
     * 获取交易记录成功
     * @param s
     */
    void getC2CRecordListSuccess(List<C2cRecordEntity> s);

    /**
     * 删除成功
     */
    void doCancelSuccess();
}
