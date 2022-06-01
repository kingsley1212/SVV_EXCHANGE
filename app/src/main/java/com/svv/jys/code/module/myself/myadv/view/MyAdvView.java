package com.svv.jys.code.module.myself.myadv.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.AdvListEntity;

/**
 * Created by js on 2018/7/27.
 */

public interface MyAdvView extends BaseView {
    void setAdvList(AdvListEntity list);
    void setStatusSuccese();
}
