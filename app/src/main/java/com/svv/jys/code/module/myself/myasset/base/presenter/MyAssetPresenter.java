package com.svv.jys.code.module.myself.myasset.base.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.entity.LtAssatEntity;
import com.svv.jys.code.common.entity.OtcAssatEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.base.model.IMyAssetModel;
import com.svv.jys.code.module.myself.myasset.base.model.impl.MyAssetModelImpl;
import com.svv.jys.code.module.myself.myasset.base.view.IMyAssetView;
import com.svv.jys.code.module.net.req.GET_BARBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.GET_COINBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.GET_FBBUSINESS_ASSET_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class MyAssetPresenter extends BasePresent<IMyAssetView, IMyAssetModel> {
    public MyAssetPresenter() {
        model = new MyAssetModelImpl();
    }

    public void getAssatList(int page, String coinname, final boolean isRefresh) {
        GET_COINBUSINESS_ASSET_REQ req = new GET_COINBUSINESS_ASSET_REQ();
        req.coinname = coinname;
        req.limit = "10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_getUserCoin().doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (!isRefresh) {
                    showLoading(view.getMContext());
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                HomeAssatEntity>() {
            @Override
            public HomeAssatEntity call(BaseResponse baseResponse) {
                HomeAssatEntity homeAssatEntity = JSONObject.parseObject(baseResponse.datas, HomeAssatEntity.class);
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

            }

            @Override
            public void onNext(HomeAssatEntity entity) {
                view.setAssatTransaction(entity);
            }
        });
    }

    public void getLtUserCoin(int page, String market, final boolean isRefresh) {
        GET_BARBUSINESS_ASSET_REQ req = new GET_BARBUSINESS_ASSET_REQ();
        req.limit = "10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        req.market = market;
        model.rx_getLtUserCoin(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (!isRefresh) {
                    showLoading(view.getMContext());
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                LtAssatEntity>() {
            @Override
            public LtAssatEntity call(BaseResponse baseResponse) {
                LtAssatEntity ltAssatEntity = JSONObject.parseObject(baseResponse.datas, LtAssatEntity.class);

                return ltAssatEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<LtAssatEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());

            }

            @Override
            public void onNext(LtAssatEntity entity) {
                view.setLtAssat(entity);
            }
        });
    }

    public void getqtcAssatList(int page, String coinname, final boolean isRefresh) {
        GET_FBBUSINESS_ASSET_REQ req = new GET_FBBUSINESS_ASSET_REQ();
        req.coinname = coinname;
        req.limit = "10";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_getqtcUserCoin(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (!isRefresh) {
                    showLoading(view.getMContext());
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                OtcAssatEntity>() {
            @Override
            public OtcAssatEntity call(BaseResponse baseResponse) {
                OtcAssatEntity otcAssatEntity = JSONObject.parseObject(baseResponse.datas, OtcAssatEntity.class);
                return otcAssatEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<OtcAssatEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());

            }

            @Override
            public void onNext(OtcAssatEntity entity) {
                view.setOtcAssat(entity);
            }
        });
    }

}
