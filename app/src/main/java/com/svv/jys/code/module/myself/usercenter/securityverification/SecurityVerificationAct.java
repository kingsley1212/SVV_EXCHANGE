package com.svv.jys.code.module.myself.usercenter.securityverification;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PswPopupView;
import com.svv.jys.code.module.myself.usercenter.securityverification.model.SecurityVerificationModel;
import com.svv.jys.code.module.myself.usercenter.securityverification.presenter.SecurityVerificationPresenter;
import com.svv.jys.code.module.myself.usercenter.securityverification.view.SecurityVerificationView;

public class SecurityVerificationAct extends MvpActivity<SecurityVerificationView,SecurityVerificationModel,SecurityVerificationPresenter> implements SecurityVerificationView, View.OnClickListener {

    private FUserInfoEntity fUserInfoEntity;
    private RelativeLayout rl_psw_type,rl_psw_type1,rl_psw_type2;
    private ImageView iv_psw_type,iv_psw_type1,iv_psw_type2;
    private String pwd_type;
    private LinearLayout titlebar_ly;
    @Override
    public SecurityVerificationPresenter initPresenter() {
        return new SecurityVerificationPresenter();
    }

    @Override
    public void baseInitialization() {
        fUserInfoEntity = (FUserInfoEntity) ACache.get(this).getAsObject(ACEConstant.ACE_USERINFO);
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_security_verification;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.usercentre_paw_set));
        rl_psw_type=findViewById(R.id.rl_psw_type);
        rl_psw_type1=findViewById(R.id.rl_psw_type1);
        rl_psw_type2=findViewById(R.id.rl_psw_type2);
        iv_psw_type=findViewById(R.id.iv_psw_type);
        iv_psw_type1=findViewById(R.id.iv_psw_type1);
        iv_psw_type2=findViewById(R.id.iv_psw_type2);
        rl_psw_type.setOnClickListener(this);
        rl_psw_type1.setOnClickListener(this);
        rl_psw_type2.setOnClickListener(this);
        if (!TextUtils.isEmpty(fUserInfoEntity.getPwd_type())){
            if (fUserInfoEntity.getPwd_type().equals("0")){
                iv_psw_type.setVisibility(View.VISIBLE);
            }else if (fUserInfoEntity.getPwd_type().equals("1")){
                iv_psw_type1.setVisibility(View.VISIBLE);
            }else if (fUserInfoEntity.getPwd_type().equals("2")){
                iv_psw_type2.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public Context getMContext() {
        return this;
    }

    public void showPop(View v){
        PswPopupView pswPopupView=new PswPopupView(this, new PswPopupView.SubmitLisnener() {
            @Override
            public void doSubmit(String pw) {
                presenter.changepwdtype(pwd_type,pw);
            }
        });
        pswPopupView.showPop(v);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_psw_type:
                if (iv_psw_type.getVisibility()==View.GONE){
                    pwd_type="0";
                    showPop(v);
                }
                break;
            case R.id.rl_psw_type1:
                if (iv_psw_type1.getVisibility()==View.GONE){
                    pwd_type="1";
                    showPop(v);
                }
                break;
            case R.id.rl_psw_type2:
                if (iv_psw_type2.getVisibility()==View.GONE){
                    pwd_type="2";
                    showPop(v);
                }
                break;
        }
    }

    @Override
    public void succese() {
        switch (pwd_type){
            case "0":
                iv_psw_type.setVisibility(View.VISIBLE);
                iv_psw_type1.setVisibility(View.GONE);
                iv_psw_type2.setVisibility(View.GONE);
                fUserInfoEntity.setPwd_type("0");
                ToolUtils.saveUserInfo(this,fUserInfoEntity);
                break;
            case "1":
                iv_psw_type.setVisibility(View.GONE);
                iv_psw_type1.setVisibility(View.VISIBLE);
                iv_psw_type2.setVisibility(View.GONE);
                fUserInfoEntity.setPwd_type("1");
                ToolUtils.saveUserInfo(this,fUserInfoEntity);
                break;
            case "2":
                iv_psw_type.setVisibility(View.GONE);
                iv_psw_type1.setVisibility(View.GONE);
                iv_psw_type2.setVisibility(View.VISIBLE);
                fUserInfoEntity.setPwd_type("2");
                ToolUtils.saveUserInfo(this,fUserInfoEntity);
                break;
        }
    }
}
