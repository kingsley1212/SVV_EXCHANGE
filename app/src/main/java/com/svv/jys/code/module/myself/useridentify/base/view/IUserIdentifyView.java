package com.svv.jys.code.module.myself.useridentify.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.IndentityImgEntity;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface IUserIdentifyView extends BaseView {
    void identifySuccese();
    void successUpImg(int type,IndentityImgEntity entity);
    void successRemove();
}
