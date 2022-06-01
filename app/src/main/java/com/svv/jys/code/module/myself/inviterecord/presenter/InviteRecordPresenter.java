package com.svv.jys.code.module.myself.inviterecord.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.InviteEntity;
import com.svv.jys.code.common.entity.InviteRecordEntity;
import com.svv.jys.code.common.entity.RewardEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.inviterecord.model.InviteRecordModel;
import com.svv.jys.code.module.myself.inviterecord.model.impl.InviteRecordModelImpl;
import com.svv.jys.code.module.myself.inviterecord.view.InviteRecordView;
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

public class InviteRecordPresenter extends BasePresent<InviteRecordView, InviteRecordModel> {
    public InviteRecordPresenter() {
        model = new InviteRecordModelImpl();
    }

    public void getInviteList(final int offset) {
        PayMethodReq req = new PayMethodReq();
        req.limit = "10";
        req.offset = String.valueOf(offset);
        model.rx_getInviteRecord(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<InviteRecordEntity>>() {
            @Override
            public List<InviteRecordEntity> call(BaseResponse baseResponse) {
                return JSON.parseArray( JSON.parseObject(baseResponse.datas).getString("rows"),InviteRecordEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<InviteRecordEntity>>() {
            @Override

            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);

            }

            @Override
            public void onNext(List<InviteRecordEntity>  list) {
                if(offset>0){
                    view.loadMoreFinishInvite(list);
                }else {
                    view.refreshFinishInvite(list);
                }
            }
        });
    }

    public void getInvite() {
        model.rx_getInvite().doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, InviteEntity>() {
            @Override
            public InviteEntity call(BaseResponse baseResponse) {
                return JSON.parseObject(baseResponse.datas,InviteEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<InviteEntity>() {
            @Override

            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);

            }

            @Override
            public void onNext(InviteEntity  entity) {
              view.setData(entity);
            }
        });
    }

    public void getRewardList(final int offset) {
        PayMethodReq req = new PayMethodReq();
        req.limit = "10";
        req.offset = String.valueOf(offset);
        model.rx_getRewardRecord(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<RewardEntity>>() {
            @Override
            public List<RewardEntity> call(BaseResponse baseResponse) {
                return JSON.parseArray( JSON.parseObject(baseResponse.datas).getString("rows"),RewardEntity.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<RewardEntity>>() {
            @Override

            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);

            }

            @Override
            public void onNext(List<RewardEntity>  list) {
                if(offset>0){
                    view.loadMoreFinishReward(list);
                }else {
                    view.refreshFinishReward(list);
                }
            }
        });
    }

}
