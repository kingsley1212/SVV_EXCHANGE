package com.svv.jys.code.module.myself.coinshow.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CoinShowEntity;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface ICoinShowView extends BaseView {
    void setData(List<CoinShowEntity> list);
}
