package com.svv.jys.code.module.myself.apply.view;


import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.ApplyEntity;

/**
 * Created by lzj on 2018/10/9.
 */

public interface IApplyView extends BaseView {

    void setData(ApplyEntity entity);

    void applySuccess();
}
