package com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.view;


import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.SuoCangLiXiEntity;

/**
 * Created by js on 2018/8/10.
 */

public interface SuoCangView extends BaseView {
    void suoCangSucese();

    void showLiXi(SuoCangLiXiEntity entity);

}
