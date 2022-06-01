package com.svv.jys.code.module.myself.inviterecord.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.InviteEntity;
import com.svv.jys.code.common.entity.InviteRecordEntity;
import com.svv.jys.code.common.entity.RewardEntity;

import java.util.List;

/**
 * Created by js on 2018/6/7.
 */

public interface InviteRecordView extends BaseView {


    void loadMoreFinishInvite(List<InviteRecordEntity> list);

    void refreshFinishInvite(List<InviteRecordEntity> list);

    void setData(InviteEntity entity);

    void refreshFinishReward(List<RewardEntity> list);

    void loadMoreFinishReward(List<RewardEntity> list);
}
