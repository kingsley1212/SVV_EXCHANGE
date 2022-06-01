package com.svv.jys.code.module.myself.useridentify.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.IETConstant;
import com.svv.jys.code.common.entity.IndentityImgEntity;
import com.svv.jys.code.common.entity.NewIdentifyEntity;
import com.svv.jys.code.common.utils.GetPictureUtils;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.PermissionUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.popup.PopupGetPictureView;
import com.svv.jys.code.common.view.popup.PopupSafeVerifyView;
import com.svv.jys.code.module.myself.useridentify.base.model.IUserIdentifyModel;
import com.svv.jys.code.module.myself.useridentify.base.presenter.UserIdentifyPresenter;
import com.svv.jys.code.module.myself.useridentify.base.view.IUserIdentifyView;

import java.io.File;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class UserIdentifyAct extends MvpActivity<IUserIdentifyView, IUserIdentifyModel, UserIdentifyPresenter> implements IUserIdentifyView, View.OnClickListener {
    private TextView identifyCommit, failed_reason_tv;
    private ImageView iv_pic1, iv_pic2, iv_pic3;
    private EditText et_name, et_code, et_xingshi, et_city, et_deteleAddress;
    RelativeLayout rl_xingshi, rl_city, rl_deteleAddress;
    private File userImgFile;
    private String pic1, pic2, pic3;
    //    private String surname;
    private String address;
    private String city;
    private String turename;
    private String idcard;
    private String country;
    private NewIdentifyEntity entity;
    private View zhengmian_ll, fanmian_ll, shouchi_ll, pic1_rl, pic2_rl, pic3_rl;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public UserIdentifyPresenter initPresenter() {
        return new UserIdentifyPresenter();
    }

    @Override
    public void baseInitialization() {
        country = getIntent().getStringExtra("country");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_useridentify;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.useridentify_title));
        zhengmian_ll = findViewById(R.id.zhengmian_ll);
        fanmian_ll = findViewById(R.id.fanmian_ll);
        shouchi_ll = findViewById(R.id.shouchi_ll);
        pic1_rl = findViewById(R.id.pic1_rl);
        pic2_rl = findViewById(R.id.pic2_rl);
        pic3_rl = findViewById(R.id.pic3_rl);
        rl_xingshi = findViewById(R.id.rl_xingshi);
        failed_reason_tv = findViewById(R.id.failed_reason_tv);
        rl_city = findViewById(R.id.rl_city);
        rl_deteleAddress = findViewById(R.id.rl_deteleAddress);

        et_xingshi = findViewById(R.id.et_xingshi);
        et_city = findViewById(R.id.et_city);
        et_name = findViewById(R.id.et_name);
        et_deteleAddress = findViewById(R.id.et_deteleAddress);
        et_code = findViewById(R.id.et_code);

        if (country.equals("1")) {
            et_city.setVisibility(View.GONE);
            et_deteleAddress.setVisibility(View.GONE);
            rl_city.setVisibility(View.GONE);
            rl_deteleAddress.setVisibility(View.GONE);
        } else {
            et_city.setVisibility(View.VISIBLE);
            et_deteleAddress.setVisibility(View.VISIBLE);
            rl_city.setVisibility(View.VISIBLE);
            rl_deteleAddress.setVisibility(View.VISIBLE);
        }
        iv_pic1 = findViewById(R.id.iv_pic1);
        iv_pic2 = findViewById(R.id.iv_pic2);
        iv_pic3 = findViewById(R.id.iv_pic3);
        iv_pic1.setOnClickListener(this);
        iv_pic2.setOnClickListener(this);
        iv_pic3.setOnClickListener(this);
        zhengmian_ll.setOnClickListener(this);
        fanmian_ll.setOnClickListener(this);
        shouchi_ll.setOnClickListener(this);


        identifyCommit = (TextView) $(R.id.identifyCommit);
        identifyCommit.setSelected(true);

        identifyCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turename = et_name.getText().toString().trim();
                idcard = et_code.getText().toString().trim();
//                surname=et_xingshi.getText().toString().trim();
                if (ToolUtils.isNull(turename)) {
                    T.showShort(UserIdentifyAct.this, getString(R.string.UserIdentifyAct_input_name_z));
                    return;
                }
                if (ToolUtils.isNull(idcard)) {
                    T.showShort(UserIdentifyAct.this, getString(R.string.UserIdentifyAct_input_sfz));
                    return;
                }

                if (ToolUtils.isNull(pic1) || ToolUtils.isNull(pic2) || ToolUtils.isNull(pic3)) {
                    if (entity == null) {
                        T.showShort(UserIdentifyAct.this, getString(R.string.UserIdentifyAct_image));
                    } else {
                        T.showShort(UserIdentifyAct.this, getString(R.string.UserIdentifyAct_reset));
                    }
                    return;
                }
//                if (ToolUtils.isNull(surname)){
//                    T.showShort(UserIdentifyAct.this,getString(R.string.UserIdentifyAct_xing));
//                    return;
//                }
                if (!country.equals("1")) {

                    city = et_city.getText().toString().trim();
                    address = et_deteleAddress.getText().toString().trim();

                    if (ToolUtils.isNull(city)) {
                        T.showShort(UserIdentifyAct.this, getString(R.string.UserIdentifyAct_city));
                        return;
                    }
                    if (ToolUtils.isNull(address)) {
                        T.showShort(UserIdentifyAct.this, getString(R.string.UserIdentifyAct_address));
                        return;
                    }
                }
                new PopupSafeVerifyView(getMContext(), new PopupSafeVerifyView.checkVerifyListener() {
                    @Override
                    public void checkSuccess() {
                        presenter.postIdentify(turename, idcard, pic1, pic2, pic3, "", city, address);
                    }
                }).showPop(view, "0");

            }
        });
    }

    public void setData() {
        entity = (NewIdentifyEntity) getIntent().getSerializableExtra("entity");
        if (entity.getIdentity_info() != null) {
            failed_reason_tv.setVisibility(View.VISIBLE);
            failed_reason_tv.setText(entity.getTips());
            NewIdentifyEntity.IdentityInfoBean bean = entity.getIdentity_info();
            et_name.setText(bean.getTrue_name());
            et_code.setText(bean.getIdcard());
            et_xingshi.setText(bean.getSurname());
            et_city.setText(bean.getCity());
            et_deteleAddress.setText(bean.getAddress());
            if (TextUtils.isEmpty(bean.getPic_1())) {
                zhengmian_ll.setVisibility(View.VISIBLE);
                pic1_rl.setVisibility(View.GONE);
            } else {
                pic1 = bean.getPic1();
                zhengmian_ll.setVisibility(View.GONE);
                pic1_rl.setVisibility(View.VISIBLE);
                GlideUtils.loadImageDefult(this, bean.getPic_1(), iv_pic1);
            }
            if (TextUtils.isEmpty(bean.getPic_2())) {
                fanmian_ll.setVisibility(View.VISIBLE);
                pic2_rl.setVisibility(View.GONE);
            } else {
                pic2 = bean.getPic2();
                fanmian_ll.setVisibility(View.GONE);
                pic2_rl.setVisibility(View.VISIBLE);
                GlideUtils.loadImageDefult(this, bean.getPic_2(), iv_pic2);
            }
            if (TextUtils.isEmpty(bean.getPic_3())) {
                shouchi_ll.setVisibility(View.VISIBLE);
                pic3_rl.setVisibility(View.GONE);
            } else {
                pic3 = bean.getPic3();
                shouchi_ll.setVisibility(View.GONE);
                pic3_rl.setVisibility(View.VISIBLE);
                GlideUtils.loadImageDefult(this, bean.getPic_3(), iv_pic3);
            }
            if (!entity.getAble().equals("true")) {
                iv_pic1.setEnabled(false);
                iv_pic2.setEnabled(false);
                iv_pic3.setEnabled(false);
                identifyCommit.setVisibility(View.GONE);
                et_name.setFocusable(false);
                et_name.setFocusableInTouchMode(false);

                et_code.setFocusable(false);
                et_code.setFocusableInTouchMode(false);

                et_xingshi.setFocusable(false);
                et_xingshi.setFocusableInTouchMode(false);

                et_city.setFocusable(false);
                et_city.setFocusableInTouchMode(false);

                et_deteleAddress.setFocusable(false);
                et_deteleAddress.setFocusableInTouchMode(false);
            }


        }
    }

    @Override
    public void doBusiness() {
        setData();
    }


    @Override
    public void identifySuccese() {
        T.showShort(this, getString(R.string.UserIdentifyAct_wite_successs));
        finish();
    }


    @Override
    public void successUpImg(int type, IndentityImgEntity entity) {
        if (type == 1) {
            GlideUtils.loadImageDefult(this, entity.getSrc(), iv_pic1);
            pic1 = entity.getUrl();
            zhengmian_ll.setVisibility(View.GONE);
            pic1_rl.setVisibility(View.VISIBLE);
        } else if (type == 2) {
            GlideUtils.loadImageDefult(this, entity.getSrc(), iv_pic2);
            pic2 = entity.getUrl();
            fanmian_ll.setVisibility(View.GONE);
            pic2_rl.setVisibility(View.VISIBLE);
        } else if (type == 3) {
            GlideUtils.loadImageDefult(this, entity.getSrc(), iv_pic3);
            pic3 = entity.getUrl();
            shouchi_ll.setVisibility(View.GONE);
            pic3_rl.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void successRemove() {
        if (remove == 1) {
            iv_pic1.setEnabled(true);
            iv_pic1.setImageResource(R.mipmap.add_img);
        } else if (remove == 2) {
            iv_pic2.setEnabled(true);
            iv_pic2.setImageResource(R.mipmap.add_img);
        } else if (remove == 3) {
            iv_pic3.setEnabled(true);
            iv_pic3.setImageResource(R.mipmap.add_img);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pic1:
            case R.id.zhengmian_ll:
                selectImg(view);
                type = 1;
                break;
            case R.id.iv_pic2:
            case R.id.fanmian_ll:
                selectImg(view);
                type = 2;
                break;
            case R.id.iv_pic3:
            case R.id.shouchi_ll:
                selectImg(view);
                type = 3;
                break;
       /*     case R.id.iv_delete1:
                remove=1;
                successRemove();
                pic1 = null;
                break;
            case R.id.iv_delete2:
                remove=2;
                pic2 = null;
                successRemove();
                break;
            case R.id.iv_delete3:
                remove=3;
                pic3 =null;
                successRemove();
                break;*/
        }
    }

    public void selectImg(View view) {
        PopupGetPictureView popupGetPictureView = new PopupGetPictureView(this, new PopupGetPictureView.GetPicture() {
            @Override
            public void takePhoto(View v) {
                if (PermissionUtils.checkTakePhotoPermission(getMContext())) {
                    userImgFile = GetPictureUtils.takePicture(getMContext(), IETConstant.GETPICTURE_TAKEPHOTO);
                }
            }

            @Override
            public void selectPhoto(View v) {
                if (PermissionUtils.checkAlbumStroagePermission(getMContext())) {
                    GetPictureUtils.selectPhoto(getMContext(), IETConstant.GETPICTURE_SELECTPHOTO);
                }
            }
        });
        popupGetPictureView.showPop(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }

        switch (requestCode) {
            case IETConstant.GETPICTURE_TAKEPHOTO:
//                userImgFile = GetPictureUtils.cutPicture(getMContext(), userImgFile);
                presenter.compressAndcommitImg(userImgFile, type);
                break;
            case IETConstant.GETPICTURE_SELECTPHOTO:
                userImgFile = GetPictureUtils.getPhotoFromIntent(data, getMContext());
//                userImgFile = GetPictureUtils.cutPicture(getMContext(), userImgFile);
                presenter.compressAndcommitImg(userImgFile, type);
                break;
            case IETConstant.CUT_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    presenter.compressAndcommitImg(userImgFile, type);
                }
                break;
            default:
                break;
        }

    }

    int type;
    int remove;

}
