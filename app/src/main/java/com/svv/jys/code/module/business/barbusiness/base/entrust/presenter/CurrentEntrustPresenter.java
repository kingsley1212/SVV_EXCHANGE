package com.svv.jys.code.module.business.barbusiness.base.entrust.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.module.business.barbusiness.base.entrust.model.ICurrentEntrustModel;
import com.svv.jys.code.module.business.barbusiness.base.entrust.model.ipml.CurrentEntrustModelImpl;
import com.svv.jys.code.module.business.barbusiness.base.entrust.view.ICurrentEntrustView;

/**
 * Created by js on 2018/5/10.
 */

public class CurrentEntrustPresenter extends BasePresent<ICurrentEntrustView,ICurrentEntrustModel>{
    public CurrentEntrustPresenter(){
        model=new CurrentEntrustModelImpl();
    }
}
