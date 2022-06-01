package com.svv.jys.code.module.myself.myasset.transfer.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.OtcTransferEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.transfer.model.TransferRecordModel;
import com.svv.jys.code.module.myself.myasset.transfer.model.impl.TransferRecordModelImpl;
import com.svv.jys.code.module.myself.myasset.transfer.view.TransferRecordView;
import com.svv.jys.code.module.net.req.PayMethodReq;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/7.
 */

public class TransferRecordPresenter extends BasePresent<TransferRecordView, TransferRecordModel> {
    public TransferRecordPresenter() {
        model = new TransferRecordModelImpl();
    }

    public void getOtcList(final int offset) {
        PayMethodReq req = new PayMethodReq();
        req.limit = "10";
        req.offset = String.valueOf(offset);
        model.rx_getTransferRecord(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<OtcTransferEntity> >() {
            @Override
            public List<OtcTransferEntity> call(BaseResponse baseResponse) {
               return JSON.parseArray( JSON.parseObject(baseResponse.datas).getString("rows"),OtcTransferEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<OtcTransferEntity>>() {
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
            public void onNext(List<OtcTransferEntity>  list) {
                if(offset>0){
                    view.loadMoreFinish(list);
                }else {
                    view.refreshFinish(list);
                }
            }
        });
    }

}
