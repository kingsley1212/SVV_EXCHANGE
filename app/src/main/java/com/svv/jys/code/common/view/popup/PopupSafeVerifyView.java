package com.svv.jys.code.common.view.popup;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.R;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.utils.CountDownMsgUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.net.api.API_Factory;
import com.svv.jys.code.module.net.req.POST_CHECK_VERIFY;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_GETVERIFY;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class PopupSafeVerifyView implements TextWatcher {

    public PopupWindow popupWindow;
    private Context context;
    private View phone_ll, email_ll, cancel_tv, parent, rightCommit, guge_ll;
    private TextView email_send_code_tv, phone_send_code_tv;
    private EditText phone_et, email_et, guge_et;
    private checkVerifyListener listener;
    private CountDownMsgUtils mobileUtils,emailUtils;


    public PopupSafeVerifyView(Context activity, checkVerifyListener listener) {
        this.context = activity;
        this.listener = listener;
        initPopupView();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (TextUtils.isEmpty(s)) {
            rightCommit.setSelected(false);
            return;
        }
        if ((TextUtils.isEmpty(phone_et.getText()) && phone_et.isShown())
                || (TextUtils.isEmpty(email_et.getText()) && email_et.isShown())
                || (TextUtils.isEmpty(guge_et.getText()) && guge_et.isShown())) {
            rightCommit.setSelected(false);
        } else {
            rightCommit.setSelected(true);
        }
    }

    public interface checkVerifyListener {
        void checkSuccess();
    }


    private void initPopupView() {
        final View popView = LayoutInflater.from(context).inflate(
                R.layout.act_safe_verify, null);
        phone_ll = popView.findViewById(R.id.phone_ll);
        email_ll = popView.findViewById(R.id.email_ll);
        guge_ll = popView.findViewById(R.id.guge_ll);
        cancel_tv = popView.findViewById(R.id.cancel_tv);
        rightCommit = popView.findViewById(R.id.rightCommit);
        phone_et = popView.findViewById(R.id.phone_et);
        email_et = popView.findViewById(R.id.email_et);
        guge_et = popView.findViewById(R.id.guge_et);
        email_send_code_tv = popView.findViewById(R.id.email_send_code_tv);
        phone_send_code_tv = popView.findViewById(R.id.phone_send_code_tv);
        rightCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkVerify();
            }
        });
        email_send_code_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                docode("1", "3");
            }
        });
        phone_send_code_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                docode("0", "3");
            }
        });
        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置popwindow出现和消失动画
        popupWindow.setAnimationStyle(R.style.PopMenuAnimation2);

        popupWindow.setBackgroundDrawable(BitmapDrawable.createFromPath(null));
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });
        popupWindow.setOutsideTouchable(true);
        guge_et.addTextChangedListener(this);
        phone_et.addTextChangedListener(this);
        email_et.addTextChangedListener(this);
        mobileUtils = new CountDownMsgUtils(ACEConstant.VERIFY_MOBILE, context, 60, phone_send_code_tv);
        emailUtils = new CountDownMsgUtils(ACEConstant.VERIFY_EMAIL, context, 60, email_send_code_tv);
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        ((Activity) context).getWindow().setAttributes(lp);
    }


    /**
     * 显示popWindow
     */
    public void showPop(View parent, String type) {
        // 获取弹框的真实高度
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        // 获取popwindow焦点
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        this.parent = parent;
        email_et.setText("");
        phone_et.setText("");
        getVerify(type);
    }

    public void dismiss() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }


    public void getVerify(String type) {
        POST_GETVERIFY req = new POST_GETVERIFY();
        req.type = type;
        API_Factory.API_GetUserVerify(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, JSONObject>() {
            @Override
            public JSONObject call(BaseResponse baseResponse) {
                return JSON.parseObject(baseResponse.datas);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<JSONObject>() {
            @Override

            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(context, e, true, true);
            }

            @Override
            public void onNext(JSONObject object) {
                showAfterRequest(object);
            }
        });
    }

    public void showAfterRequest(JSONObject value) {
        boolean phone = value.getString("0").equals("true");
        boolean email = value.getString("1").equals("true");
        boolean guge = value.getString("2").equals("true");
        if (phone) {
            phone_ll.setVisibility(View.VISIBLE);
        } else {
            phone_ll.setVisibility(View.GONE);
        }
        if (email) {
            email_ll.setVisibility(View.VISIBLE);
        } else {
            email_ll.setVisibility(View.GONE);
        }
        if (guge) {
            guge_ll.setVisibility(View.VISIBLE);
        } else {
            guge_ll.setVisibility(View.GONE);

        }
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        if (Build.VERSION.SDK_INT < 24) {
            popupWindow.update();
        } else {
            popupWindow.dismiss();
            popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        }
        backgroundAlpha(0.5f);
    }

    public void docode(final String type, String send) {
        POST_CODE_REQ req = new POST_CODE_REQ();
        req.type = type;
        req.send = send;

        API_Factory.API_DoCode(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                return baseResponse.msg;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override

            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(context, e, true, true);

            }

            @Override
            public void onNext(String s) {
                if (!ToolUtils.isNull(s)) {
                    T.showLong(context, s);
                }
                switch (type){
                    case "0":
                        mobileUtils.requestSuccess();
                        break;
                    case "1":
                        emailUtils.requestSuccess();
                        break;
                }
            }
        });
    }

    public void checkVerify() {
        POST_CHECK_VERIFY req = new POST_CHECK_VERIFY();
        req.setMobile(phone_et.getText().toString());
        req.setEmail(email_et.getText().toString());
        req.setGoogle(guge_et.getText().toString());

        API_Factory.API_CheckVerify(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {

            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, String>() {
            @Override
            public String call(BaseResponse baseResponse) {
                return baseResponse.msg;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override

            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(context, e, true, true);
                dismiss();
            }

            @Override
            public void onNext(String s) {
                listener.checkSuccess();
                dismiss();
            }
        });
    }

    public void setTagNull() {
    }

}
