package com.svv.jys.code.module.business.tradechat.introduction.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CoinIntroduceEntity;

/**
 * Created by js on 2018/5/19.
 */

public interface IntroductionView extends BaseView {

    void setCoinInfo(CoinIntroduceEntity entity);
}
