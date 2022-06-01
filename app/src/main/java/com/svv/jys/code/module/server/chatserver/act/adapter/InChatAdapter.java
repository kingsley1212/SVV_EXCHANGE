package com.svv.jys.code.module.server.chatserver.act.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.svv.jys.R;
import com.svv.jys.code.common.base.MBaseAdapter;
import com.svv.jys.code.common.utils.BitmapUtil;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.server.chatserver.bean.FChatMessageEntity;
import com.svv.jys.code.module.server.chatserver.view.UserImgStatusView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lzj on 2018/1/5.
 */

public class InChatAdapter extends MBaseAdapter {

    public final static int ONLYTX_LY = 0;
    public final static int IMG_LY = 1;

    public Context context;
    public LayoutInflater mLayoutInflater;
    public List<FChatMessageEntity> fChatMessageEntities;

    private ImageWatcher vImageWatcher;

    public InChatAdapter(Context context) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.fChatMessageEntities = new ArrayList<>();

        vImageWatcher = ImageWatcher.Helper.with((Activity) context) // 一般来讲， ImageWatcher 需要占据全屏的位置
                .setTranslucentStatus(0) // 如果是透明状态栏，你需要给ImageWatcher标记 一个偏移值，以修正点击ImageView查看的启动动画的Y轴起点的不正确
                .setErrorImageRes(R.mipmap.error_picture) // 配置error图标 如果不介意使用lib自带的图标，并不一定要调用这个API
                .setOnPictureLongPressListener(new ImageWatcher.OnPictureLongPressListener() {
                    @Override
                    public void onPictureLongPress(ImageView v, String url, int pos) {

                    }
                }) // 长按图片的回调，你可以显示一个框继续提供一些复制，发送等功能
                .setLoader(new ImageWatcher.Loader() {
                    @Override
                    public void load(Context context, String url, final ImageWatcher.LoadCallback lc) {
                        final FChatMessageEntity chatMessageEntity = fChatMessageEntities.get(ToolUtils.String2Int
                                (url));
//                        lc.onResourceReady());
                        if (ToolUtils.isNull(chatMessageEntity.img)) {
                            if (chatMessageEntity.weakBitMap.get() != null) {
                                lc.onResourceReady(chatMessageEntity.weakBitMap.get());
                            } else {
                                rx.Observable.just(chatMessageEntity).observeOn(Schedulers.io()).map(new Func1<FChatMessageEntity, Boolean>() {

                                    @Override
                                    public Boolean call(FChatMessageEntity fChatMessageEntity) {
                                        fChatMessageEntity.weakBitMap = new WeakReference<Bitmap>(BitmapUtil
                                                .base64ToBitmap
                                                        (fChatMessageEntity.content));
                                        return true;
                                    }
                                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
                                    @Override
                                    public void call(Boolean aBoolean) {
                                        lc.onResourceReady(chatMessageEntity.weakBitMap.get());
                                    }
                                });
                            }
                        } else {
                            Glide.with(context).asBitmap().load(chatMessageEntity.img).listener(new RequestListener<Bitmap>() {


                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap>
                                        target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target,
                                                               DataSource dataSource, boolean isFirstResource) {
                                    lc.onResourceReady(resource);
                                    return true;
                                }
                            });
                        }
                    }
                })
                .create();
    }

    public void addMessage(FChatMessageEntity entity) {
        this.fChatMessageEntities.add(entity);
    }

    public void addOldMessages(List<FChatMessageEntity> fChatMessageEntities) {
        this.fChatMessageEntities.addAll( fChatMessageEntities);
    }

    public FChatMessageEntity getItem(int position) {
        try {
            return fChatMessageEntities.get(position);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ONLYTX_LY:
            default:
                return new TX_VH(mLayoutInflater.inflate(R.layout.item_inchat_tx, parent, false));
            case IMG_LY:
                return new IMG_VH(mLayoutInflater.inflate(R.layout.item_inchat_img, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TX_VH) {
            ((TX_VH) holder).onBind(position, getItem(position));
        } else if (holder instanceof IMG_VH) {
            ((IMG_VH) holder).onBind(position, getItem(position));
        }
    }

    @Override
    public int getItemCount() {
        return fChatMessageEntities.size();
    }

    @Override
    public int getItemViewType(int position) {
        int laytype = fChatMessageEntities.get(position).media_type;
        switch (laytype) {
            case FChatMessageEntity.IMG_MSG://图片模式
                return IMG_LY;
            case FChatMessageEntity.TX_MSG://纯文字
            default://默认
                return ONLYTX_LY;
        }
    }


    class M_VH extends RecyclerView.ViewHolder {

        public View itemView;
        public View other_rl, my_rl;
        public UserImgStatusView other_userImg_s, my_userImg_s;
        public TextView  sys_message,other_maddtime,my_maddtime;

        public M_VH(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.other_rl = itemView.findViewById(R.id.other_rl);
            this.my_rl = itemView.findViewById(R.id.my_rl);
            this.other_maddtime = (TextView) itemView.findViewById(R.id.other_maddtime);
            this.my_maddtime = (TextView) itemView.findViewById(R.id.my_maddtime);
            this.other_userImg_s = (UserImgStatusView) itemView.findViewById(R.id.other_userImg_s);
            this.my_userImg_s = (UserImgStatusView) itemView.findViewById(R.id.my_userImg_s);
            this.sys_message = (TextView) itemView.findViewById(R.id.sys_message);
        }

        public void onBind(int position, FChatMessageEntity chatMessageEntity) {

        }
    }

    class TX_VH extends M_VH {
        public TextView other_message, my_message;

        public TX_VH(View itemView) {
            super(itemView);
            this.other_message = (TextView) itemView.findViewById(R.id.other_message);
            this.my_message = (TextView) itemView.findViewById(R.id.my_message);
        }

        @Override
        public void onBind(final int position, FChatMessageEntity chatMessageEntity) {
            super.onBind(position, chatMessageEntity);
            other_rl.setVisibility(View.GONE);
            my_rl.setVisibility(View.GONE);
            sys_message.setVisibility(View.GONE);

            if (chatMessageEntity.all.equals("1")) {
                sys_message.setVisibility(View.VISIBLE);
                sys_message.setText(chatMessageEntity.content);
//                sys_maddtime.setText(chatMessageEntity.add_time);
            } else {
                if (chatMessageEntity.isOther) {
                    other_rl.setVisibility(View.VISIBLE);
                    other_userImg_s.loadImage(context, chatMessageEntity.user_logo);
                    other_userImg_s.hideStatus();
                    other_message.setText(chatMessageEntity.content);
                    other_maddtime.setText(chatMessageEntity.add_time);
                } else {
                    my_rl.setVisibility(View.VISIBLE);
                    my_userImg_s.loadImage(context, chatMessageEntity.user_logo);
                    my_userImg_s.hideStatus();
                    my_message.setText(chatMessageEntity.content);
                    my_maddtime.setText(chatMessageEntity.add_time);
                }
            }
        }
    }


    class IMG_VH extends M_VH {
        public ImageView other_img, my_img;

        public IMG_VH(View itemView) {
            super(itemView);
            this.other_img = (ImageView) itemView.findViewById(R.id.other_img);
            this.my_img = (ImageView) itemView.findViewById(R.id.my_img);
        }

        @Override
        public void onBind(final int position, final FChatMessageEntity chatMessageEntity) {
            super.onBind(position, chatMessageEntity);
            other_rl.setVisibility(View.GONE);
            my_rl.setVisibility(View.GONE);
            sys_message.setVisibility(View.GONE);


            if (chatMessageEntity.all.equals("1")) {
                sys_message.setVisibility(View.VISIBLE);
                sys_message.setText(chatMessageEntity.content);
            } else {
                if (chatMessageEntity.isOther) {
                    other_rl.setVisibility(View.VISIBLE);
                    other_userImg_s.loadImage(context, chatMessageEntity.user_logo);
                    other_userImg_s.hideStatus();
                    other_maddtime.setText(chatMessageEntity.add_time);
                    if (ToolUtils.isNull(chatMessageEntity.img)) {
                        if (chatMessageEntity.weakBitMap.get() != null) {
                            other_img.setImageBitmap(chatMessageEntity.weakBitMap.get());
                        } else {
                            rx.Observable.just(chatMessageEntity).observeOn(Schedulers.io()).map(new Func1<FChatMessageEntity, Boolean>() {

                                @Override
                                public Boolean call(FChatMessageEntity fChatMessageEntity) {
                                    fChatMessageEntity.weakBitMap = new WeakReference<Bitmap>(BitmapUtil.base64ToBitmap
                                            (fChatMessageEntity.content));
                                    return true;
                                }
                            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    other_img.setImageBitmap(chatMessageEntity.weakBitMap.get());
                                }
                            });
                        }
                    } else {
                        GlideUtils.loadImageDefult(context, chatMessageEntity.img, other_img);
                    }
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<String> strings = new ArrayList<>();
                            strings.add(position + "");
                            List<ImageView> imageViews = new ArrayList<>();
                            imageViews.add(other_img);
                            vImageWatcher.show(other_img, imageViews, strings);
                        }
                    });
                } else {
                    my_rl.setVisibility(View.VISIBLE);
                    my_userImg_s.loadImage(context, chatMessageEntity.user_logo);
                    my_userImg_s.hideStatus();
                    my_maddtime.setText(chatMessageEntity.add_time);
//                    if (chatMessageEntity.weakBitMap.get() != null) {
//                    my_img.setImageBitmap(chatMessageEntity.bitmap);
//                    }
                    if (ToolUtils.isNull(chatMessageEntity.img)) {
                        if (chatMessageEntity.weakBitMap != null && chatMessageEntity.weakBitMap.get() != null) {
                            my_img.setImageBitmap(chatMessageEntity.weakBitMap.get());
                        } else {
                            rx.Observable.just(chatMessageEntity).observeOn(Schedulers.io()).map(new Func1<FChatMessageEntity, Boolean>() {

                                @Override
                                public Boolean call(FChatMessageEntity fChatMessageEntity) {
                                    fChatMessageEntity.weakBitMap = new WeakReference<Bitmap>(BitmapUtil.base64ToBitmap
                                            (fChatMessageEntity.content));
                                    return true;
                                }
                            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    my_img.setImageBitmap(chatMessageEntity.weakBitMap.get());
                                }
                            });
                        }
                    } else {
                        GlideUtils.loadImageDefult(context, chatMessageEntity.img, my_img);
                    }
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<String> strings = new ArrayList<>();
                            strings.add(position + "");
                            List<ImageView> imageViews = new ArrayList<>();
                            imageViews.add(my_img);
                            vImageWatcher.show(my_img, imageViews, strings);
                        }
                    });
                }
            }
        }
    }


    public void releaseD() {
        for (FChatMessageEntity entity : fChatMessageEntities) {
            if (entity != null && entity.media_type == FChatMessageEntity.IMG_MSG) {
                if (entity.weakBitMap.get() != null) {
                    entity.weakBitMap.get().recycle();
                }
            }
        }
    }

}
