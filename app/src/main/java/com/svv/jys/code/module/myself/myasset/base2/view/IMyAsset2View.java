package com.svv.jys.code.module.myself.myasset.base2.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.module.myself.myasset.base2.vh.TopAssetTypeVH;

import java.util.List;

/**
 * Created by lzj on 2018/6/8.
 */

public interface IMyAsset2View extends BaseView{

    void setTopAsset(List<TopAssetTypeVH.TopAssetTypeBean> topAsset);
    //展示资产账户
    void setCoinBusinessAsset(HomeAssatEntity entity);
    //展示法币账户
    void setFBBusinessAsset(HomeAssatEntity entity);

    void setSuoCangAsset(HomeAssatEntity entity);

}
