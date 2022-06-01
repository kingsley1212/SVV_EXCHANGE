package com.svv.jys.code.module.net.api;

import com.svv.jys.code.common.base.net.BaseRXNetApi;
import com.svv.jys.code.common.base.net.BaseRequest;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.module.net.req.ADD_OR_EDIT_BANKINFO_REQ;
import com.svv.jys.code.module.net.req.AddPayReq;
import com.svv.jys.code.module.net.req.AdvInfoReq;
import com.svv.jys.code.module.net.req.AdvListReq;
import com.svv.jys.code.module.net.req.AdvPulishReq;
import com.svv.jys.code.module.net.req.BANKADDRESS_DELETE_REQ;
import com.svv.jys.code.module.net.req.BankStatusReq;
import com.svv.jys.code.module.net.req.BusinessInfoReq;
import com.svv.jys.code.module.net.req.C2CBuyOrSellReq;
import com.svv.jys.code.module.net.req.C2CCoinInfoReq;
import com.svv.jys.code.module.net.req.ChangePswTypeReq;
import com.svv.jys.code.module.net.req.CollectMarketReq;
import com.svv.jys.code.module.net.req.CunFangRecordReq;
import com.svv.jys.code.module.net.req.DO_C2CCANCEL_REQ;
import com.svv.jys.code.module.net.req.DeletePayReq;
import com.svv.jys.code.module.net.req.GET_ADDRESS_LIST_REQ;
import com.svv.jys.code.module.net.req.GET_APPVERSION_REQ;
import com.svv.jys.code.module.net.req.GET_ARTICLE_REQ;
import com.svv.jys.code.module.net.req.GET_BARBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.GET_COININFO_REQ;
import com.svv.jys.code.module.net.req.GET_ENTRUST_REQ;
import com.svv.jys.code.module.net.req.GET_FBBUSINESS_ASSET_REQ;
import com.svv.jys.code.module.net.req.GET_LT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GET_MARKETINFO_REQ;
import com.svv.jys.code.module.net.req.GET_MARKET_LIST_REQ;
import com.svv.jys.code.module.net.req.GET_MY_TRADELIST_REQ;
import com.svv.jys.code.module.net.req.GET_ORDER_DETAIL_REQ;
import com.svv.jys.code.module.net.req.GET_OTC_ADV_REQ;
import com.svv.jys.code.module.net.req.GET_OTC_ORDER_REQ;
import com.svv.jys.code.module.net.req.GET_SHOP_ORDER_REQ;
import com.svv.jys.code.module.net.req.GET_TRADEACCOUNT_RECORD_REQ;
import com.svv.jys.code.module.net.req.GET_TRADELIST_REQ;
import com.svv.jys.code.module.net.req.GET_USER_COIN_INFO;
import com.svv.jys.code.module.net.req.GetC2CRecordListReq;
import com.svv.jys.code.module.net.req.GoogleRenzReq;
import com.svv.jys.code.module.net.req.MentionRecordReq;
import com.svv.jys.code.module.net.req.OrderDetailReq;
import com.svv.jys.code.module.net.req.POST_ADD_ADDRESS_REQ;
import com.svv.jys.code.module.net.req.POST_ADV_INFO;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_SUBMIT_MARKETPRICE_REQ;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_SUBMIT_REQ;
import com.svv.jys.code.module.net.req.POST_BARBUSINESS_TRADEREVOKE_REQ;
import com.svv.jys.code.module.net.req.POST_BINDEMAIL_REQ;
import com.svv.jys.code.module.net.req.POST_BINDPHONE_REQ;
import com.svv.jys.code.module.net.req.POST_CHANGEPSW_REQ;
import com.svv.jys.code.module.net.req.POST_CHANGE_VERIFY;
import com.svv.jys.code.module.net.req.POST_CHATFILE_REQ;
import com.svv.jys.code.module.net.req.POST_CHECK_VERIFY;
import com.svv.jys.code.module.net.req.POST_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_SUBMIT_MARKETPRICE_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_SUBMIT_REQ;
import com.svv.jys.code.module.net.req.POST_COINBUSINESS_TRADEREVOKE_REQ;
import com.svv.jys.code.module.net.req.POST_CRASHLOG_REQ;
import com.svv.jys.code.module.net.req.POST_DELETE_ADDRESS_REQ;
import com.svv.jys.code.module.net.req.POST_FORGETLOGINPWD_REQ;
import com.svv.jys.code.module.net.req.POST_FORGETPSW1_REQ;
import com.svv.jys.code.module.net.req.POST_FORGETPSW2_REQ;
import com.svv.jys.code.module.net.req.POST_GETVERIFY;
import com.svv.jys.code.module.net.req.POST_IDENTITY_IMG_REQ;
import com.svv.jys.code.module.net.req.POST_IDENTITY_REQ;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;
import com.svv.jys.code.module.net.req.POST_LOGIN_REQ;
import com.svv.jys.code.module.net.req.POST_LT_DEBIT_REQ;
import com.svv.jys.code.module.net.req.POST_LT_TRANSFER_REQ;
import com.svv.jys.code.module.net.req.POST_NOTICE_REQ;
import com.svv.jys.code.module.net.req.POST_OTC_BUY_REQ;
import com.svv.jys.code.module.net.req.POST_OTC_COIN;
import com.svv.jys.code.module.net.req.POST_OTC_TRANSFER_REQ;
import com.svv.jys.code.module.net.req.POST_REGISTER_CODE_REQ;
import com.svv.jys.code.module.net.req.POST_REGISTER_REQ;
import com.svv.jys.code.module.net.req.POST_REMOVEFILE_REQ;
import com.svv.jys.code.module.net.req.POST_R_NAME;
import com.svv.jys.code.module.net.req.POST_SAFEPSW_REQ;
import com.svv.jys.code.module.net.req.POST_SHENSU_REQ;
import com.svv.jys.code.module.net.req.POST_STASUS_REQ;
import com.svv.jys.code.module.net.req.POST_TRANSFEROUT_REQ;
import com.svv.jys.code.module.net.req.POST_UNDO_ENTRUST_REQ;
import com.svv.jys.code.module.net.req.POST_UPDATA_IMG_REQ;
import com.svv.jys.code.module.net.req.POST_VERIFY_PAYMENT;
import com.svv.jys.code.module.net.req.PayMethodReq;
import com.svv.jys.code.module.net.req.Post_Reimbursement_Req;
import com.svv.jys.code.module.net.req.RenGouChexiaoReq;
import com.svv.jys.code.module.net.req.RenGouRea;
import com.svv.jys.code.module.net.req.RenGouRecordReq;
import com.svv.jys.code.module.net.req.SetStatusReq;
import com.svv.jys.code.module.net.req.SuoCangReq;
import com.svv.jys.code.module.net.req.VerifyMethodReq;
import com.svv.jys.code.module.net.req.VerifyReq;
import com.svv.jys.code.module.net.req.WelfareReq;
import com.svv.jys.code.module.net.req.YiJiaReq;
import com.svv.jys.code.module.net.u.NET_URL;

import rx.Observable;

/**
 * Created by Administrator on 2018/4/27 0027.
 */

public class API_Factory {

    /**
     * 获取app版本
     *
     * @param req
     * @return
     */
    public static Observable<BaseResponse> API_getAppVersionData(GET_APPVERSION_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GET_APPVERSION_DATA, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 此系统当前为测试系统，随时清空数据
     */
    public static Observable API_isCeshi() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.isCeshi, BaseRXNetApi.RXExecuteType.POST, new BaseRequest(), BaseResponse
                .class);
    }

    /**
     * 首页数据
     */
    public static Observable<BaseResponse> API_getIndexData() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_POSTINDEX, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse
                .class);
    }

    /**
     * 登录接口
     *
     * @param req
     * @return
     */
    public static Observable<BaseResponse> API_DoLogin(POST_LOGIN_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_DOLOGIN, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 退出登录
     */
    public static Observable<BaseResponse> API_DoLoginOut() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.Logout, BaseRXNetApi.RXExecuteType.POST, new BaseRequest(),
                BaseResponse
                        .class);
    }

    /**
     * 首页轮播图
     */
    public static Observable<BaseResponse> API_DoHomeBanner(POST_KONG_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_HOME_BANNER, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse
                        .class);
    }

    /**
     * 获取最新公告
     */
    public static Observable<BaseResponse> API_DoGetNotice(POST_KONG_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_Notice, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 用户资产数据
     */
    public static Observable<BaseResponse> API_DoGetUserCoin() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_UserCoin, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse
                .class);
    }

    /**
     * 获取交易区
     */
    public static Observable<BaseResponse> API_DoGetMarketArea(POST_KONG_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetMarketArea, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取市场列表
     */
    public static Observable<BaseResponse> API_DoGetMarketList(GET_MARKET_LIST_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetMarketList, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取委托记录
     */
    public static Observable<BaseResponse> API_DoTradeList(GET_TRADELIST_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetTradeList, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 获取我的委托记录
     */
    public static Observable<BaseResponse> API_DoGetMyTradeList(GET_MY_TRADELIST_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetMyTradeList, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 币交易 根据限价 提交委托
     */
    public static Observable<BaseResponse> API_DoCoinBusinessSubmit(POST_COINBUSINESS_SUBMIT_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostCoinBusinessUpTrade, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 币交易 根据市价 提交委托
     */
    public static Observable<BaseResponse> API_DoCoinBusinessSubmitByMarketPrice
    (POST_COINBUSINESS_SUBMIT_MARKETPRICE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostCoinBusinessUpTradeByMarket, BaseRXNetApi.RXExecuteType
                        .POST, req,
                BaseResponse.class);
    }

    /**
     * 币交易 撤销委托
     */
    public static Observable<BaseResponse> API_DoCoinBusinessTradeRevoke
    (POST_COINBUSINESS_TRADEREVOKE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostCoinBusinessTradeRevoke, BaseRXNetApi.RXExecuteType
                        .POST, req,
                BaseResponse.class);
    }


    /**
     * 杆杠交易 根据限价 提交委托
     */
    public static Observable<BaseResponse> API_DoBarBusinessSubmit(POST_BARBUSINESS_SUBMIT_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostBarBusinessUpTrade, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 杆杠交易 根据市价 提交委托
     */
    public static Observable<BaseResponse> API_DoBarBusinessSubmitByMarketPrice
    (POST_BARBUSINESS_SUBMIT_MARKETPRICE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostBarBusinessUpTradeByMarket, BaseRXNetApi.RXExecuteType
                        .POST, req,
                BaseResponse.class);
    }

    /**
     * 杆杠交易 撤销委托
     */
    public static Observable<BaseResponse> API_DoBarBusinessTradeRevoke
    (POST_BARBUSINESS_TRADEREVOKE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostBarBusinessTradeRevoke, BaseRXNetApi.RXExecuteType
                        .POST, req,
                BaseResponse.class);
    }


    /**
     * 实名认证提交
     */
    public static Observable<BaseResponse> API_DoIdentity(POST_IDENTITY_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostIdentity, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 获取地址列表
     */
    public static Observable<BaseResponse> API_DoGetAddress(GET_ADDRESS_LIST_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetAddress, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 用户地址新增
     */
    public static Observable<BaseResponse> API_DoAddAddress(POST_ADD_ADDRESS_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.AddAddress, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 获取所有币种
     */
    public static Observable<BaseResponse> API_DoGetAllCoins(POST_KONG_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetAllCoins, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 获取验证码
     */
    public static Observable<BaseResponse> API_DoAcquiringCode(POST_REGISTER_CODE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_REGISTERCODE, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 修改登录密码接口
     */
    public static Observable<BaseResponse> API_DoForgetLoginPwd(POST_FORGETLOGINPWD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_DOFINDPWD, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 获取协议
     *
     * @param req
     * @return
     */
    public static Observable<BaseResponse> API_GetProtocol(BaseRequest req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GET_PROTOCOL, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse
                        .class);
    }

    /**
     * 发起手机注册接口
     *
     * @param req
     * @return
     */
    public static Observable<BaseResponse> API_DoRegister(POST_REGISTER_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_DOREGISTER, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse
                        .class);
    }

    /**
     * 获取验证码
     */
    public static Observable<BaseResponse> API_DoCode(POST_CODE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_REGISTERCODE, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 获取个人信息
     *
     * @return
     */
    public static Observable<BaseResponse> API_GetUserInfo() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GET_USERINFODATA, BaseRXNetApi.RXExecuteType.POST, new
                        BaseRequest(),
                BaseResponse.class);
    }

    /**
     * 上传图片
     */
    public static Observable<BaseResponse> API_updataImg(POST_UPDATA_IMG_REQ req) {
        return BaseRXNetApi.rx_doExecuteMedia(NET_URL.POST_MYORDEREVALUATEIMG, req, BaseResponse.class);
    }

    /**
     * 找回登录密码第一步
     */
    public static Observable<BaseResponse> API_PostForgerPsw1(POST_FORGETPSW1_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.ChangePwdFirst, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 找回登录密码第二步
     */
    public static Observable<BaseResponse> API_PostForgerPsw2(POST_FORGETPSW2_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.ChangePwdFirst2, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 设置安全密码提交
     */
    public static Observable<BaseResponse> API_PostSafePsw(POST_SAFEPSW_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.SetSecPwd, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 删除用户地址
     */
    public static Observable<BaseResponse> API_PostdelelteAddress(POST_DELETE_ADDRESS_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.deleteAddress, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 获取国家、区号
     */
    public static Observable<BaseResponse> API_GetCountries(POST_KONG_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetCountries, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 上传图片信息
     */
    public static Observable<BaseResponse> API_UploadImage(POST_IDENTITY_IMG_REQ req) {
        return BaseRXNetApi.rx_doExecuteMedia(NET_URL.UploadImage, req, BaseResponse.class);
    }

    /**
     * 删除文件
     */
    public static Observable<BaseResponse> API_RemoveFile(POST_REMOVEFILE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.RemoveFile, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取最新一条实名认证申请
     */
    public static Observable<BaseResponse> API_GetIdentityInfo() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.IdentityInfo, BaseRXNetApi.RXExecuteType.POST, new BaseRequest
                (), BaseResponse.class);
    }

    /**
     * 获取文章内容
     */
    public static Observable<BaseResponse> API_GetArticleInfo(GET_ARTICLE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetArticleInfo, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse.class);
    }

    /**
     * 个人推广海报
     */
    public static Observable<BaseResponse> API_GetInviteImages() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.InviteImages, BaseRXNetApi.RXExecuteType.POST, new BaseRequest
                (), BaseResponse.class);
    }

    /**
     * 杠杆账户数据
     */
    public static Observable<BaseResponse> API_GetLtUserCoin(GET_BARBUSINESS_ASSET_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.LtUserCoin, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取我的委托记录
     */
    public static Observable<BaseResponse> API_GetMyTradeList(GET_ENTRUST_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.MyTradeList, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 撤销委托
     */
    public static Observable<BaseResponse> API_UndoEntrust(POST_UNDO_ENTRUST_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.UndoENtrust, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 委托成交数据
     */
    public static Observable<BaseResponse> API_GetEntrustDetail(GET_ORDER_DETAIL_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.EntrustDetail, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 钱包日志数据
     */
    public static Observable<BaseResponse> API_GetTradeAccountRecord(GET_TRADEACCOUNT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.TradeAccountRecord, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse
                        .class);
    }

    /**
     * 币种转出
     */
    public static Observable<BaseResponse> API_PostCoinTransferOut(POST_TRANSFEROUT_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.TransferOut, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取币种信息
     */
    public static Observable<BaseResponse> API_GetCoinInfo(GET_COININFO_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetCoinInfo, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 杠杆钱包日志数据
     */
    public static Observable<BaseResponse> API_GetLtRecord(GET_LT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetLtAccountRecord, BaseRXNetApi.RXExecuteType.POST, req,
                BaseResponse
                        .class);
    }

    /**
     * 查询市场信息
     */
    public static Observable<BaseResponse> API_GetMarketInfo(GET_MARKETINFO_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetMarketInfo, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 用户杠杆资产信息
     */
    public static Observable<BaseResponse> GetLtUserCoinInfo(GET_MARKETINFO_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetLtUserCoinInfo, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 用户资产信息
     */
    public static Observable<BaseResponse> GetUserCoinInfo(GET_USER_COIN_INFO req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetUserCoinInfo, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    public static Observable<BaseResponse> GetWalletCoinInfo(GET_TRADEACCOUNT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_COININFO, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    public static Observable<BaseResponse> GetWalletOtcInfo(GET_TRADEACCOUNT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_OTCINFO, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 借贷申请
     */
    public static Observable<BaseResponse> postLtDebitApply(POST_LT_DEBIT_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostltDebit, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 币种转账数据
     */
    public static Observable<BaseResponse> MentionRecord(GET_TRADEACCOUNT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetMentionRecord, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 币种转入数据
     */
    public static Observable<BaseResponse> ChargeRecord(GET_TRADEACCOUNT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetChargeRecord, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 杠杆转账数据
     */
    public static Observable<BaseResponse> API_GetLtTransferRecord(GET_LT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetLtTransferRecord, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 借贷申请数据
     */
    public static Observable<BaseResponse> API_GetLtBorrowRecord(GET_LT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetLtBorrowRecord, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 借贷归还
     */
    public static Observable<BaseResponse> API_PostReimbursement(Post_Reimbursement_Req req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostReimbursement, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 杠杆币种转账
     */
    public static Observable<BaseResponse> API_PostTransfer(POST_LT_TRANSFER_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.PostLtCoinTransfet, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 设置币种地址
     */
    public static Observable<BaseResponse> API_SetAddress(GET_USER_COIN_INFO req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.SetAddress, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 修改密码
     */
    public static Observable<BaseResponse> API_changePassword(POST_CHANGEPSW_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.changePassword, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 绑定邮箱
     */
    public static Observable<BaseResponse> API_bindEmail(POST_BINDEMAIL_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.bindEmail, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 绑定手机
     */
    public static Observable<BaseResponse> API_bindPhone(POST_BINDPHONE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.bindPhone, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 法币账户资产列表
     */
    public static Observable<BaseResponse> API_GetQtcUserCoin(GET_FBBUSINESS_ASSET_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.qtcUserCoin, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 法币资产信息
     *
     * @param req
     * @return
     */
    public static Observable<BaseResponse> GetOtcCoinInfo(GET_USER_COIN_INFO req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.qtcUserCoininfo, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 法币钱包日志数据
     */
    public static Observable<BaseResponse> GetOtcCoinlist(GET_TRADEACCOUNT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.qtcUserCoinList, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    public static void API_postErrorCrashLog(POST_CRASHLOG_REQ req) {
        BaseRXNetApi.doExecuteErrorLog(NET_URL.POST_CRASHLOG, req);
    }

    /**
     * 获取所有法币币种
     *
     * @return
     */
    public static Observable<BaseResponse> API_GetotcCoin() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.otcCoin, BaseRXNetApi.RXExecuteType.POST, new BaseRequest
                (), BaseResponse.class);
    }

    /**
     * 获取所有法币币种
     *
     * @return
     */
    public static Observable<BaseResponse> API_GetotcCoin(POST_OTC_COIN req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.otcCoin, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }

    /**
     * 法币币种转账
     */
    public static Observable<BaseResponse> GetOtcTransfer(POST_OTC_TRANSFER_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.otcTransfer, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 所有法币广告
     */
    public static Observable<BaseResponse> GetOtcAdv(GET_OTC_ADV_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.otcAdv, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 购买订单
     */
    public static Observable<BaseResponse> PostOtcAdvBuy(POST_OTC_BUY_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.otcAdvBuy, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 订单数据
     */
    public static Observable<BaseResponse> GetOtcOrder(GET_OTC_ORDER_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.getOtcOrder, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 商家订单数据
     */
    public static Observable<BaseResponse> GetShopOrder(GET_SHOP_ORDER_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.getshopOrder, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 修改状态
     */
    public static Observable<BaseResponse> SetStatus(POST_STASUS_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.SetStatus, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 订单申诉
     */
    public static Observable<BaseResponse> orderShensu(POST_SHENSU_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.orderShensu, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 收藏市场
     */
    public static Observable<BaseResponse> collectMarket(CollectMarketReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.collectMarket, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 支付方式数据
     */
    public static Observable<BaseResponse> paymethod(PayMethodReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.paymethod, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 会员支付方式新增或编辑
     */
    public static Observable<BaseResponse> addOrEditPayMethod(AddPayReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.addpaymethod, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 删除支付方式
     */
    public static Observable<BaseResponse> deletePayMethod(DeletePayReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.adeletepaymethod, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取订单信息
     */
    public static Observable<BaseResponse> getOrderDetail(OrderDetailReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.getorderDetail, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 查看商家信息
     */
    public static Observable<BaseResponse> qureyBusinessInfo(BusinessInfoReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.businessInfo, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取商家交易信息
     */
    public static Observable<BaseResponse> getBusinessList(BusinessInfoReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.businessList, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取谷歌验证信息
     */
    public static Observable<BaseResponse> getGoogleInfo() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.getGoogleInfo, BaseRXNetApi.RXExecuteType.POST, new BaseRequest(), BaseResponse.class);
    }

    /**
     * 谷歌验证提交
     */
    public static Observable<BaseResponse> setGoogleRenz(GoogleRenzReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.setGoogleRenz, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }

    /**
     * 协议
     */
    public static Observable<BaseResponse> xieyi(GET_ARTICLE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.protocol, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }

    /**
     * 协议
     */
    public static Observable<BaseResponse> API_GETSJXY() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.sj_protocol, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse.class);
    }

    /**
     * 获取发布广告配置信息
     */
    public static Observable<BaseResponse> GetAdvSetting() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetAdvSetting, BaseRXNetApi.RXExecuteType.POST, new BaseRequest(), BaseResponse
                .class);
    }

    /**
     * 获取溢价
     */
    public static Observable<BaseResponse> GetYijia(YiJiaReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.yijia, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 发布广告
     */
    public static Observable<BaseResponse> pulish(AdvPulishReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.publish, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 我的广告
     */

    public static Observable<BaseResponse> advList(AdvListReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.advlist, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 修改状态
     */
    public static Observable<BaseResponse> setStatus(SetStatusReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.AdvSetStatus, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 查看用户能否发布广告
     */
    public static Observable<BaseResponse> isPublish() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.ispublish, BaseRXNetApi.RXExecuteType.POST, new BaseRequest(), BaseResponse
                .class);
    }

    /**
     * 启用或者禁用
     */
    public static Observable<BaseResponse> setBankStatus(BankStatusReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.setBankStatus, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取聊天服务器地址
     *
     * @param req
     * @return
     */
    public static Observable<BaseResponse> API_getChatIp(BaseRequest req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GET_CHATIP, BaseRXNetApi.RXExecuteType.GET, req, BaseResponse
                .class);
    }

    /**
     * 获取C2C交易区域
     */
    public static Observable<BaseResponse> c2ctrademarket() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.c2ctrademarket, BaseRXNetApi.RXExecuteType.POST, new BaseRequest(), BaseResponse
                .class);
    }

    /**
     * 获取币种信息
     */
    public static Observable<BaseResponse> c2cCoinInfo(C2CCoinInfoReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.c2ccoininfo, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 提交c2c交易
     */
    public static Observable<BaseResponse> c2cBuyOrSell(C2CBuyOrSellReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.c2cbuyorsell, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /***
     * 获取c2c记录
     * @param recordListReq
     * @return
     */
    public static Observable<BaseResponse> API_getC2CRecordList(GetC2CRecordListReq recordListReq) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.GetC2cRecordUrl, BaseRXNetApi.RXExecuteType.POST, recordListReq, BaseResponse
                .class);
    }

    /**
     * c2c撤单
     *
     * @return
     */
    public static Observable<BaseResponse> API_doC2CCancel(DO_C2CCANCEL_REQ doCancelReq) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.C2cCancelUrl, BaseRXNetApi.RXExecuteType.POST, doCancelReq, BaseResponse
                .class);
    }

    /**
     * 用户银行数据
     */
    public static Observable<BaseResponse> banklist() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.banklist, BaseRXNetApi.RXExecuteType.POST, new BaseRequest(), BaseResponse
                .class);
    }

    /**
     * 删除银行信息
     *
     * @return
     */
    public static Observable<BaseResponse> API_deleteBankMes(BANKADDRESS_DELETE_REQ addressDeleteReq) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.DeleteBankMesUrl, BaseRXNetApi.RXExecuteType.POST, addressDeleteReq, BaseResponse
                .class);
    }


//    /**
//     * 用户银行新增或编辑
//     */
//    public static Observable<BaseResponse> addbank(AddorEditBankReq req) {
//        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.addoreditbank, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
//                .class);
//    }


    /**
     * 用户银行新增或编辑
     */
    public static Observable<BaseResponse> addbank(ADD_OR_EDIT_BANKINFO_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.addoreditbank, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 支付方式
     */
    public static Observable<BaseResponse> payMethod() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.pay, BaseRXNetApi.RXExecuteType.POST, new BaseRequest(), BaseResponse
                .class);
    }

    /**
     * 验证方式
     */

    public static Observable<BaseResponse> setVerifyMethod(VerifyMethodReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.verifyMethod, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取交易区
     */
    public static Observable<BaseResponse> API_DoPostChatFile(POST_CHATFILE_REQ req) {
        return BaseRXNetApi.rx_doExecuteMedia(NET_URL.UploadImage, req, BaseResponse
                .class);
    }


    /**
     * 验证
     */
    public static Observable<BaseResponse> verify(VerifyReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.verify, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取货币信息
     */
    public static Observable<BaseResponse> API_GetCurrencyInfo() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_CURRENCY_INFO, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse
                .class);
    }

    /**
     * 获取币种
     */
    public static Observable<BaseResponse> API_GetCoinList() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_COIN_LIST, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse
                .class);
    }

    /**
     * 获取c2c页面信息
     */
    public static Observable<BaseResponse> API_GetC2cIndexInfo(GET_TRADEACCOUNT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_C2CINDEXINFO, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取币币页面信息
     */
    public static Observable<BaseResponse> API_GetBBData(GET_MARKET_LIST_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_BBINDEXINFO, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取商家申请信息
     */
    public static Observable<BaseResponse> API_GetApply() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_APPLYDATA, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse
                .class);
    }

    /**
     * 商家申请
     */
    public static Observable<BaseResponse> API_DoApply() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_DO_APPLY, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse
                .class);
    }

    /**
     * 获取验证情况
     */
    public static Observable<BaseResponse> API_GetVerify() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_VERIFY, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse
                .class);
    }

    /**
     * 获取订单详情
     */
    public static Observable<BaseResponse> API_GetAdvInfo(POST_ADV_INFO req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_ADVINFO, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 获取验证方式
     */
    public static Observable<BaseResponse> API_GetUserVerify(POST_GETVERIFY req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_USERVERIFY, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 验证验证方式
     */
    public static Observable<BaseResponse> API_CheckVerify(POST_CHECK_VERIFY req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_CHECKVERIFY, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 开关验证码
     */
    public static Observable<BaseResponse> API_CHANGEVERIFY(POST_CHANGE_VERIFY req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_CHANGEVERIFY, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 重命名
     */
    public static Observable<BaseResponse> API_RNAME(POST_R_NAME req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_RName, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }


    /**
     * 上传图片信息
     */
    public static Observable<BaseResponse> Api_payCode(POST_IDENTITY_IMG_REQ req) {
        return BaseRXNetApi.rx_doExecuteMedia(NET_URL.PayCode, req, BaseResponse.class);
    }


    public static Observable<BaseResponse> Api_advInfo(AdvInfoReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.advInfo, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }


    public static Observable<BaseResponse> Api_mentionRecord(MentionRecordReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.mentionRecord, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    public static Observable<BaseResponse> API_ChargeRecord(MentionRecordReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_LIST_CHARGE, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    public static Observable<BaseResponse> Api_welfare(WelfareReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.welfare, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    public static Observable<BaseResponse> Api_welfareRecord(WelfareReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.welfareRecord, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    public static Observable<BaseResponse> Api_welfarereceive(AdvInfoReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.receive, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }


    public static Observable<BaseResponse> orderInfo(AdvInfoReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.orderInfo, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 验证支付方式
     */
    public static Observable<BaseResponse> API_VerifyPayment(POST_VERIFY_PAYMENT req) {
        return BaseRXNetApi.rx_doExecuteMedia(NET_URL.verifyPayment, req, BaseResponse.class);
    }

    /**
     * 消息中心
     */
    public static Observable<BaseResponse> API_NOTICE(POST_NOTICE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_NOTICE, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }

    /**
     * 划转记录
     */
    public static Observable<BaseResponse> API_TransferRecord(PayMethodReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_Transfer_Record, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }

    /**
     * 邀请数据
     */
    public static Observable<BaseResponse> API_Invite() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_Invite, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse.class);
    }

    /**
     * 邀请记录
     */
    public static Observable<BaseResponse> API_InviteRecord(PayMethodReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_Invite_Record, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }

    /**
     * 佣金记录
     */
    public static Observable<BaseResponse> API_RewardRecord(PayMethodReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_Reward_Record, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }

    /**
     * 撤销提币
     */
    public static Observable<BaseResponse> API_DeleteTransfer(GET_ARTICLE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_DELETE_TRANSFER, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }

    /**
     * 获取文章列表
     */
    public static Observable<BaseResponse> API_GetArticleClass(POST_NOTICE_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_ARTICLE_CLASS, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }

    /**
     * 获取交易对
     */
    public static Observable<BaseResponse> API_GetExchangeArea() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_GET_EXCHANGE, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse.class);
    }

    /**
     * 币种信息
     */
    public static Observable<BaseResponse> API_GetCoinInfo(GET_MARKET_LIST_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.POST_COIN_INFO, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }


    public static Observable<BaseResponse> changepwdtype(ChangePswTypeReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.changepwdtype, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse.class);
    }


    public static Observable<BaseResponse> API_GetSettingInfo() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.SUB_INFO, BaseRXNetApi.RXExecuteType.POST, null, BaseResponse.class);
    }


    public static Observable<BaseResponse> API_getSubscriptionList(GetC2CRecordListReq recordListReq) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.sub_list, BaseRXNetApi.RXExecuteType.POST, recordListReq, BaseResponse
                .class);
    }

    public static Observable<BaseResponse> API_subscription(C2CBuyOrSellReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.SUB, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }


    public static Observable<BaseResponse> getcoindata(C2CBuyOrSellReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.getcoindata, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }
    /**
     * 获取数字银行配置列表
     */
    public static Observable<BaseResponse> getNumBankList() {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.getNumBankList, BaseRXNetApi.RXExecuteType.POST,new BaseRequest(), BaseResponse
                .class);
    }
    /**
     *用户锁仓
     */
    public static Observable<BaseResponse> suocang(SuoCangReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.suocang, BaseRXNetApi.RXExecuteType.POST,req, BaseResponse
                .class);
    }
    /**
     *用户获取锁仓数据
     */
    public static Observable<BaseResponse> getLiXi(SuoCangReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.reckon, BaseRXNetApi.RXExecuteType.POST,req, BaseResponse
                .class);
    }
    /**
     * 用户锁仓数据
     * @param req
     * @return
     */
    public static Observable<BaseResponse> suocangRecord(CunFangRecordReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.suocangRecord, BaseRXNetApi.RXExecuteType.POST,req, BaseResponse
                .class);
    }

    /**
     * 锁仓账户
     * @param req
     * @return
     */
    public static Observable<BaseResponse> API_getSuoCangAssetData(GET_FBBUSINESS_ASSET_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.qtc_SUO_CANG_UserCoin, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    public static Observable<BaseResponse> GetWalletSCInfo(GET_TRADEACCOUNT_RECORD_REQ req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.API_GET_SUOCANG, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 认购配售
     */
    public static Observable<BaseResponse> rengou(RenGouRea req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.rengou, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }
    /**
     * 认购配售记录
     */
    public static Observable<BaseResponse> rengourecord(RenGouRecordReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.rengourecord, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

    /**
     * 认购配售撤銷
     */
    public static Observable<BaseResponse> rengourechexiao(RenGouChexiaoReq req) {
        return BaseRXNetApi.rx_doExecuteQuick(NET_URL.Cancel, BaseRXNetApi.RXExecuteType.POST, req, BaseResponse
                .class);
    }

}
