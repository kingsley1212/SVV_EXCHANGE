package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.BusinessInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.model.UserInfoModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.model.inpl.UserInfoModelImpl;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.view.UserInfoView;
import com.svv.jys.code.module.net.req.BusinessInfoReq;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/6/20.
 */

public class UserInfoPresenter extends BasePresent<UserInfoView,UserInfoModel>{
    public UserInfoPresenter(){
        model=new UserInfoModelImpl();
    }
    public void getBusinessinfo(String uid){
        BusinessInfoReq req=new BusinessInfoReq();
        req.user_id=uid;
        model.rx_queryBusinessInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, BusinessInfoEntity>() {
            @Override
            public BusinessInfoEntity call(BaseResponse baseResponse) {
                BusinessInfoEntity entity= JSONObject.parseObject(baseResponse.datas,BusinessInfoEntity.class);
                Log.e("TAG",baseResponse.datas);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<BusinessInfoEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(BusinessInfoEntity s) {
                view.setBusinessInfo(s);
            }
        });
    }

    public void getBusinessList(String uid, final int offset, String type){
        BusinessInfoReq req=new BusinessInfoReq();
        req.user_id=uid;
        req.offset = String.valueOf(offset);
        req.type = type;
        req.limit = "10";
        model.rx_getBusinessList(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<BusinessInfoEntity.RowsBean>>() {
            @Override
            public List<BusinessInfoEntity.RowsBean> call(BaseResponse baseResponse) {
                return  JSON.parseArray(JSON.parseObject(baseResponse.datas).getString("rows"),BusinessInfoEntity.RowsBean.class);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<BusinessInfoEntity.RowsBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(List<BusinessInfoEntity.RowsBean> list) {
                boolean isLoadMore = offset>0;
                view.setListData(list,isLoadMore);
            }
        });
    }

}
