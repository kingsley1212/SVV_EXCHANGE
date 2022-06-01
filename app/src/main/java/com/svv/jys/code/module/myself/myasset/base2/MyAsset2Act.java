package com.svv.jys.code.module.myself.myasset.base2;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.selfview.CommonViewPager.CommonViewPager;
import com.svv.jys.code.common.view.selfview.CommonViewPager.ViewPagerHolderCreator;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.myself.myasset.base2.adapter.CoinBusinessAssetAdapter;
import com.svv.jys.code.module.myself.myasset.base2.adapter.FbBusinessAssetAdapter;
import com.svv.jys.code.module.myself.myasset.base2.adapter.SuoCangBusinessAssetAdapter;
import com.svv.jys.code.module.myself.myasset.base2.model.IMyAsset2Model;
import com.svv.jys.code.module.myself.myasset.base2.presenter.MyAsset2Presenter;
import com.svv.jys.code.module.myself.myasset.base2.vh.TopAssetTypeVH;
import com.svv.jys.code.module.myself.myasset.base2.view.IMyAsset2View;
import com.svv.jys.code.module.myself.myasset.jiaoyi.TradingAccountAct;

import java.util.List;

/**
 * Created by lzj on 2018/6/8.
 */

public class MyAsset2Act extends MvpActivity<IMyAsset2View, IMyAsset2Model, MyAsset2Presenter> implements
        IMyAsset2View, TextWatcher {


    private CommonViewPager myasset_vp;
    private View  toptype_ll, no_data_ly;
    private LinearLayout top_cb, top_fb,top_suocang;
    private RecyclerView myasset_rv;
    private EditText er_assat_search;
    private CoinBusinessAssetAdapter coinBusinessAssetAdapter;
    private FbBusinessAssetAdapter fbBusinessAssetAdapter;
    private SuoCangBusinessAssetAdapter suoCangBusinessAssetAdapter;
    private String coinname;
    private LinearLayout ll_check;
    private String showOrhide;
    private CheckBox check;

    @Override
    public MyAsset2Presenter initPresenter() {
        return new MyAsset2Presenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_myasset2;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        check = (CheckBox) $(R.id.check);
        ll_check = (LinearLayout) $(R.id.ll_check);
        toptype_ll = $(R.id.toptype_ll);
        no_data_ly = $(R.id.no_data_ly);
        er_assat_search = (EditText) $(R.id.er_assat_search);
        er_assat_search.addTextChangedListener(this);
        top_cb = (LinearLayout) $(R.id.top_cb);
        top_fb = (LinearLayout) $(R.id.top_fb);
        top_suocang = (LinearLayout) $(R.id.top_suocang);

        myasset_rv = (RecyclerView) $(R.id.myasset_rv);
        myasset_rv.setLayoutManager(new LinearLayoutManager(getMContext()));

        myasset_vp = (CommonViewPager) findViewById(R.id.myasset_vp);

        top_cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myasset_vp.setCurrentItem(0);//交易账户
            }
        });

        top_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myasset_vp.setCurrentItem(1);//法币账户
            }
        });
        top_suocang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myasset_vp.setCurrentItem(2);//锁仓账户
            }
        });
        ll_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check.setChecked(!check.isChecked());
                ((MBaseAdapter)myasset_rv.getAdapter()).fullData();

            }
        });
    }

    public boolean isHidden(){
        return check.isChecked();
    }

    @Override
    public void doBusiness() {
        presenter.getTopAsset();
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public void setCoinBusinessAsset(final HomeAssatEntity entity) {
        try {
            presenter.topAsset.get(0).setP_amount(entity.getSum_total());
            presenter.topAsset.get(0).setCny_amount(ToolUtils.multiplyWithScale(ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE), entity
                    .getSum_total(), 2));
            myasset_vp.notifyDataSetChanged();
        } catch (Exception e) {

        }
        if (entity == null || entity.getRows().size() == 0) {
            myasset_rv.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        } else {
            myasset_rv.setVisibility(View.VISIBLE);
            no_data_ly.setVisibility(View.GONE);
            if (coinBusinessAssetAdapter == null) {
                coinBusinessAssetAdapter = new CoinBusinessAssetAdapter(this, entity.getRows());
                myasset_rv.setAdapter(coinBusinessAssetAdapter);
                coinBusinessAssetAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        Bundle bundle = new Bundle();
                        bundle.putString("coin", ((HomeAssatEntity.Rows) o).getName());
                        bundle.putInt("type", 0);
                        bundle.putSerializable("assat",((HomeAssatEntity.Rows)o));
                        gotoActivity(TradingAccountAct.class, false, bundle);
                    }
                });
            } else {
                coinBusinessAssetAdapter.setData(entity.getRows());
                myasset_rv.setAdapter(coinBusinessAssetAdapter);
                coinBusinessAssetAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void setFBBusinessAsset(HomeAssatEntity entity) {
        try {
            presenter.topAsset.get(1).setP_amount(entity.getSum_total());
            presenter.topAsset.get(1).setCny_amount(ToolUtils.multiplyWithScale(ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE), entity
                    .getSum_total(), 2));
            myasset_vp.notifyDataSetChanged();
        } catch (Exception e) {

        }

        if (entity == null || entity.getRows().size() == 0) {
            myasset_rv.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        } else {
            myasset_rv.setVisibility(View.VISIBLE);
            no_data_ly.setVisibility(View.GONE);
            if (fbBusinessAssetAdapter == null) {
                fbBusinessAssetAdapter = new FbBusinessAssetAdapter(this, entity.getRows());
                myasset_rv.setAdapter(fbBusinessAssetAdapter);
                fbBusinessAssetAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", 1);
                        bundle.putString("coin", ((HomeAssatEntity.Rows) o).getName());
                        bundle.putSerializable("assat",((HomeAssatEntity.Rows)o));
                        gotoActivity(TradingAccountAct.class, false, bundle);
                    }
                });
            } else {
                fbBusinessAssetAdapter.setData(entity.getRows());
                myasset_rv.setAdapter(fbBusinessAssetAdapter);
                fbBusinessAssetAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setSuoCangAsset(HomeAssatEntity entity) {
        try {
            presenter.topAsset.get(2).setP_amount(entity.getSum_total());
            presenter.topAsset.get(2).setCny_amount(ToolUtils.multiplyWithScale(ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE), entity
                    .getSum_total(), 2));
            myasset_vp.notifyDataSetChanged();
        } catch (Exception e) {

        }

        if (entity == null || entity.getRows().size() == 0) {
            myasset_rv.setVisibility(View.GONE);
            no_data_ly.setVisibility(View.VISIBLE);
        } else {
            myasset_rv.setVisibility(View.VISIBLE);
            no_data_ly.setVisibility(View.GONE);
            if (suoCangBusinessAssetAdapter == null) {
                suoCangBusinessAssetAdapter = new SuoCangBusinessAssetAdapter(this, entity.getRows());
                myasset_rv.setAdapter(suoCangBusinessAssetAdapter);
                suoCangBusinessAssetAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("type", 2);
                        bundle.putString("coin", ((HomeAssatEntity.Rows) o).getName());
                        bundle.putSerializable("assat",((HomeAssatEntity.Rows)o));
                        gotoActivity(TradingAccountAct.class, false, bundle);
                    }
                });
            } else {
                suoCangBusinessAssetAdapter.setData(entity.getRows());
                myasset_rv.setAdapter(suoCangBusinessAssetAdapter);
                suoCangBusinessAssetAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setTopAsset(List<TopAssetTypeVH.TopAssetTypeBean> topAsset) {
        //        // 设置数据
        myasset_vp.setPages(topAsset, new ViewPagerHolderCreator<TopAssetTypeVH>() {
            @Override
            public TopAssetTypeVH createViewHolder() {
                // 返回ViewPagerHolder
                return new TopAssetTypeVH();
            }
        });
        myasset_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0://交易账户
                        presenter.getCoinBusinessAssetData();
                        changeTopText(0);
                        break;
                    case 1://法币账户
                        presenter.getFbBusinessAssetData();
                        changeTopText(2);
                        break;
                    case 2://锁仓
                        presenter.getSuoCangAssetData();
                        changeTopText(1);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //默认交易账户
        myasset_vp.setCurrentItem(0);//交易账户
        changeTopText(0);
        presenter.getCoinBusinessAssetData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (myasset_vp.getCurrentItem()==0){
            presenter.getCoinBusinessAssetData();
        }else if (myasset_vp.getCurrentItem()==1){
            presenter.getFbBusinessAssetData();
        }else {
            presenter.getSuoCangAssetData();
        }
    }

    public void changeTopText(int pos) {
        top_cb.setSelected(false);
        top_fb.setSelected(false);
        top_suocang.setSelected(false);
        switch (pos) {
            case 0://交易账户
                top_cb.setSelected(true);
                break;

            case 2://法币账户
                top_fb.setSelected(true);
                break;
            case 1://锁仓账户
                top_suocang.setSelected(true);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        coinname = er_assat_search.getText().toString().trim();
        switch (myasset_vp.getCurrentItem()) {
            case 0://交易账户
                presenter.getCoinBusinessAssetData();
                changeTopText(0);
                break;
            case 1://法币账户
                presenter.getFbBusinessAssetData();
                changeTopText(2);
                break;
            case 2://锁仓账户
                presenter.getSuoCangAssetData();
                changeTopText(1);
                break;
        }
    }

    public void setAssetType(int position, boolean selected) {
       if(position == 0){
           if(coinBusinessAssetAdapter!=null) {
               coinBusinessAssetAdapter.setShield(selected);
               coinBusinessAssetAdapter.notifyDataSetChanged();
           }
       }else if (position==1){
           if(fbBusinessAssetAdapter!=null) {
               fbBusinessAssetAdapter.setShield(selected);
               fbBusinessAssetAdapter.notifyDataSetChanged();
           }
       }else {
           if(suoCangBusinessAssetAdapter!=null) {
               suoCangBusinessAssetAdapter.setShield(selected);
               suoCangBusinessAssetAdapter.notifyDataSetChanged();
           }
       }
    }
}
