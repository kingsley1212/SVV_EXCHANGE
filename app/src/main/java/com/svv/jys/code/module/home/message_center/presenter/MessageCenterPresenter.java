package com.svv.jys.code.module.home.message_center.presenter;

import com.alibaba.fastjson.JSON;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.MessageCenterEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.home.message_center.model.MessageCenterModel;
import com.svv.jys.code.module.home.message_center.model.impl.MessageCenterModelImpl;
import com.svv.jys.code.module.home.message_center.view.MessageCenterView;
import com.svv.jys.code.module.net.req.POST_NOTICE_REQ;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MessageCenterPresenter extends BasePresent<MessageCenterView, MessageCenterModel> {
    public MessageCenterPresenter() {
        model = new MessageCenterModelImpl();
    }

    public void getData(Integer currentPage, final Boolean isShow) {
        POST_NOTICE_REQ req = new POST_NOTICE_REQ();
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(currentPage - 1));
        model.rx_getMessageCenterData(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (isShow) {
                    showLoading(view.getMContext());
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<MessageCenterEntity>>() {
            @Override
            public List<MessageCenterEntity> call(BaseResponse baseResponse) {
                List<MessageCenterEntity> list = JSON.parseArray(JSON.parseObject(baseResponse.datas).get("rows").toString(), MessageCenterEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MessageCenterEntity>>() {
            @Override
            public void onCompleted() {
                if (isShow) {
                    dismissLoading(view.getMContext());
                }
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                if (isShow) {
                    dismissLoading(view.getMContext());
                }

            }

            @Override
            public void onNext(List<MessageCenterEntity> list) {
                view.setData(list);
            }
        });
    }
    public void getAboutData(Integer currentPage, final Boolean isShow) {
        POST_NOTICE_REQ req = new POST_NOTICE_REQ();
        req.cid = "7";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(currentPage - 1));
        model.rx_getArticleData(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (isShow) {
                    showLoading(view.getMContext());
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<MessageCenterEntity>>() {
            @Override
            public List<MessageCenterEntity> call(BaseResponse baseResponse) {
                List<MessageCenterEntity> list = JSON.parseArray(JSON.parseObject(baseResponse.datas).get("rows").toString(), MessageCenterEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MessageCenterEntity>>() {
            @Override
            public void onCompleted() {
                if (isShow) {
                    dismissLoading(view.getMContext());
                }
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                if (isShow) {
                    dismissLoading(view.getMContext());
                }

            }

            @Override
            public void onNext(List<MessageCenterEntity> list) {
                view.setData(list);
            }
        });
    }

    public void getQuestionData(Integer currentPage, final Boolean isShow) {
        POST_NOTICE_REQ req = new POST_NOTICE_REQ();
        req.cid = "6";
        req.offset = ToolUtils.multiply(req.limit, String.valueOf(currentPage - 1));
        model.rx_getArticleData(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                if (isShow) {
                    showLoading(view.getMContext());
                }
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.io()).map(new Func1<BaseResponse,
                List<MessageCenterEntity>>() {
            @Override
            public List<MessageCenterEntity> call(BaseResponse baseResponse) {
                List<MessageCenterEntity> list = JSON.parseArray(JSON.parseObject(baseResponse.datas).get("rows").toString(), MessageCenterEntity.class);
                return list;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<MessageCenterEntity>>() {
            @Override
            public void onCompleted() {
                if (isShow) {
                    dismissLoading(view.getMContext());
                }
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                if (isShow) {
                    dismissLoading(view.getMContext());
                }

            }

            @Override
            public void onNext(List<MessageCenterEntity> list) {
                view.setData(list);
            }
        });
    }
}
