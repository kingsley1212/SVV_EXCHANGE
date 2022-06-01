package com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.BusinessInfoEntity;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.buyorsell.BuyOrSellAct;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.adapter.BusinessInfoAdapter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.model.UserInfoModel;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.presenter.UserInfoPresenter;
import com.svv.jys.code.module.myself.myasset.otc.otctrading.fragment.userinfo.view.UserInfoView;

import java.util.List;

/**
 * Created by js on 2018/6/20.
 */

public class UserInfoAct extends MvpActivity<UserInfoView,UserInfoModel,UserInfoPresenter> implements UserInfoView{
    private TextView tv_business_name,tv_business_time,tv_buy,tv_sell;
    private ImageView iv_phone,iv_email,iv_identity,iv_guge,iv_userImg;
    private XRecyclerView rv_buy;
    private String uid;
    private TextView name_tv,sj_info_tv,transaction_num,success_tv,finish_rate_tv;
    private BusinessInfoAdapter buyAdapter;
    //商家出售或购买列表
    private String type = "-1";
    private LinearLayout sell_ll,buy_ll;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public UserInfoPresenter initPresenter() {
        return new UserInfoPresenter();
    }

    @Override
    public void baseInitialization() {
        uid = getIntent().getStringExtra("uid");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_userinfo;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        tv_buy=findViewById(R.id.tv_buy);
        tv_sell=findViewById(R.id.tv_sell);
        rv_buy=findViewById(R.id.rv_buy);
        iv_guge=findViewById(R.id.iv_guge);
        iv_phone=findViewById(R.id.iv_phone);
        iv_email=findViewById(R.id.iv_email);
        iv_identity=findViewById(R.id.iv_identity);
        tv_business_name=findViewById(R.id.tv_business_name);
        tv_business_time=findViewById(R.id.tv_business_time);
        iv_userImg = findViewById(R.id.iv_userImg);
        name_tv = findViewById(R.id.name_tv);
        sj_info_tv = findViewById(R.id.sj_info_tv);
        transaction_num = findViewById(R.id.transaction_num);
        success_tv = findViewById(R.id.success_tv);
        finish_rate_tv = findViewById(R.id.finish_rate_tv);
        rv_buy.setLayoutManager(new LinearLayoutManager(this));
        buyAdapter = new BusinessInfoAdapter(this,0,ACache.get(this).getAsString(ACEConstant.ACE_USERINFO_USERID).equals(uid));
        rv_buy.setAdapter(buyAdapter);
        rv_buy.setPullRefreshEnabled(false);
        rv_buy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                presenter.getBusinessList(uid,buyAdapter.getItemCount(),type);
            }
        });
        buy_ll = (LinearLayout) $(R.id.buy_ll);
        sell_ll = (LinearLayout) $(R.id.sell_ll);
        tabChange("1");
        buy_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabChange("0");
            }
        });
        sell_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabChange("1");
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getBusinessinfo(uid);
    }

    @Override
    public void setBusinessInfo(BusinessInfoEntity entity) {

        BusinessInfoEntity.UserBean advUserBean=entity.getUser();

//        BusinessInfoEntity.OrderBean orderBean=entity.getOrder();
        GlideUtils.loadImageDefult(getMContext(),advUserBean.getLogo(),iv_userImg);
        name_tv.setText(advUserBean.getNick_name());
        StringBuilder sj_info = new StringBuilder();
        if(advUserBean.getIs_shoper().equals("1")){
            sj_info.append(getString(R.string.user_info_tv1));
        }
        sj_info.append(String.format(getString(R.string.user_info_tv2),advUserBean.getReg_time()));
        sj_info_tv.setText(sj_info.toString());
        BusinessInfoEntity.MessageBean messageBean = entity.getMessage();
        transaction_num.setText(messageBean.getTotal());
        success_tv.setText(messageBean.getFinish());
        finish_rate_tv.setText(String.format("%s%%", messageBean.getFinish_rate()));

        final List<BusinessInfoEntity.RowsBean> buyList=entity.getBuy().getRows();
        final List<BusinessInfoEntity.RowsBean> sellList=entity.getSell().getRows();
        tv_business_name.setText(advUserBean.getTrue_name());
        tv_business_time.setText(getString(R.string.user_info_tv6)+ToolUtils.timeStamp2String(advUserBean.getReg_time(),"yyyy-MM-dd HH:mm:ss"));
/*        tv_business_finesh_rate.setText("完成率："+orderBean.getFinish_rate()+"%");
        tv_business_fangxing.setText("平均放行："+orderBean.getFangxing()+"分钟");
        tv_business_finish.setText("总成单："+orderBean.getFinish()+"次");
        tv_business_three.setText("30日成单："+orderBean.getThree()+"次");
        tv_business_appeal.setText("申诉："+orderBean.getAppeal()+"次");
        tv_business_sussece.setText("胜诉："+orderBean.getSuccess()+"次");*/
        if (advUserBean.getIs_identity().equals("1")){
            iv_identity.setSelected(true);
        }
        if (advUserBean.getVerify_email().equals("1")){
            iv_email.setSelected(true);
        }
        if (advUserBean.getVerify_mobile().equals("1")){
            iv_phone.setSelected(true);
        }
        if (advUserBean.getVerify_google().equals("1")){
            iv_guge.setSelected(true);
        }
        buyAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("id",((BusinessInfoEntity.RowsBean)o).getId());
                if(type.equals("1")){
                    bundle.putString("trade_type","0");
                }else {
                    bundle.putString("trade_type","1");
                }

                bundle.putSerializable("coin",((BusinessInfoEntity.RowsBean)o).getCoin_name());
                gotoActivity(BuyOrSellAct.class,false,bundle);
            }
        });
    }

    public void tabChange(String type){
        if(this.type.equals(type)){
            return;
        }
        this.type = type;
        buyAdapter.setType(Integer.parseInt(this.type));
        presenter.getBusinessList(uid,0,this.type);
        if(type.equals("0")){
            buy_ll.setSelected(true);
            sell_ll.setSelected(false);
        }else {
            sell_ll.setSelected(true);
            buy_ll.setSelected(false);
        }
    }

    public void setListData(List<BusinessInfoEntity.RowsBean> list,boolean isLoadMore){
        if(isLoadMore){
            rv_buy.loadMoreComplete();
            if(list == null || list.size() == 0){
                rv_buy.setNoMore(true);
                rv_buy.setLoadingMoreEnabled(false);
            }
            buyAdapter.addList(list);
            buyAdapter.notifyDataSetChanged();
        }else {
            rv_buy.setLoadingMoreEnabled(true);
            rv_buy.refreshComplete();
            buyAdapter.setList(list);
            buyAdapter.notifyDataSetChanged();
        }
    }
}
