package com.svv.jys.code.module.myself.base.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.entity.NewIdentifyEntity;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public interface IMySelfView extends BaseView {

    void getUserInfoComplete();

    void setUsetData(FUserInfoEntity usetData);

    void loadUserImg(String imgUrl);

    void successLoginout();

    void getidentifyInfo( NewIdentifyEntity entity);
}
