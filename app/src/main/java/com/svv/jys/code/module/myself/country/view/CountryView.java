package com.svv.jys.code.module.myself.country.view;


import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CountryEntity;

import java.util.List;

/**
 * Created by LB on 2018/3/2.
 */

public interface CountryView extends BaseView {
    void setData(List<CountryEntity> list);
}
