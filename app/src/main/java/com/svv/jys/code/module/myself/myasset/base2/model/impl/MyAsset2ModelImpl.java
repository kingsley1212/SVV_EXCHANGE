package com.svv.jys.code.module.myself.myasset.base2.model.impl;

import com.svv.jys.R;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.myself.myasset.base2.model.IMyAsset2Model;
import com.svv.jys.code.module.myself.myasset.base2.vh.TopAssetTypeVH;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.GET_BARBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.GET_FBBUSINESS_ASSET_REQ;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by lzj on 2018/6/8.
 */

public class MyAsset2ModelImpl implements IMyAsset2Model {
    @Override
    public Observable<BaseResponse> rx_getCoinBusinessAsset() {
        return API_Factory.API_DoGetUserCoin();
    }

    @Override
    public Observable<BaseResponse> rx_getBarBusinessAsset(GET_BARBUSINESS_ASSET_REQ req) {
        return API_Factory.API_GetLtUserCoin(req);
    }

    @Override
    public Observable<BaseResponse> rx_getFbBusinessAsset(GET_FBBUSINESS_ASSET_REQ req) {
        return API_Factory.API_GetQtcUserCoin(req);
    }

    @Override
    public List<TopAssetTypeVH.TopAssetTypeBean> getTopAsset() {
        List<TopAssetTypeVH.TopAssetTypeBean> beans = new ArrayList<>();
        TopAssetTypeVH.TopAssetTypeBean b1 = new TopAssetTypeVH.TopAssetTypeBean();
        b1.typeName = MAppliaction.getApp().getString(R.string.myassetact_tv1);
        b1.unit = "USDT";
        b1.p_amount = "---";
        b1.cny_amount = "---";
        TopAssetTypeVH.TopAssetTypeBean b3 = new TopAssetTypeVH.TopAssetTypeBean();
        b3.typeName = MAppliaction.getApp().getString(R.string.myassetact_tab3);
        b3.unit = "USDT";
        b3.p_amount = "---";
        b3.cny_amount = "---";
        TopAssetTypeVH.TopAssetTypeBean b2 = new TopAssetTypeVH.TopAssetTypeBean();
        b2.typeName = MAppliaction.getApp().getString(R.string.suochagn_zhagnhu);
        b2.unit = "USDT";
        b2.p_amount = "---";
        b2.cny_amount = "---";
        beans.add(b1);
        beans.add(b3);
//        beans.add(b2);
        return beans;
    }

    @Override
    public Observable<BaseResponse> rx_getSuoCangAssetData(GET_FBBUSINESS_ASSET_REQ req) {
        return API_Factory.API_getSuoCangAssetData(req);
    }
}
