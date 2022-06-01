package com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.presenter;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.EntrustEntity;
import com.svv.jys.code.common.entity.MarketOrederEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.model.EntrustModel;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.model.impl.EntrustModelImpl;
import com.svv.jys.code.module.myself.ordermanage.base.entrustfragment.view.EntrustView;
import com.svv.jys.code.module.net.req.GET_ENTRUST_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;
import com.svv.jys.code.module.net.req.POST_UNDO_ENTRUST_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/5/24.
 */

public class EntrustPresenter extends BasePresent<EntrustView,EntrustModel>{
    public EntrustPresenter(){
        model=new EntrustModelImpl();
    }
    public void getEntrust(int page,String status,String market,String type){
        GET_ENTRUST_REQ req=new GET_ENTRUST_REQ();
        req.limit = "10";
        req.status=status;
        req.market=market;
        req.type=type;
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(page - 1));
        model.rx_entrust(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse, EntrustEntity>() {
            @Override
            public EntrustEntity call(BaseResponse baseResponse) {
                EntrustEntity entrustEntity= JSONObject.parseObject(baseResponse.datas,EntrustEntity.class);
                return entrustEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<EntrustEntity>() {
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
            public void onNext(EntrustEntity entity) {
                view.setEntrustList(entity);
            }
        });
    }
    public void undoEntrust(String id){
        POST_UNDO_ENTRUST_REQ req=new POST_UNDO_ENTRUST_REQ();
        req.id=id;
        model.rx_undo_entrust(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {

                return baseResponse.msg;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
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
            public void onNext(String s) {
                T.showShort(view.getMContext(),s);
                view.undoSuccess();
            }
        });
    }

    public void getMarket() {
        POST_KONG_REQ req = new POST_KONG_REQ();
        model.rx_getMarketTitle(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<MarketOrederEntity>>() {
            @Override
            public List<MarketOrederEntity> call(BaseResponse baseResponse) {
                List<MarketOrederEntity> list = JSONObject.parseArray(baseResponse.datas, MarketOrederEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MarketOrederEntity>>() {
            @Override
            public void onCompleted() {
//                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());

            }

            @Override
            public void onNext(List<MarketOrederEntity> marketTitleEntities) {
               view.setMarket(marketTitleEntities);
            }
        });
    }
}
