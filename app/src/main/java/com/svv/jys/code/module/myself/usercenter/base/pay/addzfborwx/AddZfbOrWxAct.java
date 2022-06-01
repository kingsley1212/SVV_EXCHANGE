package com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.IETConstant;
import com.svv.jys.code.common.entity.AddPayWxEntity;
import com.svv.jys.code.common.entity.IndentityImgEntity;
import com.svv.jys.code.common.entity.OtcPayEntity;
import com.svv.jys.code.common.utils.GetPictureUtils;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.PermissionUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.view.popup.PopupGetPictureView;
import com.svv.jys.code.common.view.popup.PswPopupView;
import com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.model.AddZfbOrWxModel;
import com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.presenter.AddZfbOrWxPresenter;
import com.svv.jys.code.module.myself.usercenter.base.pay.addzfborwx.view.AddZfbOrWxView;

import java.io.File;

public class AddZfbOrWxAct extends MvpActivity<AddZfbOrWxView,AddZfbOrWxModel,AddZfbOrWxPresenter> implements AddZfbOrWxView {

    private OtcPayEntity.RowsBean entity;
    private EditText et_pay_name,et_pay_zhanghao;
    private TextView tv_pay_title,tv_payment_name,tv_pay_name_code,tv_add;
    private ImageView iv_pay_code;
    private File userImgFile;
    private String url;
    @Override
    public void baseInitialization() {
        entity = (OtcPayEntity.RowsBean) getIntent().getSerializableExtra("entity");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_add_zfborwx;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        et_pay_name=findViewById(R.id.et_pay_name);
        et_pay_zhanghao=findViewById(R.id.et_pay_zhanghao);
        tv_pay_title=findViewById(R.id.tv_pay_title);
        tv_payment_name=findViewById(R.id.tv_payment_name);
        tv_pay_name_code=findViewById(R.id.tv_pay_name_code);
        tv_add=findViewById(R.id.tv_add);
        iv_pay_code=findViewById(R.id.iv_pay_code);
        if (entity.getCode().equals("alipay")){
            tv_pay_title.setText(getResources().getString(R.string.add_pay)+" "+getString(R.string.AddPayAct_alpaly));
            tv_payment_name.setText(getString(R.string.AddPayAct_alpaly)+" "+getResources().getString(R.string.OrderDetailAct_zhagnhao));
            tv_pay_name_code.setText(getString(R.string.AddPayAct_alpaly)+" "+getResources().getString(R.string.add_pay_code));
            et_pay_zhanghao.setHint(getResources().getString(R.string.AddPayAct_please_input)+" "+getString(R.string.AddPayAct_alpaly)+" "+getResources().getString(R.string.OrderDetailAct_zhagnhao));
        }else if (entity.getCode().equals("wechat")){
            tv_pay_title.setText(getResources().getString(R.string.add_pay)+" "+getString(R.string.AddPayAct_vchact_paly));
            tv_payment_name.setText(getString(R.string.AddPayAct_vchact_paly)+" "+getResources().getString(R.string.OrderDetailAct_zhagnhao));
            tv_pay_name_code.setText(getString(R.string.AddPayAct_vchact_paly)+" "+getResources().getString(R.string.add_pay_code));
            et_pay_zhanghao.setHint(getResources().getString(R.string.AddPayAct_please_input)+" "+getString(R.string.AddPayAct_vchact_paly)+" "+getResources().getString(R.string.OrderDetailAct_zhagnhao));
        }



        iv_pay_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImg(v);
            }
        });
        tv_add.setOnClickListener(new View.OnClickListener() {

            private String zhanghao;
            private String username;

            @Override
            public void onClick(View v) {
                username = et_pay_name.getText().toString().trim();
                zhanghao = et_pay_zhanghao.getText().toString().trim();
                if (TextUtils.isEmpty(username)){
                    T.showShort(AddZfbOrWxAct.this,getResources().getString(R.string.add_pay_name));
                    return;
                }
                if (TextUtils.isEmpty(zhanghao)){
                    T.showShort(AddZfbOrWxAct.this,getResources().getString(R.string.AddPayAct_please_input)+entity.getName()+getResources().getString(R.string.OrderDetailAct_zhagnhao));
                    return;
                }
                PswPopupView pswPopupView=new PswPopupView(AddZfbOrWxAct.this, new PswPopupView.SubmitLisnener() {
                    @Override
                    public void doSubmit(String pw) {
                        AddPayWxEntity addPayWxEntity = new AddPayWxEntity(username, zhanghao,url);
                        String memo = JSON.toJSONString(addPayWxEntity);
                        presenter.addPay( entity.getCode(), memo, pw);
                    }
                });
                pswPopupView.showPop(v);
            }
        });
    }

    public void selectImg(View view){
        PopupGetPictureView popupGetPictureView = new PopupGetPictureView(this, new PopupGetPictureView.GetPicture() {
            @Override
            public void takePhoto(View v) {
                if (PermissionUtils.checkTakePhotoPermission(getMContext())) {
                    userImgFile = GetPictureUtils.takePicture(getMContext(), IETConstant.GETPICTURE_TAKEPHOTO);
                }
            }

            @Override
            public void selectPhoto(View v) {
                if (PermissionUtils.checkAlbumStroagePermission(getMContext())) {
                    GetPictureUtils.selectPhoto(getMContext(), IETConstant.GETPICTURE_SELECTPHOTO);
                }
            }
        });
        popupGetPictureView.showPop(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }

        switch (requestCode) {
            case IETConstant.GETPICTURE_TAKEPHOTO:
//                userImgFile = GetPictureUtils.cutPicture(getMContext(), userImgFile);
                presenter.compressAndcommitImg(userImgFile);
                break;
            case IETConstant.GETPICTURE_SELECTPHOTO:
                userImgFile = GetPictureUtils.getPhotoFromIntent(data, getMContext());
//                userImgFile = GetPictureUtils.cutPicture(getMContext(), userImgFile);
                presenter.compressAndcommitImg(userImgFile);
                break;
            case IETConstant.CUT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    presenter.compressAndcommitImg(userImgFile);
                }
                break;
            default:
                break;
        }

    }


    @Override
    public void doBusiness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public AddZfbOrWxPresenter initPresenter() {
        return new AddZfbOrWxPresenter();
    }

    @Override
    public void succese(IndentityImgEntity entity) {
        url=entity.getUrl();
        GlideUtils.loadImageDefult(this,entity.getSrc(),iv_pay_code);
    }

    @Override
    public void addSuccese() {
        finish();
    }
}
