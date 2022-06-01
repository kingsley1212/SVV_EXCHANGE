package com.svv.jys.code.module.business.tradechat.introduction;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.entity.CoinIntroduceEntity;
import com.svv.jys.code.module.business.tradechat.introduction.model.IntroductionModel;
import com.svv.jys.code.module.business.tradechat.introduction.persenter.IntroductionPresenter;
import com.svv.jys.code.module.business.tradechat.introduction.view.IntroductionView;

/**
 * Created by js on 2018/5/19.
 */

public class IntroductionFragment extends MvpFragment<IntroductionView, IntroductionModel, IntroductionPresenter> implements IntroductionView {
    private TextView block_tv,book_tv,website_tv,issue_date_tv,coin_name_tv,detail_tv;

    public static IntroductionFragment newInstance(String area) {
        Bundle args = new Bundle();
        IntroductionFragment fragment = new IntroductionFragment();
        args.putString("AREA",area);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Context getMContext() {
        return getActivity();
    }

    @Override
    public IntroductionPresenter initPresenter() {
        return new IntroductionPresenter();
    }

    @Override
    public void onWakeBussiness() {
    }

    @Override
    public void baseInitialization() {
    }

    @Override
    public int setR_Layout() {
        return R.layout.frag_introduction;
    }

    @Override
    public void viewInitialization() {
        block_tv = (TextView) $(R.id.block_tv);
        book_tv = (TextView) $(R.id.book_tv);
        website_tv = (TextView) $(R.id.website_tv);
        issue_date_tv = (TextView) $(R.id.issue_date_tv);
        coin_name_tv = (TextView) $(R.id.coin_name_tv);
        detail_tv = (TextView) $(R.id.detail_tv);
    }




    @Override
    public void doBusiness() {
        String area = getArguments().getString("AREA");
        presenter.getCoin(area);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setCoinInfo(CoinIntroduceEntity entity) {
        block_tv.setText(entity.getBlock_link());
        book_tv.setText(entity.getWhite_paper());
        website_tv.setText(entity.getWebsite());
        issue_date_tv.setText(entity.getIssue());
        coin_name_tv.setText(entity.getName().toUpperCase());
        detail_tv.setText(entity.getDescription());
        if (!TextUtils.isEmpty(block_tv.getText().toString())){
            block_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(block_tv.getText().toString()));
                    startActivity(intent);
                }
            });
        }
        if (!TextUtils.isEmpty(book_tv.getText().toString())){
            book_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(book_tv.getText().toString()));
                    startActivity(intent);
                }
            });
        }
        if (!TextUtils.isEmpty(website_tv.getText().toString())){
            website_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(website_tv.getText().toString()));
                    startActivity(intent);
                }
            });
        }

    }
}
