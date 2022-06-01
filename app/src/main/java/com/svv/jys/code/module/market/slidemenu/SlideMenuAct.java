package com.svv.jys.code.module.market.slidemenu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpDataSocketActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.entity.MarketTitleEntity;
import com.svv.jys.code.module.server.dataserver.event.MarketListEvent;
import com.svv.jys.code.module.market.slidemenu.adapter.MenuAdapter;
import com.svv.jys.code.module.market.slidemenu.presenter.SlideMenuPersenter;
import com.svv.jys.code.module.market.slidemenu.adapter.MenuItemAdapter;
import com.svv.jys.code.module.market.slidemenu.model.SlideMenuModel;
import com.svv.jys.code.module.market.slidemenu.view.SlideMenuView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by js on 2018/5/17.
 */

public class SlideMenuAct extends MvpDataSocketActivity<SlideMenuView, SlideMenuModel, SlideMenuPersenter> implements
        SlideMenuView {
    private final String TAG = "SlideMenuAct";
    public static final int ACT_REQCODE = 2001;
    public static final int ACT_RES_OK = 2002;
    public static final int ACT_RES_FAIL = 2003;
    public boolean isOptional = true;
    public RecyclerView rv_menu, ho_menu;
    private MenuAdapter adapter;
    private MenuItemAdapter menuAdapter;
    private View no_data;


    @Override
    public void cSConttectSeccuss() {

    }

    @Override
    public void cSConttectFail() {

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_no, R.anim.anim_left_out);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public SlideMenuPersenter initPresenter() {
        return new SlideMenuPersenter();
    }

    @Override
    public void baseInitialization() {
        styleControl = false;
        EventBus.getDefault().register(this);
        ACache.get(this).remove(ACEConstant.ACE_MARKET_ENTITY);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doMarketListEvent(MarketListEvent marketListEvent) {
        if (marketListEvent != null) {
            presenter.doHandleMarketList(marketListEvent);
        }
    }

    @Override
    public int setR_Layout() {
       /* String mode = ACache.get(this).getAsString(
                ACEConstant.ACE_THEMEMODE);
        if (ToolUtils.isNull(mode)) {
            mode = NORConstant.DAY_THEME;
        }
        switch (mode) {
            case NORConstant.DAY_THEME:
                setTheme(R.style.TranslucentTheme_Day);
                break;
            case NORConstant.NIGHT_THEME:
                setTheme(R.style.TranslucentTheme_Night);
                break;
            default:
                break;
        }*/
        return R.layout.act_slide;
    }

    @Override
    public void viewInitialization() {
        findViewById(R.id.ll_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2);
                finish();
            }
        });
        no_data = findViewById(R.id.no_data);
        rv_menu = findViewById(R.id.rv_menu);
        rv_menu.setLayoutManager(new LinearLayoutManager(this));

        ho_menu = findViewById(R.id.ho_menu);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        ho_menu.setLayoutManager(linearLayoutManager);
        $(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(2);
                finish();
            }
        });
    }

    @Override
    public void doBusiness() {
        presenter.getMenu();
    }

    @Override
    public void setMenu(final MarketTitleEntity entity) {
        List<String> list = new ArrayList<>();
        list.add(getString(R.string.SlideMenuAct_zixuan));
        list.addAll(entity.getCoin_name());
        adapter = new MenuAdapter(this, list);
        ho_menu.setAdapter(adapter);
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                if (position == 0) {
                    isOptional = true;
                    setMarket(entity.getCollect());
                } else {
                    isOptional = false;
                    setMarket(entity.getMarket().get(position - 1));
                }
            }
        });
        setMarket(entity.getCollect());
//        mDataService.getMarketByArea(list.get(0).getName(), TAG, getMarketByAreaResponse);
    }
    List<MarketListEntity> marketListEntities;

    public void setMarket(List<MarketListEntity> marketListEntities) {
        this.marketListEntities = marketListEntities;
        if (marketListEntities == null || marketListEntities.size() == 0) {
            no_data.setVisibility(View.VISIBLE);
            rv_menu.setVisibility(View.GONE);
        } else {
            rv_menu.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
            if (menuAdapter != null) {
                menuAdapter.setList(marketListEntities, isOptional);
                menuAdapter.notifyDataSetChanged();
            } else {
                menuAdapter = new MenuItemAdapter(this, marketListEntities, isOptional);
                menuAdapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
                    @Override
                    public void onItemClick(int position, Object o) {

                        Intent intent = new Intent();
                        intent.putExtra("entity", (MarketListEntity) o );
                        setResult(SlideMenuAct.ACT_RES_OK, intent);
                        finish();
                    }
                });
                rv_menu.setAdapter(menuAdapter);
            }
        }


    }

    @Override
    public List<MarketListEntity> getNowShowMarket() {
        if (marketListEntities != null) {
            return marketListEntities;
        }
        return new ArrayList<MarketListEntity>();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
