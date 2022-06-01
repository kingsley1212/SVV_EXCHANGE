package com.svv.jys.code.module.server.chatserver.act;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpChatSocketActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.constant.IETConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.ChatOrderInfoRntity;
import com.svv.jys.code.common.entity.OrderDetailEntity;
import com.svv.jys.code.common.utils.GetPictureUtils;
import com.svv.jys.code.common.utils.PermissionUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupGetPictureView;
import com.svv.jys.code.module.server.chatserver.act.adapter.InChatAdapter;
import com.svv.jys.code.module.server.chatserver.act.model.IInChatModel;
import com.svv.jys.code.module.server.chatserver.act.presenter.InChatPresenter;
import com.svv.jys.code.module.server.chatserver.act.view.IInChatView;
import com.svv.jys.code.module.server.chatserver.bean.FChatMessageEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2018/1/5.
 */
public class InChatActivity extends MvpChatSocketActivity<IInChatView, IInChatModel, InChatPresenter> implements
        IInChatView {

    private String id;
    private TextView tv_num_unit;
    private TextView tv_price_unit;
    private TextView tv_money_unit;

    public static Intent getIntent(Context context, OrderDetailEntity fOrderDetailEntity) {
        Intent intent = new Intent(context, InChatActivity.class);
        intent.putExtra("fOrderDetailEntity", fOrderDetailEntity);
        return intent;
    }

    private XRecyclerView message_xrv;
    private EditText content_et;
    private ImageView chat_sendimg,chat_send;
    public boolean isAddgetChatFromServer;
    public InChatAdapter inChatAdapter;
    public OrderDetailEntity fOrderDetailEntity;
    public boolean isGetOldMore = false;
    public boolean hasOldMore = true;
    private File userImgFile;
    private PopupGetPictureView popupGetPictureView;
    private TextView tv_order_no, tv_order_status, tv_price, tv_money, tv_time;
    private ImageView iv_copy_order;
    private RelativeLayout send_rl;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }
        switch (requestCode) {
            case IETConstant.GETPICTURE_TAKEPHOTO:
                presenter.compressAndcommitImg(userImgFile);
//                userImgFile = GetPictureUtils.cutPicture(getMContext(), userImgFile);
                break;
            case IETConstant.CUT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    /*Glide.with(getMContext()).load(userImgFile).error(R.mipmap.ic_loading)
                            .into(userIcon);*/
                    presenter.compressAndcommitImg(userImgFile);
//                    sendImgMessage();
                    //压缩上传图片
                }
                break;
            case IETConstant.GETPICTURE_SELECTPHOTO:
                userImgFile = GetPictureUtils.getPhotoFromIntent(data, getMContext());
//                userImgFile = GetPictureUtils.cutPicture(getMContext(), userImgFile);
                presenter.compressAndcommitImg(userImgFile);
                break;
        }
    }


    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public InChatPresenter initPresenter() {
        return new InChatPresenter();
    }


    @Override
    public void sendFileMessage(String s) {
        if (ToolUtils.isNull(s)) {
            T.showShort(this, getString(R.string.shat_select_pic));
            return;
        }
        mChatService.sendMsg(chatOrderInfoRntity.getU().getId(),chatOrderInfoRntity.getT().getId(),"img["+s+"][lay]",chatOrderInfoRntity.getInfo().getId());
    }

    private ChatOrderInfoRntity chatOrderInfoRntity;

    @Override
    public void setOrderDetail(final ChatOrderInfoRntity orderDetail) {
        mChatService.getMsg(orderDetail.getInfo().getId());
        chatOrderInfoRntity=orderDetail;
        if (orderDetail.getInfo().getStatus().equals("2")||orderDetail.getInfo().getStatus().equals("3")||orderDetail.getInfo().getStatus().equals("4")){
            send_rl.setVisibility(View.GONE);
        }
        tv_num_unit.setText(String.format(getString(R.string.chat_num),orderDetail.getInfo().getCoin_name().toUpperCase()));
        tv_price_unit.setText(String.format(getString(R.string.chat_price),orderDetail.getInfo().getCurrency()));
        tv_money_unit.setText(String.format(getString(R.string.chat_money),orderDetail.getInfo().getCurrency()));
        tv_time.setText(orderDetail.getInfo().getNum());
        iv_copy_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(orderDetail.getInfo().getOrder_no());
                T.showShort(InChatActivity.this, getString(R.string.assat24));
            }
        });

        tv_order_no.setText(getString(R.string.chat_order_no) + orderDetail.getInfo().getOrder_no());
        tv_order_status.setText(orderDetail.getInfo().getStatus_name());
        tv_price.setText(orderDetail.getInfo().getPrice());
        tv_money.setText(orderDetail.getInfo().getAmount());
        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contant=content_et.getText().toString().trim();
                if (TextUtils.isEmpty(contant)){
                    T.showShort(InChatActivity.this, getString(R.string.chat_no_msg));
                    return;
                }
                mChatService.sendMsg(orderDetail.getU().getId(),orderDetail.getT().getId(),contant,orderDetail.getInfo().getId());
                content_et.setText("");
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }

    @Override
    public void baseInitialization() {
        showNewsMessageBool = false;
        EventBus.getDefault().register(this);
        id = getIntent().getStringExtra("id");
    }


    @Override
    public int setR_Layout() {
        return R.layout.act_inchat;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.lianxi));
        send_rl=findViewById(R.id.send_rl);
        chat_send=findViewById(R.id.chat_send);
        tv_num_unit = findViewById(R.id.tv_num_unit);
        tv_price_unit = findViewById(R.id.tv_price_unit);
        tv_money_unit = findViewById(R.id.tv_money_unit);
        tv_order_no = findViewById(R.id.tv_order_no);
        tv_order_status = findViewById(R.id.tv_order_status);
        tv_price = findViewById(R.id.tv_price);
        tv_money = findViewById(R.id.tv_money);
        tv_time = findViewById(R.id.tv_time);
        iv_copy_order = findViewById(R.id.iv_copy_order);
        message_xrv = (XRecyclerView) $(R.id.message_xrv);
        content_et = (EditText) $(R.id.content_et);
        LinearLayoutManager manager1 = new LinearLayoutManager(getMContext());
        message_xrv.setLayoutManager(manager1);
        message_xrv.setPullRefreshEnabled(false);
        message_xrv.setLoadingMoreEnabled(false);
        message_xrv.setHasFixedSize(true);
        chat_sendimg = (ImageView) $(R.id.chat_sendimg);

        chat_sendimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupGetPictureView == null) {
                    popupGetPictureView = new PopupGetPictureView(InChatActivity.this, new PopupGetPictureView
                            .GetPicture() {
                        @Override
                        public void takePhoto(View v) {
                            if (PermissionUtils.checkTakePhotoPermission(getMContext())) {
                                userImgFile = GetPictureUtils.takePicture(InChatActivity.this, IETConstant
                                        .GETPICTURE_TAKEPHOTO);
                            }
                            popupGetPictureView.dismiss();
                        }

                        @Override
                        public void selectPhoto(View v) {
                            if (PermissionUtils.checkAlbumStroagePermission(getMContext())) {
                                GetPictureUtils.selectPhoto(InChatActivity.this, IETConstant.GETPICTURE_SELECTPHOTO);
                            }
                            popupGetPictureView.dismiss();
                        }

                    });
                }
                popupGetPictureView.showPop(v);
            }
        });
        message_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                if (!isGetOldMore && hasOldMore) {
                    getMoreMessage();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                message_xrv.refreshComplete();
                            }
                        });
                    }
                }, 5000);
            }

            @Override
            public void onLoadMore() {

            }
        });

    }

    public void getMoreMessage() {
        isGetOldMore = true;
        String mid = (inChatAdapter.getItem(0) == null ? "0" : inChatAdapter.getItem(0).id);
//        mChatService.getMoreOldMessage(fOrderDetailEntity.getId(), mid);
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doMarketListEvent(List<FChatMessageEntity> fChatMessageEntities) {
        if (fChatMessageEntities != null&&fChatMessageEntities.size()!=0) {
            List<FChatMessageEntity> list=new ArrayList<>();
            for (int i=0;i<fChatMessageEntities.size();i++){
                if (fChatMessageEntities.get(i).order_id.equals(chatOrderInfoRntity.getInfo().getId())){
                    list.add(fChatMessageEntities.get(i));
                    if (fChatMessageEntities.get(i).user_id.equals(ACache.get(InChatActivity.this).getAsString(ACEConstant.ACE_USERINFO_USERID))){
                        fChatMessageEntities.get(i).isOther=false;
                        fChatMessageEntities.get(i).user_logo=chatOrderInfoRntity.getU().getLogo();
                    }else {
                        fChatMessageEntities.get(i).isOther=true;
                        fChatMessageEntities.get(i).user_logo=chatOrderInfoRntity.getT().getLogo();

                    }
                }

            }
            inChatAdapter.addOldMessages(list);
            message_xrv.scrollToPosition(inChatAdapter.getItemCount());
        }
    }
    @Override
    public void doBusiness() {
        presenter.getOrderDetail(id);
        inChatAdapter = new InChatAdapter(this);
        message_xrv.setAdapter(inChatAdapter);

    }


    @Override
    public void cSConttectSeccuss() {

    }

    @Override
    public void cSConttectFail() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
