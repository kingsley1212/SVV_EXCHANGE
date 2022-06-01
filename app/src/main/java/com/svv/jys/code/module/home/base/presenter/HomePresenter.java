package com.svv.jys.code.module.home.base.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.IndexEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.home.base.model.IHomeModel;
import com.svv.jys.code.module.home.base.model.impl.HomeModelImpl;
import com.svv.jys.code.module.home.base.view.IHomeView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/28 0028.
 */

public class HomePresenter extends BasePresent<IHomeView, IHomeModel> {
    public HomePresenter() {
        model = new HomeModelImpl();
    }



    public void getIndexData() {
        model.rx_getIndexData().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                IndexEntity>() {
            @Override
            public IndexEntity call(BaseResponse baseResponse) {
                IndexEntity entity = JSON.parseObject(baseResponse.datas,IndexEntity.class);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<IndexEntity>() {
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
            public void onNext(IndexEntity entity) {
                view.setBanner(entity.getBanner());
                view.setmarqueeView(entity.getNotice());
                view.setMarketList(entity.getMarket());
            }
        });
    }




}
