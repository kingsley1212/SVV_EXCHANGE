package com.svv.jys.code.module.fabi.base.view;


import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.AdvSettingEntity;
import com.svv.jys.code.common.entity.OtcAdvEntity;

import java.util.List;

/**
 * Created by js on 2018/8/1.
 */

public interface FabiView extends BaseView {
    void isPublish(String s);

    void setData(AdvSettingEntity entity);

    void setOtcAdv(List<OtcAdvEntity.RowsBean> rows, int position, boolean isLoadMore);

}
