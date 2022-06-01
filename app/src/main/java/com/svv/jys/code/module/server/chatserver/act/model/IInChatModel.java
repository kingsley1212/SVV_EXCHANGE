package com.svv.jys.code.module.server.chatserver.act.model;

import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.POST_CHATFILE_REQ;

import rx.Observable;

/**
 * Created by lzj on 2018/1/5.
 */

public interface IInChatModel {
    /**
     * 上传聊天媒体文件
     * @param req
     * @return
     */
    Observable<BaseResponse> rx_postChatFile(POST_CHATFILE_REQ req);

    Observable<BaseResponse> rx_getOrderInfo(AdvInfoReq req);
}
