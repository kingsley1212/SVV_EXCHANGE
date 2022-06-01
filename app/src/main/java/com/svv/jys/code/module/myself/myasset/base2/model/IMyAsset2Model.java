package com.svv.jys.code.module.myself.myasset.base2.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.myself.myasset.base2.vh.TopAssetTypeVH;
import com.svv.jys.code.module.net.req.GET_BARBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.GET_FBBUSINESS_ASSET_REQ;

import java.util.List;

import rx.Observable;

/**
 * Created by lzj on 2018/6/8.
 */

public interface IMyAsset2Model {
    //货币交易资产数据
    Observable<BaseResponse> rx_getCoinBusinessAsset();
    //杠杆交易资产数据
    Observable<BaseResponse> rx_getBarBusinessAsset(GET_BARBUSINESS_ASSET_REQ req);
    //法币交易资产数据
    Observable<BaseResponse> rx_getFbBusinessAsset(GET_FBBUSINESS_ASSET_REQ req);

    List<TopAssetTypeVH.TopAssetTypeBean> getTopAsset();


    Observable<BaseResponse> rx_getSuoCangAssetData(GET_FBBUSINESS_ASSET_REQ req);
}
