package com.svv.jys.code.module.myself.login.base.view;


import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.entity.FToken;

import java.util.List;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public interface IRegisterView extends BaseView {

    String getPhone();

    String getPsw();

    String getrePsw();

    String getcode();

    String getArea();

    String getCountry();

    void finishActivity();

    void successCode();

    void registerSuccese();

    String getCodeTyoe();

    String getEmail();
    void setCountry(List<CountryEntity> list);

    void doLoginInSocket(FToken token);

    String getYaoQingMa();
}
