package com.svv.jys.code.module.myself.myasset.otc.otctrading.code;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BaseAcitvity;
import com.svv.jys.code.common.utils.GlideUtils;

public class CodeAct extends BaseAcitvity {
    private String url;
    private ImageView code_iv;
    public static void startByUrl(Context context, String url) {
        Intent intent = new Intent(context, CodeAct.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    public void baseInitialization() {
        url = getIntent().getStringExtra("url");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_code;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        code_iv = (ImageView) $(R.id.code_iv);
        GlideUtils.loadImageDefult(this,url,code_iv);
    }

    @Override
    public void doBusiness() {

    }
}
