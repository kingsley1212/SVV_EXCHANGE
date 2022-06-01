package com.svv.jys.code.module.server.chatserver.act.view;


import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.ChatOrderInfoRntity;

/**
 * Created by lzj on 2018/1/5.
 */

public interface IInChatView extends BaseView {
    void sendFileMessage(String s);
    void setOrderDetail(ChatOrderInfoRntity orderDetail);
}
