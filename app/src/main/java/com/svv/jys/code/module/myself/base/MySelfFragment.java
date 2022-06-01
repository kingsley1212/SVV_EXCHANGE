package com.svv.jys.code.module.myself.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpFragment;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.constant.IETConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.CollectEntity;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.entity.NewIdentifyEntity;
import com.svv.jys.code.common.utils.GetPictureUtils;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.PermissionUtils;
import com.svv.jys.code.common.utils.StatusBarUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.common.view.popup.PopupGetPictureView;
import com.svv.jys.code.module.home.message_center.MessageCenterAct;
import com.svv.jys.code.module.myself.base.model.IMySelfModel;
import com.svv.jys.code.module.myself.base.presenter.MySelfPresenter;
import com.svv.jys.code.module.myself.base.view.IMySelfView;
import com.svv.jys.code.module.myself.login.userlogin.UserLoginAct;
import com.svv.jys.code.module.myself.myasset.base2.MyAsset2Act;
import com.svv.jys.code.module.myself.myasset.jiaoyi.chargemoney.ChargeMoneyAct;
import com.svv.jys.code.module.myself.myasset.jiaoyi.mentionmoney.MentionMoneyAct;
import com.svv.jys.code.module.myself.myasset.otc.otctransfer.OtcTransferAct;
import com.svv.jys.code.module.myself.ordermanage.base.OrderManagerAct;
import com.svv.jys.code.module.myself.qrcode.QRCodeAct;
import com.svv.jys.code.module.myself.setting.UserSettingAct;
import com.svv.jys.code.module.myself.subscription.SubscriptionAct;
import com.svv.jys.code.module.myself.usercenter.base.UserCenterAct;
import com.svv.jys.code.module.myself.useridentify.base.UserIdentifyAct;
import com.svv.jys.code.module.myself.welfare.WelfareAct;
import com.svv.jys.code.module.net.exception.NeedLoginException;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class MySelfFragment extends MvpFragment<IMySelfView, IMySelfModel, MySelfPresenter> implements IMySelfView {
    private View userInfo_ly, unRegister_ly, aboutUs_ly, address_ly, userCenter_ly, qrCode_ly, myasset_ly,
            ordermanager_ly, identify_ly, business_order_ly, view_line, business_adv_ly, view_line1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CircleImageView userHeadImg;
    private TextView userName, copy_tv;
    private FUserInfoEntity fUserInfoEntity;
    private File userImgFile;
    private ImageView iv_renz;
    private RelativeLayout rl_authentication;
    private View to_login_rl;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }

        switch (requestCode) {
            case IETConstant.GETPICTURE_TAKEPHOTO:
                userImgFile = GetPictureUtils.cutPicture(getMContext(), userImgFile);
                presenter.compressAndcommitImg(userImgFile);
                break;
            case IETConstant.GETPICTURE_SELECTPHOTO:
                userImgFile = GetPictureUtils.getPhotoFromIntent(data, getMContext());
                presenter.compressAndcommitImg(userImgFile);
//                userImgFile = GetPictureUtils.cutPicture(getMContext(), userImgFile);
                break;
            case IETConstant.CUT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    presenter.compressAndcommitImg(userImgFile);
                }
                break;
            default:
                break;
        }

    }

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public MySelfPresenter initPresenter() {
        return new MySelfPresenter();
    }

    @Override
    public void doBusiness() {
        getUserInfo(true);
    }

    @Override
    public void onWakeBussiness() {
        getUserInfo(true);
        StatusBarUtils.setImmersiveStatusBar(getActivity(), true);
    }

    @Override
    public void onResume() {
        super.onResume();
        StatusBarUtils.setImmersiveStatusBar(getActivity(), true);
    }
    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.fragment_myself;
    }


    @Override
    public void viewInitialization() {
        rl_authentication = (RelativeLayout) $(R.id.rl_authentication);
        iv_renz = (ImageView) $(R.id.iv_renz);
        copy_tv = (TextView) $(R.id.copy_tv);
        userHeadImg = (CircleImageView) $(R.id.userHeadImg);
        userName = (TextView) $(R.id.userName);
        to_login_rl = $(R.id.to_login_rl);
        userInfo_ly = $(R.id.userInfo_ly);
        userInfo_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                    } catch (NeedLoginException e) {

                    }
                }


            }
        });
        $(R.id.contract_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    gotoActivity(MentionMoneyAct.class);
                }
            }
        });
        $(R.id.rl_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
//                    try {
//                        ToolUtils.checkLogin(getActivity(), true);
                    gotoActivity(UserSettingAct.class);
//                    } catch (NeedLoginException e) {
//                        e.printStackTrace();
//                    }
                }


            }
        });
        $(R.id.charge_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    try {
                        if (ToolUtils.checkLogin(getActivity(), false)) {
                            gotoActivity(ChargeMoneyAct.class);
                        }
                    } catch (NeedLoginException e) {
                        gotoActivity(UserLoginAct.class);
                    }

                }


            }
        });
        $(R.id.transfer_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    try {
                        if (ToolUtils.checkLogin(getActivity(), false)) {
                            gotoActivity(OtcTransferAct.class);
                        }
                    } catch (NeedLoginException e) {
                        gotoActivity(UserLoginAct.class);
                    }
                }

            }
        });
        copy_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ToolUtils.checkLogin(getMContext(), false);
                    ClipboardManager cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                    // 将文本内容放到系统剪贴板里。
                    cm.setText(fUserInfoEntity.getInvite_code());
                    T.showShort(getActivity(), getString(R.string.assat24));
                } catch (NeedLoginException e) {
                    gotoActivity(UserLoginAct.class);
                }
            }
        });

        userHeadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    try {
                        if (ToolUtils.checkLogin(getActivity(), false)) {
                            PopupGetPictureView popupGetPictureView = new PopupGetPictureView(getActivity(), new
                                    PopupGetPictureView.GetPicture() {
                                        @Override
                                        public void takePhoto(View v) {
                                            if (PermissionUtils.checkTakePhotoPermission(getMContext())) {
                                                userImgFile = GetPictureUtils.takePicture(getMContext(), IETConstant
                                                        .GETPICTURE_TAKEPHOTO);
                                            }
                                        }

                                        @Override
                                        public void selectPhoto(View v) {
                                            if (PermissionUtils.checkAlbumStroagePermission(getMContext())) {
                                                GetPictureUtils.selectPhoto(getMContext(), IETConstant
                                                        .GETPICTURE_SELECTPHOTO);
                                            }
                                        }
                                    });
                            popupGetPictureView.showPop(userHeadImg);
                        }

                    } catch (NeedLoginException e) {
                        gotoActivity(UserLoginAct.class);
                    }
                }


            }

        });


        //下拉刷新
        swipeRefreshLayout = (SwipeRefreshLayout) $(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GRAY, Color.RED, Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUserInfo(false);
            }
        });

        //我的资产
        myasset_ly = $(R.id.myasset_ly);
        myasset_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                        gotoActivity(MyAsset2Act.class);
                    } catch (NeedLoginException e) {
                    }
                }


            }
        });
        $(R.id.c2c_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ToolUtils.isFastClick(view.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
//                        gotoActivity(C2cAct.class);
                        gotoActivity(SubscriptionAct.class);
                    } catch (NeedLoginException e) {
                    }
                }


            }
        });

        //订单管理
        ordermanager_ly = $(R.id.ordermanager_ly);
        ordermanager_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                        gotoActivity(OrderManagerAct.class);
                    } catch (NeedLoginException e) {
                    }
                }


            }
        });
        //用户认证
        identify_ly = $(R.id.identify_ly);
        rl_authentication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
//                    if (fUserInfoEntity.getIs_identity().equals("1")) {
//                        T.showShort(getMContext(), getString(R.string.mysele_renz));
//                    } else {
                        presenter.getIdentityInfo();
//                    }

                    } catch (NeedLoginException e) {
                    }

                }

            }
        });
        //二维码
        qrCode_ly = $(R.id.qrCode_ly);
        qrCode_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                        if (fUserInfoEntity != null) {
                            Intent intent = new Intent(getMContext(), QRCodeAct.class);
                            intent.putExtra("user", fUserInfoEntity);
                            startActivity(intent);
                        }
                    } catch (NeedLoginException e) {
                    }

                }

            }
        });

        //用户中心
        userCenter_ly = $(R.id.userCenter_ly);
        userCenter_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                        Bundle bundle = new Bundle();
                        if (fUserInfoEntity != null) {
                            bundle.putSerializable("fUserInfoEntity", fUserInfoEntity);
                        }
                        gotoActivity(UserCenterAct.class, false, bundle);
                    } catch (NeedLoginException e) {

                    }
                }


            }
        });

        $(R.id.rl_tangguo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    try {
                        ToolUtils.checkLogin(getMContext(), true);
                        gotoActivity(WelfareAct.class);
                    } catch (NeedLoginException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        $(R.id.question_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    MessageCenterAct.startByType("1", getMContext());
                }

            }
        });


        //关于我们
        aboutUs_ly = $(R.id.aboutUs_ly);
        aboutUs_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    MessageCenterAct.startByType("2", getMContext());
                }

            }
        });

        //退出登录
        unRegister_ly = $(R.id.unRegister_ly);
        unRegister_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    PopupDialogView view = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                        @Override
                        public void leftBtn() {

                        }

                        @Override
                        public void rightBtn() {
                            presenter.Logout();

                        }
                    });
                    view.showPop(v, getString(R.string.mysele_out), getString(R.string.myselffragment_conten), getString
                            (R.string
                                    .myselffragment_left_text), getString(R.string.myselffragment_right_text));
                }
            }

        });

        $(R.id.customer_service_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(ACache.get(getMContext()).getAsString(ACEConstant.HELP_URL)));
                startActivity(intent);
            }
        });
    }


    /**
     * 获取个人信息
     *
     * @param showLoading
     */
    private void getUserInfo(boolean showLoading) {
        presenter.getUserInfoData(showLoading);
        try {
            ToolUtils.checkLogin(getMContext(), false);
            unRegister_ly.setVisibility(View.GONE);
        } catch (NeedLoginException e) {
            unRegister_ly.setVisibility(View.GONE);
            userName.setText(R.string.login_register);
            userHeadImg.setImageResource(R.mipmap.ic_portrait);
            copy_tv.setVisibility(View.GONE);
            iv_renz.setVisibility(View.GONE);
            to_login_rl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getUserInfoComplete() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setUsetData(FUserInfoEntity usetData) {

        copy_tv.setVisibility(View.VISIBLE);
        to_login_rl.setVisibility(View.GONE);
        ACache.get(getMContext()).put(ACEConstant.ACE_USERINFO_MOBILE, ToolUtils.isNull(usetData.getMobile()) ? "" :
                usetData.getMobile());//绑定的手机号
        ACache.get(getMContext()).put(ACEConstant.ACE_USERINFO_EMAIL, ToolUtils.isNull(usetData.getEmail()) ? "" :
                usetData.getEmail());//绑定的邮箱
        ACache.get(getMContext()).put(ACEConstant.IS_GOOGLE, usetData.getIs_google());
        ACache.get(getMContext()).put(ACEConstant.ACE_USERINFO_ISSHOP, ToolUtils.isNull(usetData.getIs_shoper()) ? ""
                : usetData.getIs_shoper());//是不是商家
        ToolUtils.saveUserInfo(getMContext(), usetData);
        ACache.get(getMContext()).put(ACEConstant.ACE_USERINFO_USERID, usetData.getId());//用户ID
        ACache.get(getActivity()).put(ACEConstant.ACE_USERINFO_GOOGLE, usetData.getVerify_google());//谷歌验证
        ACache.get(getMContext()).put(ACEConstant.VERIFY_EMAIL, ToolUtils.isNull((usetData.getVerify_email())) ? "0" : usetData.getVerify_email());//是否开启邮箱
        ACache.get(getMContext()).put(ACEConstant.VERIFY_MOBILE, ToolUtils.isNull((usetData.getVerify_mobile())) ? "0" : usetData.getVerify_mobile());//是否开启手机
        ACache.get(getMContext()).put(ACEConstant.VERIFY_GOOGLE, ToolUtils.isNull((usetData.getVerify_google())) ? "0" : usetData.getVerify_google());//是否开启谷歌
        fUserInfoEntity = usetData;
        userName.setText(ToolUtils.isNull(usetData.getNick_name()) ? usetData.getUser_name() : usetData.getNick_name());

        CollectEntity collectEntity = new CollectEntity();
        collectEntity.setCollect_market(usetData.getCollect_market());
        ACache.get(getActivity()).put(ACEConstant.ACE_USERINFO_COLLECTMARKET, collectEntity);
        if (!usetData.getIs_identity().equals("0")) {
            iv_renz.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(usetData.getLogo())) {
            userHeadImg.setImageResource(R.mipmap.ic_portrait);
        } else {
            GlideUtils.loadUserLogo(getMContext(), usetData.getLogo(), userHeadImg);
        }
//        if (usetData.getIs_shoper().equals("1")) {
//            business_order_ly.setVisibility(View.GONE);
////            view_line.setVisibility(View.GONE);
////            view_line1.setVisibility(View.GONE);
//            business_adv_ly.setVisibility(View.GONE);
//        } else {
//            business_order_ly.setVisibility(View.GONE);
////            view_line.setVisibility(View.GONE);
////            view_line1.setVisibility(View.GONE);
//            business_adv_ly.setVisibility(View.GONE);
//        }

    }

    @Override
    public void loadUserImg(String imgUrl) {
        GlideUtils.loadImageDefult(getMContext(), imgUrl, userHeadImg);
    }

    @Override
    public void successLoginout() {
        ToolUtils.logout(getMContext());
        fUserInfoEntity = null;
        userName.setText(R.string.login_register);
        business_order_ly.setVisibility(View.GONE);
        view_line.setVisibility(View.GONE);
        view_line1.setVisibility(View.GONE);
        business_adv_ly.setVisibility(View.GONE);
        unRegister_ly.setVisibility(View.GONE);
        userHeadImg.setImageResource(R.mipmap.ic_user_default);
        copy_tv.setVisibility(View.GONE);
        to_login_rl.setVisibility(View.VISIBLE);
    }

    @Override
    public void getidentifyInfo(NewIdentifyEntity entity) {
        Bundle bundle = new Bundle();
        bundle.putString("country", fUserInfoEntity.getCountry());
        if (fUserInfoEntity != null && fUserInfoEntity.getIs_identity().equals("1")) {
            T.showShort(getMContext(), getString(R.string.mysele_renz));
        } else {
            bundle.putSerializable("entity", entity);
            gotoActivity(UserIdentifyAct.class, false, bundle);

        }
    }

}
