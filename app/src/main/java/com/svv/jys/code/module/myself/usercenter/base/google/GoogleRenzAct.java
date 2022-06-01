package com.svv.jys.code.module.myself.usercenter.base.google;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.GoogleInfoEntity;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.base.google.model.GoogleRenzModel;
import com.svv.jys.code.module.myself.usercenter.base.google.presenter.GoogleRenzPresenter;
import com.svv.jys.code.module.myself.usercenter.base.google.view.GoogleRenzView;
import com.svv.jys.code.module.zxing.encoding.EncodingHandler;

/**
 * Created by js on 2018/6/22.
 */

public class GoogleRenzAct extends MvpActivity<GoogleRenzView,GoogleRenzModel,GoogleRenzPresenter> implements GoogleRenzView, View.OnClickListener, TextWatcher {
    private ImageView google_rwcode;
    private TextView tv_miyao,tv_fuzhi,tv_bind_google;
    private EditText et_google_code;
    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public GoogleRenzPresenter initPresenter() {
        return new GoogleRenzPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_google_renz;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.google11));
        google_rwcode=findViewById(R.id.google_rwcode);
        tv_miyao=findViewById(R.id.tv_miyao);
        tv_fuzhi=findViewById(R.id.tv_fuzhi);
        tv_bind_google=findViewById(R.id.tv_bind_google);
        et_google_code=findViewById(R.id.et_google_code);
        tv_fuzhi.setOnClickListener(this);
        tv_bind_google.setOnClickListener(this);
        tv_bind_google.setSelected(false);
        et_google_code.addTextChangedListener(this);
    }

    @Override
    public void doBusiness() {
        presenter.getGoogleInfo();
    }

    @Override
    public void setGoogleInfo(GoogleInfoEntity entity) {
        try {
            Bitmap mBitmap = EncodingHandler.createQRCode(entity.getGoogle_logo(), 200);
            google_rwcode.setImageBitmap(mBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
            GlideUtils.loadImageDefult(getMContext(), "", google_rwcode);
        }
        tv_miyao.setText(entity.getGoogle_secret());
    }

    @Override
    public void googleRenzSuccese() {
        ACache.get(this).put("google","1");
        T.showShort(this,getString(R.string.GoogleRenzAct_bind_success));
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_fuzhi:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tv_miyao.getText());
                T.showShort(GoogleRenzAct.this, getString(R.string.assat24));
                break;
            case R.id.tv_bind_google:
                String code=et_google_code.getText().toString().trim();
                if (ToolUtils.isNull(code)){
                    T.showShort(this,getString(R.string.GoogleRenzAct_please_gg_code));
                    return;
                }
                presenter.setGoogleRenz(code);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(!TextUtils.isEmpty(et_google_code.getText().toString())){
            tv_bind_google.setSelected(true);
        }else {
            tv_bind_google.setSelected(false);
        }
    }
}
