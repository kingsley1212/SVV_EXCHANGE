package com.svv.jys.code.module.myself.login.userlogin.presenter;

import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.FToken;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.login.userlogin.model.IUserLoginModel;
import com.svv.jys.code.module.myself.login.userlogin.model.impl.UserLoginModelImpl;
import com.svv.jys.code.module.myself.login.userlogin.view.IUserLoginView;
import com.svv.jys.code.module.net.req.POST_LOGIN_REQ;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class UserLoginPresenter extends BasePresent<IUserLoginView, IUserLoginModel> {

    public UserLoginPresenter() {
        model = new UserLoginModelImpl();
    }

    public void doLogin(String username, String psw) {
        POST_LOGIN_REQ req = new POST_LOGIN_REQ();
        req.username = username;
        req.password = psw;
        model.doLogin(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, FToken>() {
            @Override
            public FToken call(BaseResponse baseResponse) {
                FToken fToken = new FToken();
                fToken.fromJSONAuto(baseResponse.datas);
                ACache.get(view.getMContext()).put(ACEConstant.ACE_USERINFO_USERID, fToken.getUid());//用户ID
                ToolUtils.saveToken(view.getMContext(), fToken);

                return fToken;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FToken>() {
            @Override

            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());
            }

            @Override
            public void onNext(FToken s) {
                getUserInfoData();

            }
        });
    }

    /**
     * 获取用户信息
     *
     */
    public void getUserInfoData() {
        model.rx_getUserInfo()
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, FUserInfoEntity>() {
            @Override
            public FUserInfoEntity call(BaseResponse baseResponse) {
                FUserInfoEntity entity = new FUserInfoEntity();
                entity.fromJSONAuto(baseResponse.datas);
                return entity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FUserInfoEntity>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, false);
            }

            @Override
            public void onNext(FUserInfoEntity fUserInfoEntity) {
                ToolUtils.saveUserInfo(view.getMContext(), fUserInfoEntity);
                view.loginSuccese();
            }
        });
    }



}
