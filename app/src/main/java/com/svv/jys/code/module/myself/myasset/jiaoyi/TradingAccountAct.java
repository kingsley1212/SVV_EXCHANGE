package com.svv.jys.code.module.myself.myasset.jiaoyi;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.CunFangSetEntity;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.entity.WalletDataEntity;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.myself.login.userlogin.UserLoginAct;
import com.svv.jys.code.module.myself.myasset.jiaoyi.adapter.TradingAccountAdapter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.ChargeMoneyAct;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.MentionMoneyAct;
import com.svv.jys.code.module.myself.myasset.jiaoyi.model.TradingAccountModel;
import com.svv.jys.code.module.myself.myasset.jiaoyi.presenter.TradingAccountPresenter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.rengou.RenGouAct;
import com.svv.jys.code.module.myself.myasset.jiaoyi.suocang.SuocangAct;
import com.svv.jys.code.module.myself.myasset.jiaoyi.view.TradingAccountView;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.OtcTransferAct;
import com.svv.jys.code.module.net.exception.NeedLoginException;

import java.util.List;

/**
 * Created by js on 2018/5/26.
 */

public class TradingAccountAct extends MvpActivity<TradingAccountView, TradingAccountModel, TradingAccountPresenter>
        implements TradingAccountView {
    private TextView tv_trade_account_name, tv_trade_account_able,
            tv_trade_account_freeze, tv_trade_account_zhehe,
            tv_unit, cb_tv, tb_tv, hz_tv, tv_suocang, tv_rengou, tv_peishou,top_tv;
    private String coin;
    private int type;
    private XRecyclerView xrv_trade_account;
    private View no_data_ly;
    private TradingAccountAdapter adapter;
    private ImageView coin_iv;
    private CunFangSetEntity entity;
    private boolean isSuocang;
    private CunFangSetEntity.ListBean listBean;
    private HomeAssatEntity.Rows bean;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public TradingAccountPresenter initPresenter() {
        return new TradingAccountPresenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
        type = getIntent().getIntExtra("type", 0);
        bean = (HomeAssatEntity.Rows) getIntent().getSerializableExtra("assat");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_trade_account;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.zhanghuxiangqing));
        cb_tv = findViewById(R.id.cb_tv);
        tb_tv = findViewById(R.id.tb_tv);
        hz_tv = findViewById(R.id.hz_tv);
        top_tv = findViewById(R.id.top_tv);
        tv_unit = findViewById(R.id.tv_unit);
        tv_suocang = findViewById(R.id.tv_suocang);
        tv_rengou = findViewById(R.id.tv_rengou);
        tv_peishou = findViewById(R.id.tv_peishou);
        tv_unit.setText(tv_unit.getText().toString().replace("CNY", ToolUtils.getCurrency(this)));
        no_data_ly = findViewById(R.id.no_data_ly);
        tv_trade_account_name = findViewById(R.id.tv_trade_account_name);
        tv_trade_account_able = findViewById(R.id.tv_trade_account_able);
        tv_trade_account_freeze = findViewById(R.id.tv_trade_account_freeze);
        tv_trade_account_zhehe = findViewById(R.id.tv_trade_account_zhehe);
        xrv_trade_account = findViewById(R.id.xrv_trade_account);
        coin_iv = findViewById(R.id.coin_iv);
        xrv_trade_account.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.no_data_ly);
        adapter = new TradingAccountAdapter(getMContext());
        xrv_trade_account.setAdapter(adapter);
        xrv_trade_account.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getUserCoinInfo(coin, false, type);
            }

            @Override
            public void onLoadMore() {
                presenter.getUserCoinInfo(coin, true, type);
            }
        });

        $(R.id.cb_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    ChargeMoneyAct.StartByCoin(getMContext(), coin);
                }

            }
        });
        $(R.id.tb_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    MentionMoneyAct.StartByCoin(getMContext(), coin);
                }

            }
        });
        hz_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        if (ToolUtils.checkLogin(getMContext(), false)) {
                            Bundle bundle = new Bundle();
                            bundle.putString("OtcTrans_CoinName", coin);
                            gotoActivity(OtcTransferAct.class, false, bundle);
                        }
                    } catch (NeedLoginException e) {
                        gotoActivity(UserLoginAct.class);
                    }
                }

            }
        });
        if (type == 0) {
            cb_tv.setVisibility(View.VISIBLE);
            tb_tv.setVisibility(View.VISIBLE);
            hz_tv.setVisibility(View.GONE);
            if (bean.getIs_c2c().equals("1")) {
                tv_rengou.setVisibility(View.VISIBLE);
            }
            if (bean.getIs_ps().equals("1")) {
                tv_peishou.setVisibility(View.VISIBLE);
            }
        }else if (type == 2){

            cb_tv.setVisibility(View.GONE);
            tb_tv.setVisibility(View.GONE);
            hz_tv.setVisibility(View.GONE);
            top_tv.setText(getString(R.string.trade_zhiya));
        }else {
            cb_tv.setVisibility(View.VISIBLE);
            tb_tv.setVisibility(View.GONE);
            hz_tv.setVisibility(View.VISIBLE);
        }
        tv_rengou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("coin", coin);
                bundle2.putString("type", "1");
                bundle2.putSerializable("assat",bean);
                gotoActivity(RenGouAct.class,false,bundle2);
            }
        });

        tv_peishou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle3 = new Bundle();
                bundle3.putString("coin", coin);
                bundle3.putString("type", "2");
                bundle3.putSerializable("assat",bean);
                gotoActivity(RenGouAct.class,false,bundle3);
            }
        });


    }

    @Override
    public void doBusiness() {
        presenter.getUserCoinInfo(coin, false, type);

        if (type == 2 ) {
            presenter.setNumBankList();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public String getRySize() {
        return String.valueOf(adapter.getItemCount());
    }

    @Override
    public void putData(WalletDataEntity entity, boolean isLoadMore) {
        if (TextUtils.isEmpty(tv_trade_account_name.getText().toString())) {

            GlideUtils.loadImageDefult(getMContext(), entity.getUser_coin().getLogo(), coin_iv);
            tv_trade_account_name.setText(coin.toUpperCase());
            tv_trade_account_able.setText(entity.getUser_coin().getAble());
            tv_trade_account_freeze.setText(entity.getUser_coin().getFreeze());
            tv_trade_account_zhehe.setText(ToolUtils.multiplyWithScale(ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE), entity
                    .getUser_coin().getUsdt_price(), 8));
        }
        if (isLoadMore) {
            loading(entity.getRows());
        } else {
            refresh(entity.getRows());
        }
    }

    @Override
    public void setNumBank(CunFangSetEntity setEntity) {
        this.entity = setEntity;
        if (setEntity != null) {
            for (int i = 0; i < setEntity.getList().size(); i++) {
                if (setEntity.getList().get(i).getCoin().equals(coin)) {
                    listBean = setEntity.getList().get(i);
                    isSuocang = true;
                    tv_suocang.setVisibility(View.VISIBLE);
                    break;
                }
            }
        }
        tv_suocang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSuocang) {
                    Bundle bundle4 = new Bundle();
                    bundle4.putSerializable("listbean", listBean);
                    bundle4.putSerializable("CunFangSetEntity", entity);
                    gotoActivity(SuocangAct.class, false, bundle4);
                } else {
                    T.showShort(getMContext(), getString(R.string.suocang_no_kaifang));
                }
            }
        });
    }

    public void loading(List<WalletDataEntity.RowsBean> list) {
        xrv_trade_account.loadMoreComplete();
        //加载下一页
        if (list != null && list.size() != 0) {
            adapter.addList(list);
            adapter.notifyDataSetChanged();
        } else {
            xrv_trade_account.setNoMore(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    xrv_trade_account.setNoMore(false);//已经全部加载完成
                }
            }, 1000);
        }
    }

    public void refresh(List<WalletDataEntity.RowsBean> list) {
        //刷新
        if (list != null && list.size() != 0) {
            no_data_ly.setVisibility(View.GONE);
            xrv_trade_account.setVisibility(View.VISIBLE);
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            xrv_trade_account.refreshComplete();//关闭刷新
        } else {
            xrv_trade_account.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        }
    }

}
