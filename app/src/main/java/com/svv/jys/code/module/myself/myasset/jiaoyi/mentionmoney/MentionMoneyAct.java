package com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.IETConstant;
import com.svv.jys.code.common.entity.CoinInfoEntity;
import com.svv.jys.code.common.utils.PermissionUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupSafeVerifyView;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.myself.coin.coinlist.CoinListAct;
import com.svv.jys.code.module.myself.myaddress.base.addresslist.AddressManagerAct;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.model.MentionMoneyMode;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.presenter.MentionMoneyPresenter;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.view.MentionMoneyView;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionrecord.MentionRecordAct;
import com.svv.jys.code.module.zxing.activity.CaptureActivity;
import com.svv.jys.code.module.zxing.activity.CodeUtils;
import com.svv.jys.code.module.zxing.util.ImageUtil;

/**
 * Created by js on 2018/5/26.
 */

public class MentionMoneyAct extends MvpActivity<MentionMoneyView, MentionMoneyMode, MentionMoneyPresenter>
        implements MentionMoneyView, View.OnClickListener {
    private ImageView iv_mention_address, iv_mention_code;
    private TextView tv_mention_able, tv_mention_sfx, tv_mention_reality, tv_mention_commit,coin_tv,coin_2_tv,coin_3_tv,tv_tishi;
    private EditText et_mention_address, et_mention_num;
    private String address, num;
    private String coin;

    public static void StartByCoin(Context context,String coin){
        Intent intent = new Intent(context,MentionMoneyAct.class);
        intent.putExtra("coin",coin);
        context.startActivity(intent);
    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public MentionMoneyPresenter initPresenter() {
        return new MentionMoneyPresenter();
    }

    @Override
    public void baseInitialization() {
        coin = getIntent().getStringExtra("coin");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_mention_money;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.trade_tibi));
        ImageView iv_title=findViewById(R.id.iv_title);
        iv_title.setImageResource(R.mipmap.ic_record);
        findViewById(R.id.rl_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    Bundle bundle=new Bundle();
                    bundle.putString("coin",presenter.coinInfoEntity.getCoin());
                    gotoActivity(MentionRecordAct.class,false,bundle);
                }

            }
        });
        tv_tishi=findViewById(R.id.tv_tishi);
        iv_mention_code = findViewById(R.id.iv_mention_code);
        iv_mention_address = findViewById(R.id.iv_mention_address);
        tv_mention_able = findViewById(R.id.tv_mention_able);
        tv_mention_sfx = findViewById(R.id.tv_mention_sfx);
        tv_mention_reality = findViewById(R.id.tv_mention_reality);
        tv_mention_commit = findViewById(R.id.tv_mention_commit);
        et_mention_address = findViewById(R.id.et_mention_address);
        et_mention_num = findViewById(R.id.et_mention_num);
        coin_tv = findViewById(R.id.coin_tv);
        coin_2_tv = findViewById(R.id.coin_2_tv);
        coin_3_tv = findViewById(R.id.coin_3_tv);
        tv_mention_commit.setOnClickListener(this);
        iv_mention_address.setOnClickListener(this);
        iv_mention_code.setOnClickListener(this);
        $(R.id.coins_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    CoinListAct.startByExtract(getMContext());
                }

            }
        });
        ToolUtils.setButtone(tv_mention_commit,new EditText[]{et_mention_num,et_mention_address});
    }

    @Override
    public void doBusiness() {
        if(TextUtils.isEmpty(coin))
            CoinListAct.startByExtract(getMContext());
        else
            presenter.getCoininfo(coin);

    }

    public void setData(final CoinInfoEntity entity){
        if (TextUtils.isEmpty(entity.getTips())){
            tv_tishi.setVisibility(View.GONE);
        }else {
            tv_tishi.setText(entity.getTips());
        }
        tv_mention_reality.setText(String.format("0.00%s", entity.getName().toUpperCase()));
        coin_tv.setText(entity.getCoin().toUpperCase());
        coin_2_tv.setText(entity.getCoin().toUpperCase());
        tv_mention_able.setText(String.format(getString(R.string.myassetact_tv3),entity.getAble(),entity.getName().toUpperCase()));

        if ("1".equals(presenter.coinInfoEntity.getIs_outfee())) {//这
            tv_mention_sfx.setText(entity.getOut_fee());
        }
        et_mention_num.setHint(getString(R.string.assat34) + entity.getOut_min());
        $(R.id.all_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_mention_num.setText(entity.getAble());
            }
        });
        coin_3_tv.setText(entity.getCoin().toUpperCase());
        et_mention_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                num = et_mention_num.getText().toString().trim();
                if (presenter.coinInfoEntity == null) {
                    return;
                }
                if ("0".equals(presenter.coinInfoEntity.getIs_outfee())) {//这
                    if (ToolUtils.isNull(num) || ToolUtils.String2Double(num) == 0.00) {
                        tv_mention_sfx.setText("0.00000000");
                        tv_mention_reality.setText("0.00000000" + entity.getName().toUpperCase());
                    } else {
                        if (!ToolUtils.isNull(entity.getOut_fee())) {
                            String sxf = ToolUtils.multiplyWithScale(ToolUtils.multiply(num, entity.getOut_fee()), "0.01", 8);
                            tv_mention_sfx.setText(sxf);
                            tv_mention_reality.setText(ToolUtils.subtraction(num, sxf) + entity.getName().toUpperCase());
                        } else {
                            tv_mention_sfx.setText("0.00000000");
                            tv_mention_reality.setText(ToolUtils.doublePoint8(ToolUtils.String2Double(num)) + entity.getName()
                                    .toUpperCase());
                        }
                    }
                } else {
//            if (ToolUtils.isNull(num) || ToolUtils.String2Double(num) == 0) {
//                tv_mention_sfx.setText(presenter.coinInfoEntity.getOut_fee());
//                tv_mention_reality.setText("0.00000000" + coin.toUpperCase());
//            }else {
                    if (presenter.coinInfoEntity.getOut_fee() != null) {
                        tv_mention_sfx.setText(presenter.coinInfoEntity.getOut_fee());
                        if (ToolUtils.isNull(num) || ToolUtils.String2Double(num) == 0.00){
                            tv_mention_reality.setText("0.00000000" + entity.getName().toUpperCase());
                        }else {
                            tv_mention_reality.setText(ToolUtils.subtraction(num, presenter.coinInfoEntity.getOut_fee()) + entity.getName().toUpperCase());
                        }

                    }
//            }

                }
//                num = et_mention_num.getText().toString().trim();
//                if (presenter.coinInfoEntity == null) {
//                    return;
//                }
//                if(!TextUtils.isEmpty(num)){
//                    if (presenter.coinInfoEntity.getOut_fee() != null) {
//
//                        tv_mention_reality.setText(String.format("%s%s", ToolUtils.subtraction(num, presenter.coinInfoEntity.getOut_fee()), entity.getName().toUpperCase()));
//                    }else {
//                        tv_mention_reality.setText(String.format("%s%s", ToolUtils.doublePoint8(ToolUtils.String2Double(num)), entity.getName().toUpperCase()));
//                    }
//                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_mention_address:
                if (ToolUtils.isFastClick(view.getId())){
                    Intent intent = new Intent(this, AddressManagerAct.class);
                    intent.putExtra("coin", presenter.coinInfoEntity.getCoin());
                    startActivityForResult(intent, 1);
                }

                break;
            case R.id.tv_mention_commit:
                address = et_mention_address.getText().toString().trim();
                num = et_mention_num.getText().toString().trim();
                if (ToolUtils.isNull(address)) {
                    T.showShort(this, getString(R.string.address2));
                    return;
                }
                if (ToolUtils.isNull(num)) {
                    T.showShort(this, getString(R.string.assat28));
                    return;
                }
                new PopupSafeVerifyView(getMContext(), new PopupSafeVerifyView.checkVerifyListener() {
                    @Override
                    public void checkSuccess() {
                        presenter.transferOut(presenter.coinInfoEntity.getCoin(), address, num);
                    }
                }).showPop(view,"1");
                break;
            case R.id.iv_mention_code:
                if (PermissionUtils.check2dCameraPermission(getMContext())) {
                    Intent intent1 = new Intent(MAppliaction.getApp(), CaptureActivity.class);
                    startActivityForResult(intent1, IETConstant.REQUEST_CODE);
                }
                break;

        }
    }

    @Override
    public void transferoutSuccess() {
        T.showShort(this, getString(R.string.assat33));
        finish();
    }

    @Override
    public void succeseCode() {

    }

    @Override
    public void afterGetCoin() {
        if(presenter.coinInfoEntity == null){
            finish();
        }else {
            setData(presenter.coinInfoEntity);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 402){
            if (resultCode == 502){
                String coin =  data.getStringExtra("coin");
                presenter.getCoininfo(coin);
                return;
            }else {
                afterGetCoin();
            }
        }
        if (resultCode == 2) {
            address = data.getStringExtra("address");
            et_mention_address.setText(address);
        }
        if (requestCode == IETConstant.REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    et_mention_address.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getMContext(), getResources().getString(R.string.homePage_getCode_error), Toast
                            .LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == IETConstant.REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(getMContext(), uri), new CodeUtils
                            .AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            et_mention_address.setText(result);
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(getMContext(), getResources().getString(R.string.homePage_getCode_error),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == IETConstant.REQUEST_CAMERA_PERM) {
            Toast.makeText(getMContext(), getResources().getString(R.string.homePage_from_setting_come_back), Toast
                    .LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        tv_mention_commit.setTag(null);
        super.onDestroy();

    }
}
