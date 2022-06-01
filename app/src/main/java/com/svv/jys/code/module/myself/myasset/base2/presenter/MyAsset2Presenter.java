package com.svv.jys.code.module.myself.myasset.base2.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.base2.model.IMyAsset2Model;
import com.svv.jys.code.module.myself.myasset.base2.model.impl.MyAsset2ModelImpl;
import com.svv.jys.code.module.myself.myasset.base2.vh.TopAssetTypeVH;
import com.svv.jys.code.module.myself.myasset.base2.view.IMyAsset2View;
import com.svv.jys.code.module.net.req.GET_FBBUSINESS_ASSET_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/6/8.
 */

public class MyAsset2Presenter extends BasePresent<IMyAsset2View, IMyAsset2Model> {


    public List<TopAssetTypeVH.TopAssetTypeBean> topAsset;

    public MyAsset2Presenter() {
        this.model = new MyAsset2ModelImpl();
    }


    public void getTopAsset() {
        this.topAsset = model.getTopAsset();
        view.setTopAsset(topAsset);
    }

    public void getCoinBusinessAssetData() {

        model.rx_getCoinBusinessAsset().doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                HomeAssatEntity>() {
            @Override
            public HomeAssatEntity call(BaseResponse baseResponse) {
                HomeAssatEntity homeAssatEntity = new HomeAssatEntity();
                homeAssatEntity.fromJSONAuto(baseResponse.datas);
                return homeAssatEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeAssatEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
                view.setCoinBusinessAsset(null);
            }

            @Override
            public void onNext(HomeAssatEntity entity) {
                view.setCoinBusinessAsset(entity);
            }
        });
    }



    public void getFbBusinessAssetData() {
        GET_FBBUSINESS_ASSET_REQ req = new GET_FBBUSINESS_ASSET_REQ();

        model.rx_getFbBusinessAsset(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                HomeAssatEntity>() {
            @Override
            public HomeAssatEntity call(BaseResponse baseResponse) {
                HomeAssatEntity otcAssatEntity = new HomeAssatEntity();
                otcAssatEntity.fromJSONAuto(baseResponse.datas);
                return otcAssatEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeAssatEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
                view.setFBBusinessAsset(null);
            }

            @Override
            public void onNext(HomeAssatEntity entity) {
                view.setFBBusinessAsset(entity);
            }
        });
    }
    public void getSuoCangAssetData() {
        GET_FBBUSINESS_ASSET_REQ req = new GET_FBBUSINESS_ASSET_REQ();

        model.rx_getSuoCangAssetData(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                HomeAssatEntity>() {
            @Override
            public HomeAssatEntity call(BaseResponse baseResponse) {
                HomeAssatEntity otcAssatEntity = new HomeAssatEntity();
                otcAssatEntity.fromJSONAuto(baseResponse.datas);
                return otcAssatEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<HomeAssatEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
                view.setFBBusinessAsset(null);
            }

            @Override
            public void onNext(HomeAssatEntity entity) {
                view.setSuoCangAsset(entity);
            }
        });
    }

}
