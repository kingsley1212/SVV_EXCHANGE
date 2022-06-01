package com.svv.jys.code.module.server.chatserver.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


import com.svv.jys.R;
import com.svv.jys.code.common.utils.GlideUtils;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by lzj on 2017/11/10.
 */

public class UserImgStatusView extends FrameLayout {

    private View mainView;
    private CircleImageView userImg;
    private View user_status;

    public UserImgStatusView(Context context) {
        super(context);
        init(context);
    }

    public UserImgStatusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UserImgStatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public UserImgStatusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        // 加载布局
        mainView = LayoutInflater.from(context).inflate(R.layout.view_onlineciv, this);
        userImg = (CircleImageView) mainView.findViewById(R.id.userImg);
        user_status = mainView.findViewById(R.id.user_status);
    }


    public void loadImage(Context context, String url) {
        try {
            GlideUtils.loadUserImageDefult(context, url, userImg);
        } catch (Exception e) {

        }
    }

    public void hideStatus(){
        this.user_status.setVisibility(View.GONE);
    }

    public void setStatus(boolean online){
        user_status.setSelected(online);
    }

    public void setOnLine() {
        user_status.setSelected(true);
    }

    public void setOffLine() {
        user_status.setSelected(false);
    }
}