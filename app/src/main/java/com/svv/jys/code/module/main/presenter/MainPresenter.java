package com.svv.jys.code.module.main.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.main.model.IMainModel;
import com.svv.jys.code.module.main.model.impl.MainModelImpl;
import com.svv.jys.code.module.main.view.IMainView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class MainPresenter extends BasePresent<IMainView, IMainModel> {
    public MainPresenter() {
        model = new MainModelImpl();
    }

    /**
     * 显示是否为测试系统
     */
    public void isCeshi(){
        model.rx_isCeshi().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                return baseResponse.datas;


            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
//                view.isCeshi("true");
            }

            @Override
            public void onNext(String s) {
                view.isCeshi(s);
            }
        });
    }
}
