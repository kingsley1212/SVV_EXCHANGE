package com.svv.jys.code.module.myself.myasset.otc.otctrading.orderdetail.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.OrderDetailEntity;

/**
 * Created by js on 2018/6/11.
 */

public interface OrderDetailView extends BaseView {
    void setStatusSuccese();
    void shensuSuccese();
    void getOrderDetail(OrderDetailEntity rowsBean);
}
