package com.svv.jys.code.module.server.chatserver.act.presenter;


import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseMulitRequest;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.ChatOrderInfoRntity;
import com.svv.jys.code.common.utils.BitmapUtil;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.POST_CHATFILE_REQ;
import com.svv.jys.code.module.server.chatserver.act.model.IInChatModel;
import com.svv.jys.code.module.server.chatserver.act.model.impl.InChatModelImpl;
import com.svv.jys.code.module.server.chatserver.act.view.IInChatView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/1/5.
 */

public class InChatPresenter extends BasePresent<IInChatView, IInChatModel> {

    public InChatPresenter() {
        this.model = new InChatModelImpl();
    }


    public void compressAndcommitImg(File file) {
        List<File> list = new ArrayList<>();
        list.add(file);

        BitmapUtil.compressFiles(list, new BitmapUtil.CompressImageResponse() {
            @Override
            public void onSuccess(List<File> imgs) {
                dismissLoading(view.getMContext());
                File imgFile = imgs.get(0);
                commitUserImg(imgFile);
            }

            @Override
            public void onDo() {
//                showLoading(view.getMContext());
            }

            @Override
            public void onFail() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onFinish() {
                dismissLoading(view.getMContext());
            }
        });
    }

    private void commitUserImg(File imgFile) {
        POST_CHATFILE_REQ req = new POST_CHATFILE_REQ();
        req.baseMulitRequests = new ArrayList<>();
        req.baseMulitRequests.add(new BaseMulitRequest("file", imgFile, "image/jpg"));
        model.rx_postChatFile(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                JSONObject json = JSONObject.parseObject(baseResponse.datas);
                return json.getString("src");
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(String s) {
                dismissLoading(view.getMContext());
                view.sendFileMessage(s);
            }
        });
    }

    public void getOrderDetail(String id) {
        if (ToolUtils.isNull(id)) {
            T.showShort(view.getMContext(),"无法查找该订单");
            return;
        }
        AdvInfoReq req = new AdvInfoReq();
        req.id = id;
        model.rx_getOrderInfo(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, ChatOrderInfoRntity>() {
            @Override
            public ChatOrderInfoRntity call(BaseResponse baseResponse) {
                ChatOrderInfoRntity rowsBean = JSONObject.parseObject(baseResponse.datas, ChatOrderInfoRntity.class);
                return rowsBean;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ChatOrderInfoRntity>() {
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
            public void onNext(ChatOrderInfoRntity s) {
                view.setOrderDetail(s);
            }
        });
    }

}
