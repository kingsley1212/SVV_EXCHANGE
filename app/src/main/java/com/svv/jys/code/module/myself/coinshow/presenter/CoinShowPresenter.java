package com.svv.jys.code.module.myself.coinshow.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CoinShowEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.coinshow.model.ICoinShowModel;
import com.svv.jys.code.module.myself.coinshow.model.impl.CoinShowModelImpl;
import com.svv.jys.code.module.myself.coinshow.view.ICoinShowView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class CoinShowPresenter extends BasePresent<ICoinShowView, ICoinShowModel> {
    public CoinShowPresenter() {
        model = new CoinShowModelImpl();
    }

    public void getCoinList() {
        model.rx_getCoin().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<CoinShowEntity>>() {
            @Override
            public List<CoinShowEntity> call(BaseResponse baseResponse) {
                return JSON.parseArray(baseResponse.datas,CoinShowEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<CoinShowEntity>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
            }

            @Override
            public void onNext(List<CoinShowEntity> list) {
                view.setData(list);
            }
        });
    }
}
