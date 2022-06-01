package com.svv.jys.code.module.home.message_center.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.MessageCenterEntity;

import java.util.List;

public interface MessageCenterView extends BaseView {
    void setData(List<MessageCenterEntity> list);
}
