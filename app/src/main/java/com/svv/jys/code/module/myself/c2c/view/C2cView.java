package com.svv.jys.code.module.myself.c2c.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.C2CIndexEntity;
import com.svv.jys.code.common.entity.C2CListEntity;

import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public interface C2cView extends BaseView{

    void setIndexData(C2CIndexEntity entity);

    String getRvSize();

    void loading(List<C2CListEntity.RowsBean> rows);

    void refresh(List<C2CListEntity.RowsBean> rows);
    void buyorSellSuccese();
}
