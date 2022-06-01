package com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.sell.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.AdvInfoEntity;
import com.svv.jys.code.common.entity.AdvSettingEntity;

/**
 * Created by lzj on 2018/7/24.
 */

public interface IOtcFabuSellView extends BaseView {
    void setAdvSetting(AdvSettingEntity entity);
    void setYijia(String s);
    void publishSuccese();
    void setAdvInfo(AdvInfoEntity advInfoEntity);
}
