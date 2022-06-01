package com.svv.jys.code.module.myself.ordermanage.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface IOrderManagerView extends BaseView {
    void setData(List<String> list);

}
