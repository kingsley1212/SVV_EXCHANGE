package com.svv.jys.code.module.myself.myaddress.base.addresslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.AddressEntity;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.module.myself.myaddress.base.MyAddressAct;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.adapter.AddressAdapter;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.model.AddressManagerModel;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.presenter.AddressManagerPersenter;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.view.AddressManagerView;

import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public class AddressManagerAct extends MvpActivity<AddressManagerView,AddressManagerModel,AddressManagerPersenter> implements AddressManagerView{
    private XRecyclerView rv_address;
    private String coin;
    private AddressAdapter adapter;
    private RelativeLayout ll_address;
    private View no_data;
    private TextView noDataText,tv_add_address;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public AddressManagerPersenter initPresenter() {
        return new AddressManagerPersenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_addressmanager;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(coin.toUpperCase()+getString(R.string.address));
        rv_address=findViewById(R.id.rv_address);
        ll_address=findViewById(R.id.ll_address);
        no_data=$(R.id.no_data);
        tv_add_address = (TextView) $(R.id.tv_add_address);
        tv_add_address.setText(String.format(getString(R.string.myaddressact_title),coin.toUpperCase()));
        noDataText= (TextView) $(R.id.noDataText);
        noDataText.setText(getString(R.string.address5));
        rv_address.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.tv_add_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())){
                    Bundle bundle=new Bundle();
                    bundle.putString("coin",coin);
                    gotoActivity(MyAddressAct.class,false,bundle);
                }

            }
        });
        rv_address.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                presenter.getAddressList(coin,0);
            }

            @Override
            public void onLoadMore() {
                presenter.getAddressList(coin,adapter.getItemCount());
            }
        });
        adapter = new AddressAdapter(this);
        rv_address.setAdapter(adapter);
        adapter.setOnItemClick(new MBaseAdapter.MOnItemClickListener() {
            @Override
            public void onItemClick(int position, Object o) {
                Intent intent=new Intent();
                intent.putExtra("address",((AddressEntity)o).getAddress());
                setResult(2,intent);
                finish();
            }
        });
        adapter.setAddressNanager(new AddressAdapter.AddressNanager() {
            @Override
            public void deleteAddress(final AddressEntity addressEntity, final int position) {
                PopupDialogView view = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                    @Override
                    public void leftBtn() {
                    }

                    @Override
                    public void rightBtn() {
                        presenter.deleteAddress(addressEntity.getId(),position);
                    }
                });
                view.showPop(ll_address, getString(R.string.address6), getString(R.string.address7), getString(R.string.myselffragment_left_text), getString(R.string.myselffragment_right_text));

            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAddressList(coin,0);
    }

    @Override
    public void setAddress(final List<AddressEntity> list) {
        if (list==null||list.size()==0){
            no_data.setVisibility(View.VISIBLE);
            rv_address.setVisibility(View.GONE);
            return;
        }else {
            rv_address.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.GONE);
        }
        adapter.setList(list);
        rv_address.refreshComplete();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void deleteSuccese(int position) {
        adapter.remove(position);
      adapter.notifyItemRemoved(position+1);

        T.showShort(this,getString(R.string.address8));
    }

    @Override
    public void loadMoreAddress(List<AddressEntity> list) {
        adapter.addList(list);
        adapter.notifyDataSetChanged();
        rv_address.loadMoreComplete();
    }

    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
    }
}
