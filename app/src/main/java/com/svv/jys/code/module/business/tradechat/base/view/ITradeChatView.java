package com.svv.jys.code.module.business.tradechat.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.module.business.tradechat.base.mychat.MyDataParse;

/**
 * Created by lzj on 2018/5/30.
 */

public interface ITradeChatView extends BaseView {
    void setKLineData(MyDataParse mData);

    void setMarket();
}
