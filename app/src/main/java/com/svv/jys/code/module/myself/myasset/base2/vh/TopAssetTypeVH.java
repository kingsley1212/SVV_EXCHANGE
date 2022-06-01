package com.svv.jys.code.module.myself.myasset.base2.vh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.BaseEntity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.selfview.CommonViewPager.ViewPagerHolder;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.myself.myasset.base2.MyAsset2Act;

/**
 * Created by lzj on 2018/6/8.
 */

public class TopAssetTypeVH implements ViewPagerHolder<TopAssetTypeVH.TopAssetTypeBean> {
    private View bg_ll,asset_eye_ll;
    private TextView assetname_tv, unti_tv, tv_btc, tv_money_cny;
    private Context mContext;

    @Override
    public View createView(Context context) {
        // 返回ViewPager 页面展示的布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_topassettype, null);
        bg_ll = view.findViewById(R.id.bg_ll);
        assetname_tv = (TextView) view.findViewById(R.id.assetname_tv);
        unti_tv = (TextView) view.findViewById(R.id.unti_tv);
        tv_btc = (TextView) view.findViewById(R.id.tv_btc);
        tv_money_cny = (TextView) view.findViewById(R.id.tv_money_cny);
        asset_eye_ll = view.findViewById(R.id.asset_eye_ll);
        mContext = context;
        asset_eye_ll.setSelected(true);
        return view;
    }

    @Override
    public void onBind(final Context context, final int position, final TopAssetTypeVH.TopAssetTypeBean data) {
        // 数据绑定
        assetname_tv.setText(data.typeName);
        unti_tv.setText(context.getString(R.string.assat_equivalent, data.unit));
        asset_eye_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asset_eye_ll.setSelected(!asset_eye_ll.isSelected());
                if(asset_eye_ll.isSelected()){
                    tv_btc.setText("******");
                    tv_money_cny.setText("******");
                }else {
                    tv_btc.setText(ToolUtils.doublePoint8(ToolUtils.String2Double(data.p_amount)) + "");
                    String cny = context.getString(R.string.fbprice_data,GFbPrice(data.p_amount)+ ToolUtils.getCurrency(context));
                    tv_money_cny.setText(cny );
                }
                ((MyAsset2Act)mContext).setAssetType(position,asset_eye_ll.isSelected());
            }
        });
        asset_eye_ll.performClick();
    }

    public String GFbPrice(String num) {
        String s = ToolUtils.multiplyWithScale(ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.PRICE),num, 8);
        s = ToolUtils.doublePoint8(ToolUtils.String2Double(s));
        return s;
    }


    public static class TopAssetTypeBean extends BaseEntity {
        public String typeName;
        public String unit;
        public String p_amount;
        public String cny_amount;


        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getP_amount() {
            return p_amount;
        }

        public void setP_amount(String p_amount) {
            this.p_amount = p_amount;
        }

        public String getCny_amount() {
            return cny_amount;
        }

        public void setCny_amount(String cny_amount) {
            this.cny_amount = cny_amount;
        }
    }

}
