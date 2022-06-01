package com.svv.jys.code.module.myself.login.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.entity.FToken;
import com.svv.jys.code.common.utils.CountDownMsgUtils;
import com.svv.jys.code.common.utils.StatusBarUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupSelectItem2;
import com.svv.jys.code.module.myself.country.CountryAct;
import com.svv.jys.code.module.myself.login.base.model.IRegisterModel;
import com.svv.jys.code.module.myself.login.base.presenter.RegisterPresenter;
import com.svv.jys.code.module.myself.login.base.view.IRegisterView;
import com.svv.jys.code.module.myself.login.xieyi.XieyiAct;

import java.util.ArrayList;
import java.util.List;


/**
 * 作为注册页面
 * Created by Administrator on 2017/10/21 0021.
 */

public class RegisterAct extends MvpActivity<IRegisterView, IRegisterModel, RegisterPresenter> implements IRegisterView, EditText.OnFocusChangeListener {

    private String codeType = RegisterPresenter.EMAIL_REGISTER_TYPE;
    private View rl_quhao, rl_email, rl_phone, pwd_eye_iv, re_eye_iv;
    private View leftLine, rightLine, rightLyDisplay;
    private TextView leftTabText, rightTabText, tv_country, tv_country_code;
    private EditText right_mobile_et, right_pwd_et, right_repwd_et, right_checkCode_et, right_email_et, et_yaoqingma;
    private TextView leftBtnCommit, rightCommit, getCheckCode, tv_xieyi;
    private Boolean bol = true;
    private CountDownMsgUtils countDownMsgUtils;
    private CountryEntity entity = new CountryEntity("China", "86", "中国", "1");
    private View guojia_line, mobile_line, email_line, code_line, pwd_line, sec_pwd_line, tuijianren_line;
    boolean isCountry;

    @Override
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_register;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        StatusBarUtils.setImmersiveStatusBar(this, true);
        TextView right_tv = (TextView) $(R.id.right_tv);
        right_tv.setText(getString(R.string.login_existing_account));
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pwd_eye_iv = $(R.id.pwd_eye_iv);
        re_eye_iv = $(R.id.re_eye_iv);
        et_yaoqingma = (EditText) $(R.id.et_yaoqingma);
        tv_country_code = (TextView) $(R.id.tv_country_code);
        tv_xieyi = (TextView) $(R.id.tv_xieyi);
        tv_xieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(XieyiAct.class);
            }
        });
        tv_country = (TextView) $(R.id.tv_country);
        //tab栏及展示的相应布局
        leftTabText = (TextView) $(R.id.leftTabText);
        rightTabText = (TextView) $(R.id.rightTabText);
        leftLine = $(R.id.leftLine);
        rightLine = $(R.id.rightLine);
        /**
         * 输入下面的高亮
         */
        guojia_line = $(R.id.guojia_line);
        mobile_line = $(R.id.mobile_line);
        email_line = $(R.id.email_line);
        code_line = $(R.id.code_line);
        pwd_line = $(R.id.pwd_line);
        sec_pwd_line = $(R.id.sec_pwd_line);
        tuijianren_line = $(R.id.tuijianren_line);


        leftLine.setSelected(false);
        rightLine.setSelected(false);
        rightLyDisplay = $(R.id.rightLyDisplay);
        leftTabText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabDisplay(true);
            }
        });
        rightTabText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabDisplay(false);
            }
        });

        leftBtnCommit = (TextView) $(R.id.leftBtnCommit);
        leftBtnCommit.setSelected(true);

        leftBtnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        tv_country_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    startActivityForResult(new Intent(RegisterAct.this, CountryAct.class), 100);
                }

            }
        });
        rl_quhao = $(R.id.rl_quhao);
        rl_email = $(R.id.rl_email);
        rl_phone = $(R.id.rl_phone);
        right_email_et = (EditText) $(R.id.right_email_et);
        right_mobile_et = (EditText) $(R.id.right_mobile_et);
        right_pwd_et = (EditText) $(R.id.right_pwd_et);
        right_repwd_et = (EditText) $(R.id.right_repwd_et);
        ToolUtils.setEditTextInhibitInputSpace(right_pwd_et);
        ToolUtils.setEditTextInhibitInputSpace(right_repwd_et);
        right_checkCode_et = (EditText) $(R.id.right_checkCode_et);
        rightCommit = (TextView) $(R.id.rightCommit);
        rightCommit.setSelected(true);
        getCheckCode = (TextView) $(R.id.getCheckCode);
        countDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_DAOJISHI, this, 60, getCheckCode);
        tv_country_code.setText("+86");
        rightCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isNull(codeType)) {
                    return;
                }

                if (tv_country.getText().toString().equals(getString(R.string.register2))) {
                    T.showShort(RegisterAct.this, getString(R.string.register3));
                    return;
                }

                if (codeType.equals(RegisterPresenter.MOBILE_REGISTER_TYPE)) {
                    if (tv_country_code.getText().toString().equals(getString(R.string.register4))) {
                        T.showShort(RegisterAct.this, getString(R.string.register4));
                        return;
                    }
                }

                switch (codeType) {
                    case RegisterPresenter.MOBILE_REGISTER_TYPE:
                        if (ToolUtils.isNull(getPhone())) {
                            T.showShort(RegisterAct.this, getString(R.string.register_phone_prompt));
                            return;
                        }
                        break;
                    case RegisterPresenter.EMAIL_REGISTER_TYPE:
                        if (ToolUtils.isNull(getEmail())) {
                            T.showShort(RegisterAct.this, getString(R.string.register_email_prompt));
                            return;
                        }
                        break;
                    default:
                        break;
                }

                if (ToolUtils.isNull(getcode())) {
                    T.showShort(getMContext(), getString(R.string.register_code_prompt));
                    return;
                }
                if (ToolUtils.isNull(getPsw())) {
                    T.showShort(getMContext(), getString(R.string.register_psw_prompt));
                    return;
                }
                if (ToolUtils.isNull(getrePsw())) {
                    T.showShort(getMContext(), getString(R.string.register_psw_agian_prompt));
                    return;
                }
                presenter.doregister();
            }
        });

        getCheckCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_country.getText().toString().equals(getString(R.string.register2))) {
                    T.showShort(RegisterAct.this, getString(R.string.register3));
                    return;
                }
                String mobile = right_mobile_et.getText().toString().trim();
                if (ToolUtils.isNull(codeType)) {
                    return;
                }

                switch (codeType) {
                    case RegisterPresenter.MOBILE_REGISTER_TYPE:
                        if (tv_country_code.getText().toString().equals(getString(R.string.register4))) {
                            T.showShort(RegisterAct.this, getString(R.string.register4));
                            return;
                        }
                        countDownMsgUtils.requestCheckCode(mobile, new CountDownMsgUtils.ICountDownPostCode() {
                            @Override
                            public void allowToRequestCode() {
                                presenter.docode();
                            }
                        });
                        break;
                    case RegisterPresenter.EMAIL_REGISTER_TYPE:
                        if (ToolUtils.isNull(getEmail())) {
                            T.showShort(RegisterAct.this, getString(R.string.register_email_prompt));
                        } else {
                            presenter.docode();
                        }

                        break;
                    default:
                        break;
                }

            }
        });
        rl_quhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                right_mobile_et.clearFocus();
                rl_quhao.setFocusable(true);
                rl_quhao.setFocusableInTouchMode(true);
                setHeightLightLine(6);
                if (ToolUtils.isFastClick(view.getId())) {
                    startActivityForResult(new Intent(RegisterAct.this, CountryAct.class), 200);
                }

            }
        });

        pwd_eye_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwd_eye_iv.setSelected(!pwd_eye_iv.isSelected());
                if (pwd_eye_iv.isSelected()) {
                    right_pwd_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    right_pwd_et.setSelection(right_pwd_et.getText().toString().length());
                } else {
                    right_pwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    right_pwd_et.setSelection(right_pwd_et.getText().toString().length());
                }
            }
        });
        re_eye_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                re_eye_iv.setSelected(!re_eye_iv.isSelected());
                if (re_eye_iv.isSelected()) {
                    right_repwd_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    right_repwd_et.setSelection(right_repwd_et.getText().toString().length());
                } else {
                    right_repwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    right_repwd_et.setSelection(right_repwd_et.getText().toString().length());
                }
            }
        });
        ToolUtils.setButtone(rightCommit, new EditText[]{right_checkCode_et, right_email_et, right_mobile_et, right_pwd_et, right_repwd_et});
        right_mobile_et.setOnFocusChangeListener(this);
        right_email_et.setOnFocusChangeListener(this);
        right_checkCode_et.setOnFocusChangeListener(this);
        right_pwd_et.setOnFocusChangeListener(this);
        right_repwd_et.setOnFocusChangeListener(this);
        et_yaoqingma.setOnFocusChangeListener(this);
        /**
         * 默认国家高亮
         */
        setHeightLightLine(6);

        /**
         * 设置注册协议下面的不同颜色的字体，
         */
        String registerXieyi = getString(R.string.login_protocal2);
        SpannableString spannableString = new SpannableString(registerXieyi);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(getMContext(), R.color.c_1a456d));
        spannableString.setSpan(colorSpan, registerXieyi.indexOf("《"), registerXieyi.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv_xieyi.setText(spannableString);
    }

    @Override
    public void doBusiness() {
        rightTabText.performClick();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == ACEConstant.REQUEST_OK) {
            entity = (CountryEntity) data.getSerializableExtra("countriesEntity");
            tv_country.setText(entity.getChinese() + "(" + entity.getName() + ")");
        } else if (requestCode == 100 && resultCode == ACEConstant.REQUEST_OK) {
            entity = (CountryEntity) data.getSerializableExtra("countriesEntity");
            tv_country_code.setText("+" + entity.getCode());
        }
    }

    /**
     * 展示不同的界面
     *
     * @param flag: true 邮箱登录，false 手机登录
     */
    private void changeTabDisplay(boolean flag) {
        right_pwd_et.setText("");
        right_repwd_et.setText("");
        right_pwd_et.setText("");
        right_mobile_et.setText("");
        right_email_et.setText("");
        right_checkCode_et.setText("");
        ACache.get(this).remove(ACEConstant.ACE_DAOJISHI);
        getCheckCode.setText(getString(R.string.login_get_checkcode));
        countDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_DAOJISHI, this, 60, getCheckCode);
        bol = flag;
        if (flag) {
            codeType = "1";
//            right_email_et.requestFocus();
//            leftLine.setSelected(true);
//            rightLine.setSelected(false);
            rightLine.setVisibility(View.INVISIBLE);
            leftLine.setVisibility(View.VISIBLE);
            leftTabText.setSelected(true);
            rightTabText.setSelected(false);
            rl_phone.setVisibility(View.GONE);
            rl_email.setVisibility(View.VISIBLE);

        } else {
            codeType = "0";
            leftLine.setVisibility(View.INVISIBLE);
            rightLine.setVisibility(View.VISIBLE);
//            leftLine.setSelected(false);
//            rightLine.setSelected(true);
            leftTabText.setSelected(false);
            rightTabText.setSelected(true);
            rl_phone.setVisibility(View.VISIBLE);
            rl_email.setVisibility(View.GONE);
//            leftLyDisplay.setVisibility(View.GONE);
//            rightLyDisplay.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Context getMContext() {
        return this;
    }


    @Override
    public String getPhone() {
        return right_mobile_et.getText().toString().trim();
    }

    @Override
    public String getPsw() {
        return right_pwd_et.getText().toString().trim();
    }

    @Override
    public String getrePsw() {
        return right_repwd_et.getText().toString().trim();
    }

    @Override
    public String getcode() {
        return right_checkCode_et.getText().toString().trim();
    }

    @Override
    public String getArea() {
        return entity.getCode();
    }

    @Override
    public String getCountry() {
        return entity.getId();
    }

    @Override
    public void successCode() {
        countDownMsgUtils.requestSuccess();
    }

    @Override
    public void registerSuccese() {
        T.showShort(this, getString(R.string.register_succese));
        finish();
    }

    @Override
    public String getCodeTyoe() {
        return codeType;
    }

    @Override
    public String getEmail() {
        return right_email_et.getText().toString().trim();
    }

    @Override
    public void setCountry(final List<CountryEntity> list) {
        if (isCountry) {
            final List<String> countrys = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                countrys.add(list.get(i).getChinese() + "(" + list.get(i).getName() + ")");
            }
            PopupSelectItem2 popupSelectItem1 = new PopupSelectItem2(this, countrys, new PopupSelectItem2.OnItemSelect2() {
                @Override
                public void onItemSelet(int position, Object o) {
                    entity = list.get(position);
                    tv_country.setText(countrys.get(position));
                }
            });
            popupSelectItem1.showPop(rl_quhao);
        } else {
            final List<String> countrys_code = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                countrys_code.add(list.get(i).getChinese() + "(" + list.get(i).getCode() + ")");
            }
            PopupSelectItem2 popupSelectItem1 = new PopupSelectItem2(this, countrys_code, new PopupSelectItem2.OnItemSelect2() {
                @Override
                public void onItemSelet(int position, Object o) {
                    entity = list.get(position);
                    tv_country_code.setText("+" + list.get(position).getCode());
                }
            });
            popupSelectItem1.showPop(rightLyDisplay);
        }

    }


    @Override
    public void finishActivity() {
        finish();
    }


    @Override
    public void doLoginInSocket(FToken token) {

    }

    @Override
    protected void onDestroy() {
        rightCommit.setTag(null);
        super.onDestroy();
    }

    @Override
    public String getYaoQingMa() {
        return et_yaoqingma.getText().toString().trim();
    }


    private void setHeightLightLine(int i) {
        guojia_line.setSelected(false);
        mobile_line.setSelected(false);
        email_line.setSelected(false);
        code_line.setSelected(false);
        pwd_line.setSelected(false);
        sec_pwd_line.setSelected(false);
        tuijianren_line.setSelected(false);
        switch (i) {
            case 0:
                mobile_line.setSelected(true);
                break;
            case 1:
                email_line.setSelected(true);
                break;
            case 2:
                code_line.setSelected(true);
                break;
            case 3:
                pwd_line.setSelected(true);
                break;
            case 4:
                sec_pwd_line.setSelected(true);
                break;
            case 5:
                tuijianren_line.setSelected(true);
                break;
            case 6:
                guojia_line.setSelected(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.right_mobile_et:
                if (hasFocus) {
                    setHeightLightLine(0);
                }
                break;
            case R.id.right_email_et:
                if (hasFocus) {
                    setHeightLightLine(1);
                }
                break;
            case R.id.right_checkCode_et:
                if (hasFocus) {
                    setHeightLightLine(2);
                }
                break;
            case R.id.right_pwd_et:
                if (hasFocus) {
                    setHeightLightLine(3);
                }
                break;
            case R.id.right_repwd_et:
                if (hasFocus) {
                    setHeightLightLine(4);
                }
                break;
            case R.id.et_yaoqingma:
                if (hasFocus) {
                    setHeightLightLine(5);
                }
                break;
        }
    }
}
