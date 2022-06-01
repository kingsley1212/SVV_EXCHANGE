package com.svv.jys.code.module.home.message_center;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.MessageCenterEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.home.article.ArticleAct;
import com.svv.jys.code.module.home.message_center.adapter.MessageCenterAdapter;
import com.svv.jys.code.module.home.message_center.model.MessageCenterModel;
import com.svv.jys.code.module.home.message_center.presenter.MessageCenterPresenter;
import com.svv.jys.code.module.home.message_center.view.MessageCenterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageCenterAct extends MvpActivity<MessageCenterView, MessageCenterModel, MessageCenterPresenter> implements MessageCenterView {

    @BindView(R.id.msg_x_recycler)
    XRecyclerView msg_x_recycler;
    @BindView(R.id.nodata_ly)
    View nodata_ly;
    private int currentPage = 1;//用于分页
    private Boolean isShow = true;//是否显示加载框
    private List<MessageCenterEntity> messageCenterEntities;
    private MessageCenterAdapter messageCenterAdapter;
    private String type;

    public static void startByType(String type,Context context){
        Intent intent = new Intent(context,MessageCenterAct.class);
        // 0:消息中心 1:常见问题 2:关于我们
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    @Override
    public MessageCenterPresenter initPresenter() {
        return new MessageCenterPresenter();
    }

    @Override
    public void baseInitialization() {
        type = getIntent().getStringExtra("type");
        messageCenterEntities = new ArrayList<>();
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_message_center;
    }

    @Override
    public void viewInitialization() {
        ButterKnife.bind(this);
        setBackPress();
        switch (type){
            case "0":
                setTitleTx(getResources().getString(R.string.message_center_title));
                break;
            case "1":
                setTitleTx(getResources().getString(R.string.mine_problem));
                break;
            case "2":
                setTitleTx(getResources().getString(R.string.myselffragment_item7));
                break;
        }

        ToolUtils.DXRecyclerView(msg_x_recycler, this);
        msg_x_recycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                isShow = false;
                getData();
            }

            @Override
            public void onLoadMore() {
                currentPage++;
                isShow = false;
                getData();
            }
        });

    }

    @Override
    public void doBusiness() {
        currentPage = 1;
        isShow = true;
        getData();

    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setData(List<MessageCenterEntity> list) {
        if (currentPage == 1) {
            refresh(list);
        } else {
            loading(list);
        }
    }

    public void refresh(List<MessageCenterEntity> list) {
        //刷新
        if (list != null && list.size() != 0) {
            msg_x_recycler.setVisibility(View.VISIBLE);
            nodata_ly.setVisibility(View.GONE);
            messageCenterEntities.clear();
            messageCenterEntities.addAll(list);
            if (messageCenterAdapter == null) {
                messageCenterAdapter = new MessageCenterAdapter(getMContext(), messageCenterEntities);
                msg_x_recycler.setAdapter(messageCenterAdapter);
                messageCenterAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        MessageCenterEntity messageCenterEntity = (MessageCenterEntity) o;
                        Bundle bundle = new Bundle();
                        bundle.putString("id", messageCenterEntity.getId());
                        gotoActivity(ArticleAct.class, false, bundle);
                    }
                });
            } else {
                messageCenterAdapter.notifyDataSetChanged();
            }
            msg_x_recycler.refreshComplete();//关闭刷新
        } else {
            msg_x_recycler.setVisibility(View.GONE);
            nodata_ly.setVisibility(View.VISIBLE);
        }
    }

    public void loading(List<MessageCenterEntity> list) {
        msg_x_recycler.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            messageCenterEntities.addAll(list);
            messageCenterAdapter.notifyDataSetChanged();
        } else {
            msg_x_recycler.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    msg_x_recycler.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void getData(){
        switch (type){
            case "0":
                presenter.getData(currentPage, isShow);
                break;
            case "1":
                presenter.getQuestionData(currentPage,isShow);
                break;
            case "2":
                presenter.getAboutData(currentPage,isShow);
                break;
        }
    }


}
