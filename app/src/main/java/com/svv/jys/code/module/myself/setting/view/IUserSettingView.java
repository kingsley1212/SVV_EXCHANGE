package com.svv.jys.code.module.myself.setting.view;

import com.svv.jys.code.common.base.mvp.BaseView;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public interface IUserSettingView extends BaseView {
    void getUserInfoComplete();

    void successLoginout();
    void downloadApp(String url);
}
