package com.svv.jys.code.module.myself.subscription.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.SubEntity;
import com.svv.jys.code.common.entity.SubListEntity;
import com.svv.jys.code.common.entity.SubscriptionEntity;

import java.util.List;

public interface SubscriptionView extends BaseView {
    void setSubscriptionInfo(SubscriptionEntity entity);
    String getRvSize();
    void loading(List<SubListEntity.RowsBean> rows);

    void refresh(List<SubListEntity.RowsBean> rows);
    void succeseSub();

    void subInfo(SubEntity entity);
}
