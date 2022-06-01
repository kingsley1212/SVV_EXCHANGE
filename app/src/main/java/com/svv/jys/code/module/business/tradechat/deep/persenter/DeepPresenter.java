package com.svv.jys.code.module.business.tradechat.deep.persenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.module.business.tradechat.deep.model.DeepModel;
import com.svv.jys.code.module.business.tradechat.deep.model.impl.DeepModelImpl;
import com.svv.jys.code.module.business.tradechat.deep.view.DeepView;

/**
 * Created by js on 2018/5/19.
 */

public class DeepPresenter extends BasePresent<DeepView, DeepModel> {
    public DeepPresenter() {
        model = new DeepModelImpl();
    }


}
