package com.svv.jys.code.module.myself.myasset.jiaoyi.rengourecord.view;


import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.RenGouRecordEntity;

/**
 * Created by js on 2018/10/19.
 */

public interface RenGouRecordView extends BaseView {
    void setRenGouRecord(RenGouRecordEntity entity);
    void chexiaoSuccese();
}
