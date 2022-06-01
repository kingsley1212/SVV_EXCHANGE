package com.svv.jys.code.module.myself.usercenter.base.verifymethod;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.utils.CountDownMsgUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.verifymethod.model.VerifyMethodModel;
import com.svv.jys.code.module.myself.usercenter.base.verifymethod.presenter.VerifyMethodPresenter;
import com.svv.jys.code.module.myself.usercenter.base.verifymethod.view.VerifyMethodView;

/**
 * Created by js on 2018/9/7.
 */

public class VerifyMethodAct extends MvpActivity<VerifyMethodView,VerifyMethodModel,VerifyMethodPresenter> implements VerifyMethodView, View.OnClickListener {
    private ImageView iv_email_verify,iv_phone_verify,iv_google_verify;
    private String key;
    private PopupWindow popupWindow;
    private String code,google_code,email_code;
    private EditText et_code,et_google,et_email_code;
    private TextView tv_send_email_code;
    private TextView tv_send_code;
    private CountDownMsgUtils phoneCountDownMsgUtils;
    private CountDownMsgUtils emailCountDownMsgUtils;
    private boolean isEmail;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public VerifyMethodPresenter initPresenter() {
        return new VerifyMethodPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_verify_method;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.verify_method));
        iv_email_verify=findViewById(R.id.iv_email_verify);
        iv_phone_verify=findViewById(R.id.iv_phone_verify);
        iv_google_verify=findViewById(R.id.iv_google_verify);
        iv_email_verify.setOnClickListener(this);
        iv_phone_verify.setOnClickListener(this);
        iv_google_verify.setOnClickListener(this);
        if (ACache.get(this).getAsString(ACEConstant.VERIFY_MOBILE).equals("1")){
            iv_phone_verify.setSelected(true);
        }
        if (ACache.get(this).getAsString(ACEConstant.VERIFY_EMAIL).equals("1")){
            iv_email_verify.setSelected(true);
        }
        if (ACache.get(this).getAsString(ACEConstant.VERIFY_GOOGLE).equals("1")){
            iv_google_verify.setSelected(true);
        }
//        if (!ToolUtils.isNull(ACache.get(this).getAsString(ACEConstant.ACE_WERIFY_KEY))){
//            if(ACache.get(this).getAsString(ACEConstant.ACE_WERIFY_KEY).equals("mobile")){
//                iv_phone_verify.setSelected(true);
//            }else if (ACache.get(this).getAsString(ACEConstant.ACE_WERIFY_KEY).equals("email")){
//                iv_email_verify.setSelected(true);
//            }else if (ACache.get(this).getAsString(ACEConstant.ACE_WERIFY_KEY).equals("google")){
//                iv_google_verify.setSelected(true);
//            }
//        }
    }

    @Override
    public void doBusiness() {

    }


    @Override
    public void setVerifySuccese() {
        popupWindow.dismiss();
        switch (key){
            case "email":
                iv_email_verify.setSelected(!iv_email_verify.isSelected());
                ACache.get(getMContext()).put(ACEConstant.VERIFY_EMAIL,iv_email_verify.isSelected()?"1":"0");//验证码类型
                break;
            case "mobile":
                iv_phone_verify.setSelected(!iv_phone_verify.isSelected());
                ACache.get(getMContext()).put(ACEConstant.VERIFY_MOBILE,iv_phone_verify.isSelected()?"1":"0");//验证码类型
                break;
            case "google":
                iv_google_verify.setSelected(!iv_google_verify.isSelected());
                ACache.get(getMContext()).put(ACEConstant.VERIFY_GOOGLE,iv_phone_verify.isSelected()?"1":"0");//验证码类型
                break;
        }
    }

    @Override
    public void successCode() {
        if (isEmail){
            emailCountDownMsgUtils.requestSuccess();
        }else {
            phoneCountDownMsgUtils.requestSuccess();
        }
    }



    private String type;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_email_verify:
                key="email";
                if ( ToolUtils.isNull(ACache.get(this).getAsString(ACEConstant.ACE_USERINFO_EMAIL))){
                    T.showShort(this,getString(R.string.xian_bind_email));
                }else {
                    if (!ACache.get(this).getAsString(ACEConstant.VERIFY_EMAIL).equals("1")){
                        type="1";
                    }else {
                        type="2";
                    }
                    showPop();
                }

                break;
            case R.id.iv_phone_verify:
                key="mobile";
                if ( ToolUtils.isNull(ACache.get(this).getAsString(ACEConstant.ACE_USERINFO_MOBILE))){
                    T.showShort(this,getString(R.string.xian_bind_phone));
                }else {
                    if (!ACache.get(this).getAsString(ACEConstant.VERIFY_MOBILE).equals("1")) {
                        type = "1";
                    } else {
                        type = "2";
                    }
                    showPop();
                }
                break;
            case R.id.iv_google_verify:
                key="google";
                if (ACache.get(this).getAsString(ACEConstant.IS_GOOGLE).equals("0")){
                    T.showShort(this,getString(R.string.xian_bind_google));
                }else {
                    if (!ACache.get(this).getAsString(ACEConstant.VERIFY_GOOGLE).equals("1")){
                        type="1";
                    }else {
                        type="2";
                    }
                    showPop();
                }

                break;
            case R.id.tv_send_code:
                isEmail=false;
                presenter.getCode("0");
                break;
            case R.id.tv_send_email_code:
                isEmail=true;
                presenter.getCode("1");
                break;
            case R.id.tv_quren:
                code = et_code.getText().toString().trim();
                google_code=et_google.getText().toString().trim();
                email_code=et_email_code.getText().toString().trim();
                if (type.equals("1")){
                    if (key.equals("email")){
                        if (ToolUtils.isNull(email_code)){
                            return;
                        }
                    }else if (key.equals("mobile")){
                        if (ToolUtils.isNull(code)){
                            return;
                        }
                    }else if (key.equals("google")){
                        if (ToolUtils.isNull(google_code)){
                            return;
                        }
                    }
                }else {
                    if (ACache.get(this).getAsString(ACEConstant.VERIFY_MOBILE).equals("1")){
                        if (ToolUtils.isNull(code)){
                            return;
                        }
                    }
                    if (ACache.get(this).getAsString(ACEConstant.VERIFY_EMAIL).equals("1")){
                        if (ToolUtils.isNull(email_code)){
                            return;
                        }

                    }
                    if (ACache.get(this).getAsString(ACEConstant.VERIFY_GOOGLE).equals("1")){
                        if (ToolUtils.isNull(google_code)){
                            return;
                        }
                    }
                }

                presenter.setVerifyMethod(key,code,email_code,google_code,type);
                break;
            case R.id.tv_quxiao:
                popupWindow.dismiss();
                break;
        }
    }


    public void showPop() {
        popupWindow = new PopupWindow(this);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        final View popview = LayoutInflater.from(this).inflate(R.layout.pop_yanz, null);
        popupWindow.setContentView(popview);
        tv_send_code = popview.findViewById(R.id.tv_send_code);
        et_code = popview.findViewById(R.id.et_code);
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        tv_send_email_code = popview.findViewById(R.id.tv_send_email_code);
        tv_send_email_code.setOnClickListener(this);
        phoneCountDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_YANZ_PHONE_DJS, this, 60, tv_send_code);
        emailCountDownMsgUtils = new CountDownMsgUtils(ACEConstant.ACE_YANZ_EMAIL_DJS, this, 60, tv_send_email_code);
        TextView tv_quren=popview.findViewById(R.id.tv_quren);
        TextView tv_quxiao=popview.findViewById(R.id.tv_quxiao);
        tv_quxiao.setOnClickListener(this);
        tv_quren.setOnClickListener(this);
        tv_send_code.setOnClickListener(this);
        RelativeLayout rl_verify_code=popview.findViewById(R.id.rl_verify_code);
        RelativeLayout rl_email_code=popview.findViewById(R.id.rl_email_code);
        et_google=popview.findViewById(R.id.et_google);
        et_email_code=popview.findViewById(R.id.et_email_code);
        View view_email=popview.findViewById(R.id.view_email);
        View view_google=popview.findViewById(R.id.view_google);
        View view_phone=popview.findViewById(R.id.view_phone);
        if (type.equals("1")){
            if (key.equals("email")){
                view_email.setVisibility(View.VISIBLE);
                rl_email_code.setVisibility(View.VISIBLE);
            }else if (key.equals("mobile")){
                view_phone.setVisibility(View.VISIBLE);
                rl_verify_code.setVisibility(View.VISIBLE);
            }else if (key.equals("google")){
                view_google.setVisibility(View.VISIBLE);
                et_google.setVisibility(View.VISIBLE);
            }
        }else {
            if (ACache.get(this).getAsString(ACEConstant.VERIFY_MOBILE).equals("1")){
                view_phone.setVisibility(View.VISIBLE);
                rl_verify_code.setVisibility(View.VISIBLE);

            }
            if (ACache.get(this).getAsString(ACEConstant.VERIFY_EMAIL).equals("1")){
                view_email.setVisibility(View.VISIBLE);
                rl_email_code.setVisibility(View.VISIBLE);
            }

            if (ACache.get(this).getAsString(ACEConstant.VERIFY_GOOGLE).equals("1")){
                view_google.setVisibility(View.VISIBLE);
                et_google.setVisibility(View.VISIBLE);
            }
        }
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(popview, Gravity.BOTTOM,0,0);
    }
}
