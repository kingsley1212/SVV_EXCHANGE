package com.svv.jys.code.module.myself.usercenter.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.constant.NORConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.entity.NewIdentifyEntity;
import com.svv.jys.code.common.entity.VerifyEntity;
import com.svv.jys.code.common.utils.AppLanguageUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.main.MainAct;
import com.svv.jys.code.module.myself.base.safapsw.SafePswAct;
import com.svv.jys.code.module.myself.rname.RNameAct;
import com.svv.jys.code.module.myself.usercenter.base.bind.BindActivity;
import com.svv.jys.code.module.myself.usercenter.base.google.GoogleRenzAct;
import com.svv.jys.code.module.myself.usercenter.base.model.IUserCenterModel;
import com.svv.jys.code.module.myself.usercenter.base.pay.PayMethedAct;
import com.svv.jys.code.module.myself.usercenter.base.presenter.UserCenterPresenter;
import com.svv.jys.code.module.myself.usercenter.base.verifymethod.VerifyMethodAct;
import com.svv.jys.code.module.myself.usercenter.base.view.IUserCenterView;
import com.svv.jys.code.module.myself.usercenter.help.HelpAct;
import com.svv.jys.code.module.myself.usercenter.pwd.ChangePwdAct;
import com.svv.jys.code.module.myself.usercenter.securityverification.SecurityVerificationAct;
import com.svv.jys.code.module.myself.usercenter.verify.VerifyAct;
import com.svv.jys.code.module.myself.useridentify.base.UserIdentifyAct;
import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UserCenterAct extends MvpActivity<IUserCenterView, IUserCenterModel, UserCenterPresenter> implements
        IUserCenterView, View.OnClickListener {

    private View userset_ll;
    private FUserInfoEntity fUserInfoEntity;
    private TextView tv_user_name, phone_status_tv, email_status_tv, guge_status_tv, rz_status_tv;
    private RelativeLayout rl_safe_psw, rl_login_psw, rl_bind_email, rl_bind_phone, rl_change_style, rl_pay_methed,
            rl_guge_renz, rl_verify_method, rl_help_language, rl_help_style,rl_security_verification;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public UserCenterPresenter initPresenter() {
        return new UserCenterPresenter();
    }

    @Override
    public void baseInitialization() {
        try {
            fUserInfoEntity = (FUserInfoEntity) getIntent().getSerializableExtra("fUserInfoEntity");
        } catch (Exception e) {
            fUserInfoEntity = null;
        }
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_usercenter;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.usercenter_title));
        rl_security_verification=findViewById(R.id.rl_security_verification);
        rl_change_style = findViewById(R.id.rl_change_style);
        rl_change_style.setOnClickListener(this);
        rz_status_tv = findViewById(R.id.rz_status_tv);
        userset_ll = $(R.id.userset_ll);
        tv_user_name = findViewById(R.id.tv_user_name);
        rl_safe_psw = findViewById(R.id.rl_safe_psw);
        rl_login_psw = findViewById(R.id.rl_login_psw);
        rl_bind_email = findViewById(R.id.rl_bind_email);
        rl_bind_phone = findViewById(R.id.rl_bind_phone);
        rl_guge_renz = findViewById(R.id.rl_guge_renz);
        rl_help_style = findViewById(R.id.rl_help_style);
        rl_verify_method = findViewById(R.id.rl_verify_method);
        rl_help_language = findViewById(R.id.rl_help_language);
        phone_status_tv = findViewById(R.id.phone_status_tv);
        email_status_tv = findViewById(R.id.email_status_tv);
        guge_status_tv = findViewById(R.id.guge_status_tv);
        rl_help_style.setOnClickListener(this);
        rl_help_language.setOnClickListener(this);
        if(fUserInfoEntity.getIs_identity().equals("1")) {
            rz_status_tv.setText(getText(R.string.user_center_tv8));
        }else {
            rz_status_tv.setText(getText(R.string.user_center_tv7));
        }
        $(R.id.identify_ly).setOnClickListener(this);
        $(R.id.r_name_rl).setOnClickListener(this);
        if (ToolUtils.isNull(fUserInfoEntity.getNick_name())) {
            tv_user_name.setText(fUserInfoEntity.getUser_name());
        } else {
            tv_user_name.setText(fUserInfoEntity.getNick_name());
        }
        rl_security_verification.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getVerify();
        if (fUserInfoEntity != null) {
            userset_ll.setVisibility(View.VISIBLE);
            rl_pay_methed = findViewById(R.id.rl_pay_methed);


            rl_safe_psw.setOnClickListener(this);
            rl_login_psw.setOnClickListener(this);
            rl_pay_methed.setOnClickListener(this);
            rl_verify_method.setOnClickListener(this);

        } else {
            userset_ll.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_safe_psw:
                if (ToolUtils.isFastClick(view.getId())){
                    gotoActivity(ChangePwdAct.class);
                }

                break;
            case R.id.rl_login_psw:
                if (ToolUtils.isFastClick(view.getId())){
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("loginpsw", true);
                    gotoActivity(SafePswAct.class, false, bundle);
                }

                break;
            case R.id.rl_bind_email:
                String email = presenter.verifyEntity.getVerify().getEmail();
                if (email.equals("0")) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putBoolean("isBindEmail", true);
                    gotoActivity(BindActivity.class, false, bundle1);
                } else {
                    VerifyAct.startByType(getMContext(), "e", presenter.verifyEntity);
                }
                break;
            case R.id.rl_bind_phone:
                String phone = presenter.verifyEntity.getVerify().getMobile();
                if (ToolUtils.isFastClick(view.getId())){
                    if (phone.equals("0")) {
                        gotoActivity(BindActivity.class);
                    } else {
                        VerifyAct.startByType(getMContext(), "m", presenter.verifyEntity);
                    }
                }


                break;
            case R.id.rl_change_style:
                showCustomDialog();
                break;
            case R.id.rl_pay_methed:
                if (ToolUtils.isFastClick(view.getId())){
                    gotoActivity(PayMethedAct.class);
                }

                break;
            case R.id.rl_guge_renz:
                String guge = presenter.verifyEntity.getVerify().getGoogle();
                if (ToolUtils.isFastClick(view.getId())){
                    if (guge.equals("0")) {
                        gotoActivity(GoogleRenzAct.class);
                    } else {
                        VerifyAct.startByType(getMContext(), "g", presenter.verifyEntity);
                    }
                }

                break;
            case R.id.rl_verify_method:
                if (ToolUtils.isFastClick(view.getId())){
                    gotoActivity(VerifyMethodAct.class);
                }

                break;
            case R.id.rl_help_language:
                showLanguangeType();
                break;
            case R.id.rl_help_style:
                if (ToolUtils.isFastClick(view.getId())){
                    HelpAct.startAct(getMContext(), "https://ueofficial.zendesk.com");
                }

                break;
            case R.id.identify_ly:
                if (fUserInfoEntity.getIs_identity().equals("1")) {
                    T.showShort(getMContext(), getString(R.string.mysele_renz));
                } else {
                    presenter.getIdentityInfo();
                }
                break;
            case R.id.r_name_rl:
                if (ToolUtils.isFastClick(view.getId())){
                    Intent intent = new Intent(getMContext(),RNameAct.class);
                    intent.putExtra("name",fUserInfoEntity.getNick_name());
                    startActivityForResult(intent,101);
                }

                break;
            case R.id.rl_security_verification:
                if (ToolUtils.isFastClick(view.getId())){
                    gotoActivity(SecurityVerificationAct.class);
                }

                break;
        }
    }


    /**
     * 更改应用语言
     *
     * @param newLanguage
     */
    public void changeAppLanguage(String newLanguage) {
        ACache.get(this).put(ACEConstant.CURRENTLANGUAGE_ID, newLanguage);
        AppLanguageUtils.changeAppLanguage(this, newLanguage);
        AppLanguageUtils.changeAppLanguage(MAppliaction.getApp(), newLanguage);
        //重新启动Activity
        Intent intent = new Intent(this, MainAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * 切换语言
     */
    private void showLanguangeType() {
        List<String> langs = new ArrayList<>();
        langs.add("简体");
        langs.add("繁體");
        langs.add("English");
        ToolUtils.showDialog(this, new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        changeAppLanguage(ACEConstant.LANGUAGE_SIMPLIFIED_CHINESE);
                        break;
                    case 1:
                        changeAppLanguage(ACEConstant.LANGUAGE_TRADITIONAL_CHINESE);
                        break;
                    case 2:
                        changeAppLanguage(ACEConstant.LANGUAGE_ENGLISH);
                        break;
                }
            }
        }, langs);
    }

    /**
     * 切换风格
     */
    private void showCustomDialog() {
        final List<String> names = new ArrayList<>();
        names.add(getString(R.string.UserCenterAct_witle_height));
        names.add(getString(R.string.UserCenterAct_black_low));
        ToolUtils.showDialog(UserCenterAct.this, new CustomSelectDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ACache.get(UserCenterAct.this).put(ACEConstant.ACE_THEMEMODE, NORConstant.DAY_THEME);
                        break;
                    case 1:
                        ACache.get(UserCenterAct.this).put(ACEConstant.ACE_THEMEMODE, NORConstant.NIGHT_THEME);
                        break;
                    default:
                        break;
                }
                Intent intent = new Intent(UserCenterAct.this, MainAct.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }, names);
    }

    @Override
    public void setVerifyData(VerifyEntity entity) {
        List<String> status = new ArrayList<>();
        status.add(getString(R.string.user_center_tv1));
        status.add(getString(R.string.user_center_tv2));
        status.add(getString(R.string.user_center_tv3));
        rl_bind_phone.setOnClickListener(this);
        rl_bind_email.setOnClickListener(this);
        rl_guge_renz.setOnClickListener(this);
        phone_status_tv.setText(status.get(Integer.valueOf(entity.getVerify().getMobile())));
        email_status_tv.setText(status.get(Integer.valueOf(entity.getVerify().getEmail())));
        guge_status_tv.setText(status.get(Integer.valueOf(entity.getVerify().getGoogle())));

    }
    @Override
    public void getidentifyInfo(NewIdentifyEntity entity) {
        Bundle bundle = new Bundle();
        bundle.putString("country", fUserInfoEntity.getCountry());
        if (entity.getAble().equals("true")) {
            if(entity.getIdentity_info()!=null){
                bundle.putSerializable("entity", entity);
            }
            gotoActivity(UserIdentifyAct.class, false, bundle);
        } else {
            T.showShort(getMContext(),entity.getTips());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode ==1){
            String name = data.getStringExtra("name");
            fUserInfoEntity.setNick_name(name);
                tv_user_name.setText(name);
        }
    }
}
