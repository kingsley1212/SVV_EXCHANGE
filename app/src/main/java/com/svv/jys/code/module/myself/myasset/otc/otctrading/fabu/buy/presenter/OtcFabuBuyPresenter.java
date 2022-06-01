package com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.buy.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.buy.model.IOtcFabuBuyModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.buy.model.impl.OtcFabuBuyModelImpl;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fabu.buy.view.IOtcFabuBuyView;

/**
 * Created by lzj on 2018/7/24.
 */

public class OtcFabuBuyPresenter extends BasePresent<IOtcFabuBuyView,IOtcFabuBuyModel> {

    public OtcFabuBuyPresenter() {
        this.model = new OtcFabuBuyModelImpl();
    }
}
