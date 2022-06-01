package com.svv.jys.code.module.myself.myasset.base2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.entity.HomeAssatEntity;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.myasset.base2.MyAsset2Act;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2018/6/8.
 */

public class FbBusinessAssetAdapter extends MBaseAdapter<FbBusinessAssetAdapter.MyAssetViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<HomeAssatEntity.Rows> allList;
    private boolean isHidden = false;
    private boolean isShield = false;
    private List<HomeAssatEntity.Rows> afterList;

    public void setToOtcInterface(FbBusinessAssetAdapter.ToOtcInterface toOtcInterface) {
        this.toOtcInterface = toOtcInterface;
    }

    public void setShield(boolean shield) {
        isShield = shield;
    }

    private FbBusinessAssetAdapter.ToOtcInterface toOtcInterface;

    public FbBusinessAssetAdapter(Context context, List<HomeAssatEntity.Rows> allList) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.allList = allList;
        afterList = new ArrayList<>();
        fullData();
    }

    public void setData(List<HomeAssatEntity.Rows> list) {
        allList = list;
        fullData();
    }

    public void addData(List<HomeAssatEntity.Rows> list) {
        allList.addAll(list);
        fullData();
    }



    public void fullData(){
        afterList.clear();
        isHidden = ((MyAsset2Act) context).isHidden();
        if(isHidden){
            for (HomeAssatEntity.Rows entity:allList) {
                if (!(Double.parseDouble(entity.getAble()) <= 0)) {
                    afterList.add(entity);
                }
            }
        }else {
            afterList.addAll(allList);
        }
        notifyDataSetChanged();
    }

    @Override
    public FbBusinessAssetAdapter.MyAssetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FbBusinessAssetAdapter.MyAssetViewHolder(inflater.inflate(R.layout.item_otcasset_count, parent,false));
    }

    @Override
    public void onBindViewHolder(FbBusinessAssetAdapter.MyAssetViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return afterList.size();
    }

    class MyAssetViewHolder extends RecyclerView.ViewHolder {
        TextView countName, able, freeze, zhehe, to_otc_transfer,tv_zhehe_unit;
        ImageView coin_logo_iv;

        public MyAssetViewHolder(View itemView) {
            super(itemView);
            countName = itemView.findViewById(R.id.countName);
            tv_zhehe_unit=itemView.findViewById(R.id.tv_zhehe_unit);
            able = itemView.findViewById(R.id.able);
            freeze = itemView.findViewById(R.id.freeze);
            zhehe = itemView.findViewById(R.id.zhehe);
            coin_logo_iv = itemView.findViewById(R.id.coin_logo_iv);
//            to_otc_transfer = itemView.findViewById(R.id.to_otc_transfer);
        }

        public void bindData(final int position) {
            isHidden = ((MyAsset2Act) context).isHidden();
            final HomeAssatEntity.Rows entity = afterList.get(position);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ToolUtils.isFastClick(v.getId())){
                        if (onItemClick != null) {
                            onItemClick.onItemClick(position, entity);
                        }
                    }

                }
            });
            tv_zhehe_unit.setText(String.format(context.getResources().getString(R.string.assat_zhehe_usdt),"USDT"));
            countName.setText(entity.getName().toUpperCase());
            GlideUtils.loadImageDefult(context,entity.getLogo(),coin_logo_iv);
            if(!isShield) {
                able.setText(entity.getAble());
                freeze.setText(entity.getFreeze());
                zhehe.setText(entity.getUsdt_price());
            }else {
                able.setText("******");
                freeze.setText("******");
                zhehe.setText("******");
            }

        }
    }

    interface ToOtcInterface {
        void toOtc(int position);
    }

}
