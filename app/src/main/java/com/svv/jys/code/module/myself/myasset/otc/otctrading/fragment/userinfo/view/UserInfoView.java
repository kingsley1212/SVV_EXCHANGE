package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.BusinessInfoEntity;

import java.util.List;

/**
 * Created by js on 2018/6/20.
 */

public interface UserInfoView extends BaseView {
    void setBusinessInfo(BusinessInfoEntity entity);

    void setListData(List<BusinessInfoEntity.RowsBean> list, boolean isLoadMore);
}
