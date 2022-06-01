package com.svv.jys.code.module.myself.inviterecord;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.InviteEntity;
import com.svv.jys.code.common.entity.InviteRecordEntity;
import com.svv.jys.code.common.entity.RewardEntity;
import com.svv.jys.code.module.myself.inviterecord.adapter.CommissionAdapter;
import com.svv.jys.code.module.myself.inviterecord.adapter.InviteRecordAdapter;
import com.svv.jys.code.module.myself.inviterecord.model.InviteRecordModel;
import com.svv.jys.code.module.myself.inviterecord.presenter.InviteRecordPresenter;
import com.svv.jys.code.module.myself.inviterecord.view.InviteRecordView;

import java.util.List;

/**
 * Created by js on 2018/6/7.
 */

public class InviteRecordAct extends MvpActivity<InviteRecordView, InviteRecordModel, InviteRecordPresenter> implements InviteRecordView {
    private TabLayout tab_title;
    private View invite_view;
    private View commission_view;
    private ViewPager view_pager;
    private TextView reward_tv,invite_num_tv;
    private XRecyclerView invite_rv,reward_rv;
    private View nodata_invite,nodata_reward;
    private InviteRecordAdapter inviteAdapter;
    private CommissionAdapter rewardAdapter;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public InviteRecordPresenter initPresenter() {
        return new InviteRecordPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_invite_record;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.qrcode_record));
        tab_title = (TabLayout) $(R.id.tab_title);
        reward_tv = (TextView) $(R.id.reward_tv);
        invite_num_tv = (TextView) $(R.id.invite_num_tv);
        invite_view = getLayoutInflater().inflate(R.layout.view_fabi_item, null);
        commission_view = getLayoutInflater().inflate(R.layout.view_fabi_item, null);
        invite_rv = invite_view.findViewById(R.id.xrv_buy_sell);
        reward_rv = commission_view.findViewById(R.id.xrv_buy_sell);
        nodata_invite = invite_view.findViewById(R.id.no_data_ly);
        nodata_reward = commission_view.findViewById(R.id.no_data_ly);
        view_pager = (ViewPager) $(R.id.view_pager);

        inviteAdapter = new InviteRecordAdapter(getMContext());
        invite_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        invite_rv.setAdapter(inviteAdapter);

        rewardAdapter = new CommissionAdapter(getMContext());
        reward_rv.setLayoutManager(new LinearLayoutManager(getMContext()));
        reward_rv.setAdapter(rewardAdapter);

        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                switch (position){
                    case 0:
                        container.addView(invite_view);
                        return invite_view;
                    case 1:
                        container.addView(commission_view);
                        return commission_view;
                }
                container.addView(invite_view);
                return invite_view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return getString(R.string.qrcode_record);
                    case 1:
                        return getString(R.string.qrcode_commission_record);
                }
                return "";
            }
        };
        view_pager.setAdapter(adapter);
        tab_title.setupWithViewPager(view_pager);
        invite_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getInviteList(0);
            }

            @Override
            public void onLoadMore() {
                presenter.getInviteList(inviteAdapter.getItemCount());

            }
        });
        invite_rv.setPullRefreshEnabled(false);
        reward_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getRewardList(0);
            }

            @Override
            public void onLoadMore() {
                presenter.getRewardList(rewardAdapter.getItemCount());
            }
        });
        reward_rv.setPullRefreshEnabled(false);
    }

    @Override
    public void doBusiness() {
       presenter.getInvite();
       presenter.getInviteList(0);
       presenter.getRewardList(0);
    }

    @Override
    public void loadMoreFinishInvite(List<InviteRecordEntity> list) {
        if(list.size() == 0){
            invite_rv.setNoMore(true);
        }
        inviteAdapter.addEntities(list);
        inviteAdapter.notifyDataSetChanged();
        invite_rv.loadMoreComplete();
    }

    @Override
    public void refreshFinishInvite(List<InviteRecordEntity> list) {
        if(list.size() == 0){
            nodata_invite.setVisibility(View.VISIBLE);
            invite_rv.setVisibility(View.GONE);
            return;
        }
        inviteAdapter.setEntities(list);
        inviteAdapter.notifyDataSetChanged();
        invite_rv.refreshComplete();
    }

    @Override
    public void setData(InviteEntity entity) {
        invite_num_tv.setText(entity.getInvite_num());
        reward_tv.setText(entity.getInvite_value());
    }

    @Override
    public void refreshFinishReward(List<RewardEntity> list) {
        if(list.size() == 0){
            nodata_reward.setVisibility(View.VISIBLE);
            reward_rv.setVisibility(View.GONE);
            return;
        }
        rewardAdapter.setEntities(list);
        rewardAdapter.notifyDataSetChanged();
        reward_rv.refreshComplete();
    }

    @Override
    public void loadMoreFinishReward(List<RewardEntity> list) {
        if(list.size() == 0){
            reward_rv.setNoMore(true);
        }
        rewardAdapter.addEntities(list);
        rewardAdapter.notifyDataSetChanged();
        reward_rv.loadMoreComplete();
    }
}
