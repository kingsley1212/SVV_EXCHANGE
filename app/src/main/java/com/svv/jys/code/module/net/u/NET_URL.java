package com.svv.jys.code.module.net.u;

import android.content.Context;

import com.svv.jys.code.common.utils.ToolUtils;

/****
 * 接口地址
 *
 * @author Administrator
 *
 */
public class NET_URL {

    // /***************************服务器地址**********************///////
    // RELEASE 生产环境
    // DEBUG 测试环境
    public String MODE = "RELEASE";
    private static NET_URL u = new NET_URL();
    public final String TEST_SERIVCE = "http://192.168.1.122/191120flb/digital/";

    //    public final String RELEASE_SERIVCE = "https://www.gast.vip/";//测试地址https://www.coinebtoken.com/
    public final String RELEASE_SERIVCE = "https://www.svv.cool/";//正式地址
//    public final String RELEASE_SERIVCE = "https://wwwnew.svv.cool/";//新的正式地址
    public final String API = "app/";
    public final String IMAGE_URL = "https://www.svv.cool/";
//    public final String IMAGE_URL = "https://wwwnew.svv.cool/";

    public final String RELEASE_SERIVCE_K = "https://www.svv.cool";//K线展示
//    public final String RELEASE_SERIVCE_K = "https://wwwnew.svv.cool";//K线展示


    /*****数据服务器socket******/
//    public final String DATA_SERVER = "ws://192.168.1.122:2020";
    public final String DATA_SERVER = "wss://www.svv.cool/wss";
//    public final String DATA_SERVER = "wss://wwwnew.svv.cool/wss";

    public String getDATA_SERVER(Context context) {
        return ToolUtils.getSocketNet(context, DATA_SERVER);
//        return DATA_SERVER;
    }

    /*****聊天服务器socket******/
//    private String CHAT_SERVER = "ws://192.168.1.122:2120";
//    public final String CHAT_SERVER_DEFULT = "http://119.23.160.13:2120";
    private String CHAT_SERVER = "wss://www.svv.cool/socketio";
//    private String CHAT_SERVER = "wss://wwwnew.svv.cool/socketio";

    public String getCHAT_SERVER(Context context) {
        return ToolUtils.getSocketChat(context, CHAT_SERVER);
    }

    public void setCHAT_SERVER(String CHAT_SERVER) {
        this.CHAT_SERVER = CHAT_SERVER;
    }

    // 获取实体
    public static NET_URL getInstance() {
        return u;
    }

    /**
     * 获取app版本信息
     */
    public final static String GET_APPVERSION_DATA = "base/index/upgradeversion";
    /**
     * 显示是否为测试系统
     */
    public static final String isCeshi = "/app?s=Default.IsTest";
    /**
     * 用户登录
     */
    public static final String POST_DOLOGIN = "user/exchange_user/login";
    /**
     * 退出登录
     */
    public static final String Logout = "base/exchange_index/logout";
    /**
     * 首页轮播图
     */
    public static final String POST_HOME_BANNER = "/app?s=Default.GetBanner";
    /*
     * 获取最新公告
     */
    public static final String POST_Notice = "/app?s=Article.GetNotice";
    /**
     * 用户资产数据
     */
    public static final String POST_UserCoin = "digital/exchange_usercoin/list";
    /**
     * 杠杆账户数据
     */
    public static final String LtUserCoin = "/lt/app?s=LtUserCoin.UserCoinList";
    /**
     * 获取交易区
     */
    public static final String GetMarketArea = "exchange/exchange_market/marketlistbyarea";
    /**
     * 获取市场列表
     */
    public static final String GetMarketList = "/digital/app?s=CoinMarket.GetMarketList";
    /**
     * 币交易 根据限价提交委托
     */
    public static final String PostCoinBusinessUpTrade = "exchange/exchange_trade/uptrade";
    /**
     * 币交易 根据市价提交委托
     */
    public static final String PostCoinBusinessUpTradeByMarket = "/digital/app?s=CoinTrade.UpMarketPriceTrade";
    /**
     * 币交易 撤销委托
     */
    public static final String PostCoinBusinessTradeRevoke = "exchange/exchange_trade/cancel";

    /**
     * 杠杆交易 根据限价提交委托
     */
    public static final String PostBarBusinessUpTrade = "/digital/app?s=CoinTrade.UpTrade";
    /**
     * 杠杆交易 根据市价提交委托
     */
    public static final String PostBarBusinessUpTradeByMarket = "/digital/app?s=CoinTrade.UpMarketPriceTrade";
    /**
     * 杠杆交易 撤销委托
     */
    public static final String PostBarBusinessTradeRevoke = "/digital/app?s=CoinTrade.Revoke";


    /**
     * 获取委托记录
     */
    public static final String GetTradeList = "/digital/app?s=CoinTrade.TradeList";
    /**
     * 获取我的委托记录
     */
    public static final String GetMyTradeList = "/digital/app?s=CoinTrade.MyTradeList";

    /**
     * 实名认证提交
     */
    public static final String PostIdentity = "identity/exchange_identity/insert";
    /**
     * 获取地址列表
     */
    public static final String GetAddress = "digital/exchange_coinaddress/list";
    /**
     * 用户地址新增
     */
    public static final String AddAddress = "digital/exchange_coinaddress/insert";
    /**
     * 删除用户地址
     */
    public static final String deleteAddress = "digital/exchange_coinaddress/delete";
    /**
     * 获取所有币种
     */
    public static final String GetAllCoins = "digital/exchange_coinaddress/list";
    /**
     * 获取验证码
     */
    public static final String POST_REGISTERCODE = "base/exchange_index/sendcode";
    /**
     * 手机号码修改密码
     */
    public static final String POST_DOFINDPWD = "";

    /**
     * 获取注册协议
     */
    public static final String GET_PROTOCOL = "";
    /**
     * 注册
     */
    public static final String POST_DOREGISTER = "user/exchange_user/register";
    /**
     * 获取用户个人信息
     */
    public static final String GET_USERINFODATA = "user/exchange_user/infoview";
    /**
     * 上传图片
     */
    public static final String POST_MYORDEREVALUATEIMG = "user/exchange_user/uploadlogo";
    /**
     * 找回登录密码第一步
     */
    public static final String ChangePwdFirst = "/app?s=Default.ChangePwdFirst";
    /**
     * 找回登录密码第二步
     */
    public static final String ChangePwdFirst2 = "user/exchange_user/findpwd";
    /**
     * 设置安全密码提交
     */
    public static final String SetSecPwd = "user/exchange_user/secpwd";
    /**
     * 获取国家、区号
     */
    public static final String GetCountries = "base/index/getcountries";
    /**
     * 上传图片信息
     */
    public static final String UploadImage = "base/exchange_index/uploadimage";
    /**
     * 删除文件
     */
    public static final String RemoveFile = "/app?s=Default.RemoveFile";
    /**
     * 获取最新一条实名认证申请
     */
    public static final String IdentityInfo = "identity/exchange_identity/index";
    /**
     * 个人推广海报
     */
    public static final String InviteImages = "/digital/app?s=InviteReward.InviteImages";
    /**
     * 获取文章内容
     */
    public static final String GetArticleInfo = "base/exchange_article/info";
    /**
     * 获取我的委托记录
     */
    public static final String MyTradeList = "exchange/exchange_trade/list";
    /**
     * 撤销委托
     */
    public static final String UndoENtrust = "exchange/exchange_trade/cancel";
    /**
     * 委托成交数据
     */
    public static final String EntrustDetail = "exchange/exchange_tradelog/list";
    /**
     * 钱包日志数据
     */
    public static final String TradeAccountRecord = "/digital/app?s=CoinLog.ListData";
    /**
     * 币种转出
     */
    public static final String TransferOut = "digital/exchange_transfer/insert";
    /**
     * 获取币种信息
     */
    public static final String GetCoinInfo = "digital/exchange_usercoin/cashparams";
    /**
     * 杠杆钱包日志数据
     */
    public static final String GetLtAccountRecord = "/lt/app?s=LtCoinLog.ListData";
    /**
     * 查询市场信息
     */
    public static final String GetMarketInfo = "/digital/app?s=CoinMarket.GetMarketInfo";
    /**
     * 用户杠杆资产信息
     */
    public static final String GetLtUserCoinInfo = "/lt/app?s=LtUserCoin.GetUserCoinInfo";
    /**
     * 用户资产信息
     */
    public static final String GetUserCoinInfo = "/digital/app?s=UserCoin.GetUserCoinInfo";
    /**
     * 借贷申请
     */
    public static final String PostltDebit = "/lt/app?s=LtDebitApply.DoInfo";
    /**
     * 币种转账数据
     */
    public static final String GetMentionRecord = "/digital/app?s=CoinTransfer.ListData";
    /**
     * 币种转入数据
     */
    public static final String GetChargeRecord = "/digital/app?s=CoinTransferIn.ListData";
    /**
     * 杠杆转账数据
     */
    public static final String GetLtTransferRecord = "/lt/app?s=LtCoinTransfer.ListData";
    /**
     * 借贷申请数据
     */
    public static final String GetLtBorrowRecord = "/lt/app?s=LtDebitApply.ListData";
    /**
     * 借贷归还
     */
    public static final String PostReimbursement = "/lt/app?s=LtDebitApply.DoReturn";
    /**
     * 杠杆币种转账
     */
    public static final String PostLtCoinTransfet = "/lt/app?s=LtCoinTransfer.DoInfo";
    /**
     * 设置币种地址
     */
    public static final String SetAddress = "digital/exchange_usercoin/address";
    /**
     * 修改密码
     */
    public static final String changePassword = "/app?s=User.DoPwd";
    /**
     * 绑定邮箱
     */
    public static final String bindEmail = "user/exchange_user/bindemail";
    /**
     * 绑定手机
     */
    public static final String bindPhone = "user/exchange_user/bindmobile";
    /**
     * 法币账号资产列表
     */
    public static final String qtcUserCoin = "digital/otc_usercoin/list";
    /**
     * 锁仓账号资产列表
     */
    public static final String qtc_SUO_CANG_UserCoin = "digital/coinbank_usercoin/list";
    /**
     * 法币资产信息
     */
    public static final String qtcUserCoininfo = "/otc/app?s=OtcUserCoin.GetUserCoinInfo";
    /**
     * 法币钱包日志数据
     */
    public static final String qtcUserCoinList = "/otc/app?s=OtcCoinLog.ListData";
    /**
     * 获取所有币种
     */
    public static final String otcCoin = "digital/exchange_bonustransfer/getcoins";
    /**
     * 法币币种转账
     */
    public static final String otcTransfer = "digital/exchange_bonustransfer/insert";
    /**
     * 所有法币广告
     */
    public static final String otcAdv = "otc/exchange_advs/list";
    /**
     * 购买订单
     */
    public static final String otcAdvBuy = "otc/exchange_order/addorder";
    /**
     * 订单数据
     */
    public static final String getOtcOrder = "otc/exchange_order/list";
    /**
     * 商家订单数据
     */
    public static final String getshopOrder = "/otc/app?s=OtcOrders.ShopData";
    /**
     * 修改状态
     */
    public static final String SetStatus = "otc/exchange_order/setstatus";
    /**
     * 订单申诉
     */
    public static final String orderShensu = "otc/exchange_order/appeal";
    /**
     * 收藏
     */
    public static final String collectMarket = "exchange/exchange_market/collect";
    /**
     * 支付方式数据
     */
    public static final String paymethod = "otc/exchange_userpayment/list";
    /**
     * 会员支付方式新增或编辑
     */
    public static final String addpaymethod = "otc/exchange_userpayment/doinfo";
    /**
     * 删除支付方式
     */
    public static final String adeletepaymethod = "otc/exchange_userpayment/delInfo";
    /**
     * 获取订单信息
     */
    public static final String getorderDetail = "otc/exchange_order/infoview";
    /**
     * 查看商家信息
     */
    public static final String businessInfo = "otc/exchange_advs/main";

    /**
     * 获取商家出售和购买列表
     */
    public static final String businessList = "otc/exchange_advs/mainList/";

    /**
     * 获取谷歌验证信息
     */
    public static final String getGoogleInfo = "usergoogle/exchange_usergoogle/infoview";
    /**
     * 谷歌验证提交
     */
    public static final String setGoogleRenz = "user/exchange_user/bindgoogle";
    /**
     * 协议
     */
    public static final String protocol = "exchange_protocol/infoview";
    /**
     * 协议
     */
    public static final String sj_protocol = "otc/exchange_business/protocol";
    /**
     * 获取发布广告配置信息
     */
    public static final String GetAdvSetting = "otc/exchange_advs/getadvsetting";
    /**
     * 获取溢价
     */
    public static final String yijia = "otc/exchange_advs/getpremium";
    /**
     * 发布广告
     */
    public static final String publish = "otc/exchange_advs/addadv";

    /**
     * 我的广告
     */
    public static final String advlist = "otc/exchange_advs/myadv";
    /**
     * 修改状态
     */
    public static final String AdvSetStatus = "otc/exchange_advs/update";
    /**
     * 判断用户能否发布广告
     */
    public static final String ispublish = "/otc/app?s=OtcAdv.IsBusiness";
    /**
     * 启用或者禁用
     */
    public static final String setBankStatus = "otc/exchange_userpayment/setstatus";
    /**
     * 上传崩溃信息
     */
    public final static String POST_CRASHLOG = "/app?s=Default.UploadErrorFile";
    /**
     * 获取聊天服务器地址
     */
    public static final String GET_CHATIP = "s=Default.GetSysSetting";

    /**
     * 获取C2C交易区域
     */
    public static final String c2ctrademarket = "/digital/app?s=Coin.GetC2cTrade";
    /**
     * 获取币种信息
     */
    public static final String c2ccoininfo = "/digital/app?s=Coin.GetCoinInfo";
    /**
     * 提交c2c交易
     */
    public static final String c2cbuyorsell = "c2c/exchange_c2c/uptrade";
    /**
     * 获取c2c交易记录
     */
    public static final String GetC2cRecordUrl = "c2c/exchange_c2c/list";
    /**
     * c2c撤单
     */
    public static final String C2cCancelUrl = "c2c/exchange_c2c/cancel";
    /**
     * 用户银行数据
     */
    public static final String banklist = "userbank/exchange_userbank/list";
    /**
     * 删除银行信息
     */
    public static final String DeleteBankMesUrl = "userbank/exchange_userbank/delete";
    /**
     * 用户银行新增或编辑
     */
    public static final String addoreditbank = "userbank/exchange_userbank/insert";

    /**
     * 支付方式
     */

    public static final String pay = "otc/exchange_userpayment/incomeparternlist";
    /**
     * 验证方式
     */
    public static final String verifyMethod = "/app?s=User.SetVerify";

    /**
     * 验证
     */
    public static final String verify = "/app?s=User.CheckVerifyKey";

    /**
     * 首页数据
     */
    public static final String API_POSTINDEX = "user/exchange_user/index";

    /**
     * 货币信息
     */
    public static final String API_GET_CURRENCY_INFO = "legal/currency/index";

    /**
     * 获取币种
     */
    public static final String API_GET_COIN_LIST = "digital/exchange_coin/coingroup";

    /**
     * 获取用户币币资产币信息
     */
    public static final String API_GET_OTCINFO = "digital/otc_coinlog/list";

    /**
     * 获取用户币币资产币信息
     */
    public static final String API_GET_COININFO = "digital/exchange_coinlog/list";
    /**
     * 获取用户锁仓资产币信息
     */
    public static final String API_GET_SUOCANG = "digital/coinbank_coinlog/list";

    /**
     * 获取C2c页面信息
     */
    public static final String API_GET_C2CINDEXINFO = "c2c/exchange_c2c/index";

    /**
     * 获取币币交易页面信息
     */
    public static final String API_GET_BBINDEXINFO = "exchange/exchange_trade/gettradeinfo";

    /**
     * 商家申请
     */
    public static final String API_GET_APPLYDATA = "otc/exchange_business";

    /**
     * 商家申请
     */
    public static final String API_DO_APPLY = "otc/exchange_business/insert";

    /**
     * 获取验证情况
     */
    public static final String API_GET_VERIFY = "user/exchange_user/infosafe";

    /**
     * 获取订单详情
     */
    public static final String API_GET_ADVINFO = "otc/exchange_Advs/getadvinfo";

    /**
     * 获取验证方式
     */
    public static final String API_GET_USERVERIFY = "base/exchange_index/userverify";

    /**
     * 验证验证方式
     */
    public static final String API_GET_CHECKVERIFY = "base/exchange_index/checkverify";

    /**
     * 开关验证码
     */
    public static final String API_CHANGEVERIFY = "user/exchange_user/changeverify";

    /**
     * 重命名
     */
    public static final String POST_RName = "user/exchange_user/setnickname";


    /**
     * 上传二维码
     */
    public static final String PayCode = "base/exchange_index/uploadimage";

    /**
     * 验证添加支付方式
     */
    public static final String verifyPayment = "otc/exchange_userPayment/incomeparterninfo";
    /**
     * 消息中心
     */
    public static final String POST_NOTICE = "base/exchange_article/notice";

    /**
     * 获取广告信息
     */
    public static final String advInfo = "otc/exchange_advs/myinfo";

    public static final String mentionRecord = "digital/exchange_transfer/list";

    public static final String welfare = "welfare/exchange_welfare/list";

    public static final String welfareRecord = "welfare/exchange_userwelfare/list";
    public static final String receive = "welfare/exchange_userwelfare/receive";
    public static final String orderInfo = "otc/exchange_order/infouser";

    /**
     * 划转记录
     */
    public static final String POST_Transfer_Record = "digital/exchange_bonustransfer/list";

    /**
     * 邀请数据
     */
    public static final String POST_Invite = "user/exchange_user/invite";

    /**
     * 安全密码设置
     */
    public static final String changepwdtype = "user/exchange_user/changepwdtype";

    /**
     * 邀请记录
     */
    public static final String POST_Invite_Record = "user/exchange_user/userlist";

    /**
     * 佣金记录
     */
    public static final String POST_Reward_Record = "exchange/exchange_reward/list";

    /**
     * 撤销提币
     */
    public static final String POST_DELETE_TRANSFER = "digital/exchange_transfer/delete";

    /**
     * 充币记录
     */
    public static final String POST_LIST_CHARGE = "digital/exchange_transferin/list";

    /**
     * 获取文章列表
     */
    public static final String POST_ARTICLE_CLASS = "base/exchange_article/firstclass";

    /**
     * 获取交易对
     */
    public static final String POST_GET_EXCHANGE = "digital/exchange_coin/getexchangearea";

    /**
     * 币种信息
     */
    public static final String POST_COIN_INFO = "exchange/exchange_market/info";


    public static final String SUB_INFO = "c2c/exchange_sub/index";

    public static final String SUB = "convert/exchange_convert/insert";

    public static final String sub_list = "convert/exchange_convert/list";

    public static final String getcoindata = "convert/exchange_convert/getcoindata";
    /**
     * 获取锁仓配置列表coinbank/exchange_CoinBank/getSettingList
     */
    public static final String getNumBankList = "coinbank/exchange_CoinBank/getSettingList";
    /**
     * 用户锁仓
     */
    public static final String suocang = "coinbank/exchange_CoinBank/addCoinBank";
    /**
     * 获取应反利息
     */
    public static final String reckon = "coinbank/exchange_CoinBank/reckon";
    /**
     * 用户锁仓数据
     */
    public static final String suocangRecord = "coinbank/exchange_CoinBank/list";

    /**
     * 认购配售
     */
    public static final String rengou = "buysell/exchange_buysell/insert";
    /**
     * 认购配售记录
     */
    public static final String rengourecord = "buysell/exchange_buysell/list";

    /**
     * 认购撤销
     */
    public static final String Cancel = "buysell/exchange_buysell/cancel";

    /**
     * 获取服务器地址前缀
     *
     * @return
     */
    public String getUrlPerfix() {
        switch (MODE) {
            case "DEBUG":
                return TEST_SERIVCE;
            case "RELEASE":
                return RELEASE_SERIVCE;
            default:
                return RELEASE_SERIVCE;
        }
    }

    /**
     * 获取邀请码链接
     */
    public String getInviteLink(String pid) {
        return getUrlPerfix() + "public/wap/html/user/exchange/register.html?pid=" + pid;
    }

    /**
     * 获取用户图像地址
     *
     * @param urlSuffix 图像地址后缀
     * @return
     */
    public String getUserHeadImgUrl(String urlSuffix) {
        switch (MODE) {
            case "DEBUG":
                return TEST_SERIVCE + "/public/static/" + urlSuffix;
            case "RELEASE":
                return IMAGE_URL + "/public/static/" + urlSuffix;
            default:
                return IMAGE_URL + "/public/static/" + urlSuffix;
        }
    }

    // 获取接口路径
    public String getUrl(String api) {
        switch (MODE) {
            case "DEBUG":
                return TEST_SERIVCE + API + api;
            case "RELEASE":
                return RELEASE_SERIVCE + API + api;
            default:
                return RELEASE_SERIVCE + API + api;
        }
    }

}
