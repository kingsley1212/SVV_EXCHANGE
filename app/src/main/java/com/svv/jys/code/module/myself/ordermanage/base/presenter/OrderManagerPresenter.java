package com.svv.jys.code.module.myself.ordermanage.base.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.ordermanage.base.model.IOrderManagerModel;
import com.svv.jys.code.module.myself.ordermanage.base.model.impl.OrderManagerModelImpl;
import com.svv.jys.code.module.myself.ordermanage.base.view.IOrderManagerView;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class OrderManagerPresenter extends BasePresent<IOrderManagerView, IOrderManagerModel> {
    public OrderManagerPresenter() {
        model = new OrderManagerModelImpl();
    }
    public void getExchange(){
        model.rx_getExchange().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<String>>() {
            @Override
            public List<String> call(BaseResponse baseResponse) {
                return JSON.parseArray(baseResponse.datas,String.class);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToolUtils.doNetErroMsg(view.getMContext(), e, true, false);
                    }

                    @Override
                    public void onNext(List<String> list) {
                        view.setData(list);
                    }
                });
    }
}
