package com.svv.jys.code.module.home.base.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.common.utils.ResourceUtils;
import com.svv.jys.code.common.utils.ToolUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2018/5/29.
 */

public class HP_MarketListAdapter extends PagerAdapter {
    Context context;
    public LayoutInflater mLayoutInflater;
    public List<List<MarketListEntity>> marketListEntities;

    public interface OnItemClick {
        void onItemClick(MarketListEntity marketListEntity);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    private OnItemClick onItemClick;

    public HP_MarketListAdapter(Context context) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.marketListEntities = new ArrayList<>();
    }

    public void setList(List<List<MarketListEntity>> marketListEntities) {
        this.marketListEntities = marketListEntities;
    }

    @Override
    public int getCount() {
        return marketListEntities.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    // 初始化显示的条目对象


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mLayoutInflater.inflate(R.layout.layout_viewpager, container, false);
        List<MarketListEntity> entities = marketListEntities.get(position);
        if (entities.size() >= 1) {
            TextView market_1_tv = view.findViewById(R.id.market_1_tv);
            TextView market_1_1_tv = view.findViewById(R.id.market_1_1_tv);
            TextView market_1_2_tv = view.findViewById(R.id.market_1_2_tv);
            TextView market_1_3_tv = view.findViewById(R.id.market_1_3_tv);
            LinearLayout ll_home_viper = view.findViewById(R.id.ll_home_viper);

            final MarketListEntity e1 = entities.get(0);
//            market_1_tv.setText(e1.getName());
            String one = e1.getName().replace("_", "/").toUpperCase();

            SpannableString spannableString = new SpannableString(one);
            spannableString.setSpan(new AbsoluteSizeSpan(ResourceUtils.dipToPX(context, 11)), one.indexOf("/"), one.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            market_1_tv.setText(spannableString);


            market_1_1_tv.setText(ToolUtils.SzzcFormat(e1.getNew_price()));
//            ToolUtils.setMarketUpDownTvColor(e1.getChange(), market_1_1_tv);
            if (ToolUtils.String2Double(e1.getChange()) >= 0) {
                market_1_2_tv.setText("+" + ToolUtils.doublePoint(ToolUtils.String2Double(e1
                        .getChange())) + "%");
            } else {
                market_1_2_tv.setText(ToolUtils.doublePoint(ToolUtils.String2Double(e1
                        .getChange())) + "%");
            }

            ToolUtils.setMarketUpDownTvColor(e1.getChange(), market_1_2_tv);

//            market_1_3_tv.setText("≈" + ToolUtils.doublePoint(ToolUtils.String2Double(e1
//                    .getChange())) + "CNY");
            /*market_1_3_tv.setText("≈" + ToolUtils.doublePoint(ToolUtils.String2Double(e1.getCny_price())) + "CNY");*/
            ll_home_viper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onItemClick(e1);
                }
            });
        }

        if (entities.size() >= 2) {
            TextView market_2_tv = view.findViewById(R.id.market_2_tv);
            TextView market_2_1_tv = view.findViewById(R.id.market_2_1_tv);
            TextView market_2_2_tv = view.findViewById(R.id.market_2_2_tv);
            TextView market_2_3_tv = view.findViewById(R.id.market_2_3_tv);

            final MarketListEntity e2 = entities.get(1);
//            market_2_tv.setText(e2.getName());
            /**
             * 设置不一样大小的字体
             */
            String one = e2.getName().replace("_", "/").toUpperCase();
            SpannableString spannableString = new SpannableString(one);
            spannableString.setSpan(new AbsoluteSizeSpan(ResourceUtils.dipToPX(context, 11)), one.indexOf("/"), one.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            market_2_tv.setText(spannableString);

            market_2_1_tv.setText(ToolUtils.SzzcFormat(e2.getNew_price()));
//            ToolUtils.setMarketUpDownTvColor(e2.getChange(), market_2_1_tv);
            if (ToolUtils.String2Double(e2.getChange()) >= 0) {
                market_2_2_tv.setText("+" + ToolUtils.doublePoint(ToolUtils.String2Double(e2
                        .getChange())) + "%");
            } else {
                market_2_2_tv.setText(ToolUtils.doublePoint(ToolUtils.String2Double(e2
                        .getChange())) + "%");
            }
            ToolUtils.setMarketUpDownTvColor(e2.getChange(), market_2_2_tv);

//            market_2_3_tv.setText("≈" + ToolUtils.doublePoint(ToolUtils.String2Double(e2
//                    .getChange())) + "CNY");
            /*market_2_3_tv.setText("≈" + ToolUtils.doublePoint(ToolUtils.String2Double(e2.getCny_price())) + "CNY");*/
            LinearLayout ll_home_viper = view.findViewById(R.id.ll_home_viper2);
            ll_home_viper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onItemClick(e2);
                }
            });
        }

        if (entities.size() >= 3) {
            TextView market_3_tv = view.findViewById(R.id.market_3_tv);
            TextView market_3_1_tv = view.findViewById(R.id.market_3_1_tv);
            TextView market_3_2_tv = view.findViewById(R.id.market_3_2_tv);
            TextView market_3_3_tv = view.findViewById(R.id.market_3_3_tv);

            final MarketListEntity e3 = entities.get(2);
            market_3_tv.setText(e3.getName());
            /**
             * 设置不一样大小的字体
             */
            String one = e3.getName().replace("_", "/").toUpperCase();
            SpannableString spannableString = new SpannableString(one);
            spannableString.setSpan(new AbsoluteSizeSpan(ResourceUtils.dipToPX(context, 11)), one.indexOf("/"), one.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            market_3_tv.setText(spannableString);

            market_3_1_tv.setText(ToolUtils.SzzcFormat(e3.getNew_price()));
//            ToolUtils.setMarketUpDownTvColor(e3.getChange(), market_3_1_tv);
            if (ToolUtils.String2Double(e3.getChange()) >= 0) {
                market_3_2_tv.setText("+" + ToolUtils.doublePoint(ToolUtils.String2Double(e3
                        .getChange())) + "%");
            } else {
                market_3_2_tv.setText(ToolUtils.doublePoint(ToolUtils.String2Double(e3
                        .getChange())) + "%");
            }
            ToolUtils.setMarketUpDownTvColor(e3.getChange(), market_3_2_tv);

//            market_3_3_tv.setText("≈" + ToolUtils.doublePoint(ToolUtils.String2Double(e3
//                    .getChange())) + "CNY");
            /* market_3_3_tv.setText("≈" + ToolUtils.doublePoint(ToolUtils.String2Double(e3.getCny_price())) + "CNY");*/
            LinearLayout ll_home_viper = view.findViewById(R.id.ll_home_viper3);
            ll_home_viper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onItemClick(e3);
                }
            });
        }
        container.addView(view);
        return view;
    }

    // 销毁条目对象
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        container.removeView((View) object);
    }


}
