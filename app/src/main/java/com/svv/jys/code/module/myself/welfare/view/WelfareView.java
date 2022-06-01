package com.svv.jys.code.module.myself.welfare.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.WelfareEntity;

import java.util.List;

public interface WelfareView extends BaseView {
    void setWelfare(List<WelfareEntity.RowsBean> list);
}
