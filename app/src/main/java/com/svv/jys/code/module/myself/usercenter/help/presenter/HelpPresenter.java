package com.svv.jys.code.module.myself.usercenter.help.presenter;

import android.content.Intent;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.help.HelpAct;
import com.svv.jys.code.module.myself.usercenter.help.model.IHelpModel;
import com.svv.jys.code.module.myself.usercenter.help.model.impl.HelpModelImpl;
import com.svv.jys.code.module.myself.usercenter.help.view.IHelpView;

/**
 * Created by lzj on 2018/9/20.
 */

public class HelpPresenter extends BasePresent<IHelpView,IHelpModel>{

    public HelpPresenter() {
        this.model = new HelpModelImpl();
    }

    public void getUrl(Intent intent) {
        String url = intent.getStringExtra(HelpAct.URL_KEY);
        if (ToolUtils.isNull(url)) {
            return;
        }
        view.loadUrl(url);
    }

}
