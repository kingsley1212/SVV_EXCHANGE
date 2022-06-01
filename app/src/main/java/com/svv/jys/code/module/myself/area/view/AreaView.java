package com.svv.jys.code.module.myself.area.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CountryEntity;

import java.util.List;

/**
 * Created by js on 2018/9/8.
 */

public interface AreaView extends BaseView {
    void setCountry(List<CountryEntity> list);
}
