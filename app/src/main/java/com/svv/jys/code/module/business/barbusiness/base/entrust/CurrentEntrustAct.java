package com.svv.jys.code.module.business.barbusiness.base.entrust;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.module.business.barbusiness.base.entrust.adapter.CurrentEntrustAdapter;
import com.svv.jys.code.module.business.barbusiness.base.entrust.model.ICurrentEntrustModel;
import com.svv.jys.code.module.business.barbusiness.base.entrust.presenter.CurrentEntrustPresenter;
import com.svv.jys.code.module.business.barbusiness.base.entrust.view.ICurrentEntrustView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by js on 2018/5/10.
 */

public class CurrentEntrustAct extends MvpActivity<ICurrentEntrustView,ICurrentEntrustModel,CurrentEntrustPresenter> implements ICurrentEntrustView{
    private XRecyclerView xrv_entrust;
    private TextView tv_entrust_in,tv_entrust_ont;
    private View line_entrust_in,line_entrust_ont;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public CurrentEntrustPresenter initPresenter() {
        return new CurrentEntrustPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_current_entrust;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.CurrentEntrustAct_title));
        xrv_entrust=findViewById(R.id.xrv_entrust);
        tv_entrust_in=findViewById(R.id.tv_entrust_in);
        tv_entrust_ont=findViewById(R.id.tv_entrust_ont);
        line_entrust_in=findViewById(R.id.line_entrust_in);
        line_entrust_ont=findViewById(R.id.line_entrust_ont);
        tv_entrust_in.setSelected(true);
        line_entrust_in.setSelected(true);
        tv_entrust_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_entrust_in.isSelected()){
                    tv_entrust_in.setSelected(true);
                    line_entrust_in.setSelected(true);
                    tv_entrust_ont.setSelected(false);
                    line_entrust_ont.setSelected(false);
                }

            }
        });
        tv_entrust_ont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!tv_entrust_ont.isSelected()){
                    tv_entrust_in.setSelected(false);
                    line_entrust_in.setSelected(false);
                    tv_entrust_ont.setSelected(true);
                    line_entrust_ont.setSelected(true);
                }

            }
        });
        xrv_entrust.setLayoutManager(new LinearLayoutManager(this));
        xrv_entrust.setAdapter(new CurrentEntrustAdapter(this));
    }

    @Override
    public void doBusiness() {

    }
}
