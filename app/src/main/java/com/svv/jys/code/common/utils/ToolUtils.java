package com.svv.jys.code.common.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.pedaily.yc.ycdialoglib.selectDialog.CustomSelectDialog;
import com.svv.jys.R;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.db.nativecache.ACache;
import com.svv.jys.code.common.entity.FToken;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.entity.MarketListEntity;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.business.tradechat.base3.TradeChat3Act;
import com.svv.jys.code.module.myself.login.userlogin.UserLoginAct;
import com.svv.jys.code.module.net.exception.NeedLoginException;
import com.svv.jys.code.module.net.exception.NetException;
import com.svv.jys.code.module.net.u.NET_CODE;

import org.apache.commons.lang3.StringEscapeUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lzj on 2017/6/3.
 */

public class ToolUtils {


    private static final int FAST_CLICK_DELAY_TIME = 500;
    private static long lastClickTime;
    private static int viewId;
    public static boolean isFastClick(int id) {
        boolean flag ;

        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= FAST_CLICK_DELAY_TIME ) {
            flag = true;
        }else {
            if (id!=viewId){
                flag = true;
            }else {
                flag = false;
            }
        }
        lastClickTime = currentClickTime;
        viewId=id;
        return flag;
    }

    /**
     * 禁止输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    public static boolean isListNull(List list) {
        return list == null || list.size() == 0;
    }


    public static void enterNumPoint(Editable editable, int numpoint) {
        String temp = editable.toString();
        int posDot = temp.indexOf(".");
        if (posDot > 0) {
            if (temp.length() - posDot - 1 > numpoint) {
                editable.delete(posDot + numpoint + 1, editable.length());
            }
        }
    }

    /**
     * subtraction
     */
    public static String subtraction(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }

    /**
     * 乘法
     */
    public static String multiply(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.multiply(b2).toString();
    }

    public static String division(String v1, String v2, int s) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            BigDecimal result = b1.divide(b2, s, BigDecimal.ROUND_HALF_UP);
            return formatDouble(result.toString());
        } catch (Exception e) {
            return "0";
        }

    }

    /**
     * 字符串转int
     *
     * @param o
     * @return
     */
    public final static int String2Int(String o) {
        if (o != null) {
            try {
                return Integer.valueOf(o);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }

    }

    /**
     * String 转long
     *
     * @param o
     * @return
     */
    public final static long String2Long(String o) {
        if (o != null) {
            try {
                return Long.valueOf(o);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }

    }

    /**
     * String 转double
     *
     * @param o
     * @return
     */
    public final static double String2Double(String o) {
        if (o != null) {
            try {
                return Double.valueOf(o);
            } catch (Exception e) {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 乘法
     *
     * @param v1    乘数
     * @param v2    被乘数
     * @param scale 小数点保留位数
     * @return
     */
    public static String multiplyWithScale(String v1, String v2, int scale) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            BigDecimal result = b1.multiply(b2);
            result = result.setScale(scale, RoundingMode.HALF_EVEN);
            return formatDouble(result.toString());
        } catch (Exception e) {
            return " --- ";
        }

    }

    public static String formatDouble(String value) {
        NumberFormat nf = NumberFormat.getInstance();
        //设置保留多少位小数
        nf.setMaximumFractionDigits(8);
        // 取消科学计数法
        nf.setGroupingUsed(false);
        //返回结果
        return nf.format(Double.parseDouble(value));
    }

    public static String add(String v1, String v2) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            BigDecimal result = b1.add(b2);
            return result.toString();
        } catch (Exception e) {
            return " --- ";
        }
    }

    public static String add(String v1, String v2,int s) {
        try {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            BigDecimal result = b1.add(b2);
            result = result.setScale(s, RoundingMode.HALF_EVEN);
            return result.toString();
        } catch (Exception e) {
            return " --- ";
        }
    }


    /**
     * 移除多余的0
     *
     * @param s
     * @return
     */
    public static String trimZero(String s) {
        if (s.indexOf(".") > 0) {        // 去掉多余的0
            s = s.replaceAll("0+?$", "");        // 如最后一位是.则去掉
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }

    /***
     * Cny数据保留小数位
     * @param number
     * @return
     */
    public static String CnyFormat(String number) {
        return ToolUtils.doublePoint(ToolUtils.String2Double(number));
    }

    /***
     * 数字资产数据保留小数位
     * @param number
     * @return
     */
    public static String SzzcFormat(String number) {
        return ToolUtils.trimZero(ToolUtils.doublePoint8(ToolUtils.String2Double(number)));
    }


    /***
     * 数字资产数量数据保留小数位
     * @param number
     * @return
     */
    public static String SzNumFormat(String number) {
        return ToolUtils.trimZero(ToolUtils.doublePoint8(ToolUtils.String2Double(number)));
    }

//    /**
//     * String 转double
//     *
//     * @param o
//     * @return
//     */
//    public final static double String2Double(String o) {
//        if (o != null) {
//            try {
//                return Double.valueOf(o);
//            } catch (Exception e) {
//                return 0;
//            }
//        } else {
//            return 0;
//        }
//
//    }

    /**
     * String 转float
     *
     * @param o
     * @return
     */
    public final static float String2Float(String o) {
        if (o != null) {
            try {
                return Float.valueOf(o);
            } catch (Exception e) {
                return 0f;
            }
        } else {
            return 0f;
        }

    }

    /**
     * double型数据保留两位小数
     *
     * @return
     */
    public static String doublePoint(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.00");
        return df.format(double1);
    }

    public static String doublePoint4(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.0000");
        return df.format(double1);
    }

    /**
     * double型数据保留八位小数
     *
     * @return
     */
    public static String doublePoint8(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.00000000");
        return df.format(double1);
    }

    /**
     * double型数据保留六位小数
     *
     * @return
     */
    public static String doublePoint6(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.000000");
        return df.format(double1);
    }

    /**
     * double型数据保留整数
     *
     * @return
     */
    public static String doublePointToInt(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0");
        return df.format(double1);
    }

    /**
     * PopupWindow居中。
     * 设置焦点后再调用该方法才能点击外部消失
     *
     * @param popup
     * @param parent
     * @param x
     * @param y
     */
    public static void popupWindowShowCenter(PopupWindow popup, View parent, int x, int y) {
        popup.showAtLocation(parent, Gravity.CENTER, 0, 0);
        if (Build.VERSION.SDK_INT < 24) {
            popup.update();
        } else {
            popup.dismiss();
            popup.showAtLocation(parent, Gravity.CENTER, 0, 0);
        }

    }

    /**
     * Html.fromHtml
     *
     * @param textview
     * @param htmlFormat
     */
    public static void HTML_FromHtml(TextView textview, String htmlFormat) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textview.setText(Html.fromHtml((htmlFormat), Html.FROM_HTML_MODE_LEGACY));
        } else {
            textview.setText(Html.fromHtml(htmlFormat));
        }
    }

    /**
     * The BigDecimal class provides operations for arithmetic, scale manipulation, rounding, comparison, hashing,
     * and format conversion.
     *
     * @param d
     * @return
     */
    public static double formatDouble2(int keepnum, double d) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(keepnum, RoundingMode.UP);
        return bg.doubleValue();
    }

    public final static String getStringDefult(String targt, String defult) {
        return isNull(targt) ? defult : targt;
    }

    /*****
     * String 是否空
     * ******/
    public final static boolean isNull(String value) {
        return value == null || value.equals("");
    }

    /**
     * MD5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 实现截图,返回保存路径
     *
     * @param activity
     * @param isopen   保存后是否打开图片
     * @return
     */
    public static void saveScreemShots(Activity activity, boolean isopen) {
        //1.构建Bitmap
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Bitmap bitmap;

        //2.获取截屏
        View decorview = activity.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);
        bitmap = decorview.getDrawingCache();

        //3.创建路径
        String statue = Environment.getExternalStorageState();
        File dir = null;
        if (statue.equals(Environment.MEDIA_MOUNTED)) {
            dir = new File(Environment.getExternalStorageDirectory() + "/DCIM/Screemshots");
        } else {
            dir = new File(Environment.getDownloadCacheDirectory() + "/DCIM/Screemshots");
        }
        if (!dir.exists()) {
            dir.mkdir();
        }
        String savePath = dir.getPath();

        //4.保存bitmap
        try {
            File path = new File(savePath);
            String filePath = savePath + "/" + System.currentTimeMillis() + ".png";
            File file = new File(filePath);
            if (!path.exists()) {
                path.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            if (null != fos) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
                T.showShort(activity, "截图已保存.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isopen) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri = Uri.fromFile(new File(savePath));
            intent.setDataAndType(uri, "image/*");
            activity.startActivity(intent);
        }
    }

    /**
     * 当前软键盘若隐藏，则打开；当前软键盘若打开，则隐藏.
     *
     * @param context
     */
    public static void hideShowKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 由时间戳转换得到时间,精确到秒
     *
     * @param timeStamp
     * @param format
     * @return
     */
    public static String timeStamp2String(String timeStamp, String format) {
        String sd = "";
        format = (isNull(format) ? "yyyy-MM-dd HH:mm" : format);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            if (timeStamp.length() < 13) {
                sd = sdf.format(new Date(Long.parseLong(timeStamp) * 1000));
            } else {
                sd = sdf.format(new Date(Long.parseLong(timeStamp)));
            }
        } catch (Exception e) {

        }
        return sd;
    }

    /**
     * Date类型转换得到时间,精确到秒
     *
     * @param date
     * @param format
     * @return
     */
    public static String date2String(Date date, String format) {
        String sd = "";
        format = (isNull(format) ? "yyyy-MM-dd HH:mm" : format);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sd = sdf.format(date);
        } catch (Exception e) {

        }
        return sd;
    }

    /**
     * 将HTML实体转为正常HTML
     */
    public static String htmle2String(String unicode) {
        if (!ToolUtils.isNull(unicode)) {
//            return unicode.replaceAll("&#60;","<").replaceAll("&#62;",">").replaceAll("&#47;","/");
            return StringEscapeUtils.unescapeHtml4(unicode);
        } else {
            return "";
        }
    }

    /**
     * 复制TextView文本
     *
     * @param context
     * @param aimTextView
     */
    public static void copyTextView(Context context, TextView aimTextView) {
        ClipboardManager clipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", aimTextView.getText().toString());
        clipboard.setPrimaryClip(clip);
        T.showShort(context, "复制成功");
    }

    /**
     * 获取登录令牌
     *
     * @param context
     * @param flag
     */
    public static FToken getToken(Context context, boolean flag) throws NeedLoginException {
        FToken token = (FToken) ACache.get(context).getAsObject(ACEConstant.ACE_FTOKEN_KEY);
        if (token == null || isNull(token.token)) {
            if (flag) {
//                context.startActivity(new Intent(context, LoginAct.class));
            }
            throw new NeedLoginException();
        }
        return token;
    }

    /**
     * 查看是否登录
     *
     * @param context
     * @return
     */
    public static boolean checkLogin(Context context, boolean flag) throws NeedLoginException {
        FToken token = (FToken) ACache.get(context).getAsObject(ACEConstant.ACE_FTOKEN_KEY);
        if (token == null || isNull(token.token)) {
            if (flag) {
                context.startActivity(new Intent(context, UserLoginAct.class));
            }
            throw new NeedLoginException();
        }
        return true;
    }

    /**
     * 登录成功后保存的Token
     *
     * @param context
     * @param token
     */
    public static void saveToken(Context context, FToken token) {
        ACache.get(context).put(ACEConstant.ACE_FTOKEN_KEY, token);
    }

    /**
     * 退出登录
     *
     * @param context
     */
    public static void logout(Context context) {
        ACache.get(context).remove(ACEConstant.ACE_USERINFO);
        ACache.get(context).remove(ACEConstant.ACE_FTOKEN_KEY);
        ACache.get(context).remove(ACEConstant.CURRENCY);
        ACache.get(context).remove(ACEConstant.ACE_USERINFO_USERID);
    }

    /**
     * 保存登录用户信息
     *
     * @param context
     * @param entity
     * @return
     */
    public static boolean saveUserInfo(Context context, FUserInfoEntity entity) {
        try {
            ACache.get(context).put(ACEConstant.ACE_USERINFO, entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取用户信息，若不存在则返回null
     *
     * @param context
     * @return
     */
    public static FUserInfoEntity getUserInfo(Context context) {
        try {
            return (FUserInfoEntity) ACache.get(context).getAsObject(ACEConstant.ACE_USERINFO);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 复制String
     *
     * @param context
     * @param text
     */
    public static void copyStringText(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", text);
        clipboard.setPrimaryClip(clip);
        T.showShort(context, "复制成功");
    }

    /**
     * android状态栏一体化(沉浸式模式)
     *
     * @param activity
     */
    public static void topBarImmerse(Activity activity) {
        Window window = activity.getWindow();
        if (21 <= Build.VERSION.SDK_INT) {
//设置透明状态栏,这样才能让 ContentView 向上
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//设置状态栏颜色
            window.setStatusBarColor(Color.TRANSPARENT);

            ViewGroup mContentView = activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }
        } else if (21 > Build.VERSION.SDK_INT && 19 <= Build.VERSION.SDK_INT) {
            ViewGroup mContentView = activity.findViewById(Window.ID_ANDROID_CONTENT);

//首先使 ChildView 不预留空间
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }

            //最顶部的状态栏
            int statusBarHeight1 = -1;
            //获取status_bar_height资源的ID
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight1 = activity.getResources().getDimensionPixelSize(resourceId);
            }

            int statusBarHeight = statusBarHeight1;
//需要设置这个 flag 才能设置状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//避免多次调用该方法时,多次移除了 View
            if (mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height ==
                    statusBarHeight) {
                //移除假的 View.
                mContentView.removeView(mChildView);
                mChildView = mContentView.getChildAt(0);
            }
            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                //清除 ChildView 的 marginTop 属性
                if (lp != null && lp.topMargin >= statusBarHeight) {
                    lp.topMargin -= statusBarHeight;
                    mChildView.setLayoutParams(lp);
                }
            }
        }
    }

    public static void doNetErroMsg(Context context, Throwable e, boolean isToast) {
        doNetErroMsg(context, e, isToast, true);
    }

    public static void doNetErroMsg(Context context, Throwable e, boolean isToast, boolean isControl) {
        try {
            if (isToast) {
                T.showLong(context, e.getMessage());
            }
            if (e instanceof NetException) {
                if (isControl) {
                    int code = ((NetException) e).netCode;
                    switch (code) {
                        case NET_CODE.C_403:
                            ToolUtils.logout(context);
                            break;
                        default:
                            break;
                    }
                }
            } else {
            }

        } catch (Exception e1) {

        }
    }


    /**
     * 获取缓存的语言
     *
     * @param context
     * @return
     */
    public static String getAppLanguage(Context context) {
        String language_id = ACache.get(context).getAsString(ACEConstant.CURRENTLANGUAGE_ID);
        if (ToolUtils.isNull(language_id)) {
            language_id = ACEConstant.LANGUAGE_SIMPLIFIED_CHINESE;
            ACache.get(context).put(ACEConstant.CURRENTLANGUAGE_ID, language_id);
        }
        return language_id;
    }


    /**
     * 获取缓存的语言
     *
     * @return
     */
    public static String getAppLanguageApi() {
        try {
            String language_id = ACache.get(MAppliaction.getApp()).getAsString(ACEConstant.CURRENTLANGUAGE_ID);
            if (ToolUtils.isNull(language_id)) {
                return "zh_cn";
            }
            switch (language_id) {
                case ACEConstant.LANGUAGE_SIMPLIFIED_CHINESE:
                default:
                    return "zh_cn";
                case ACEConstant.LANGUAGE_TRADITIONAL_CHINESE:
                    return "zh_tw";
                case ACEConstant.LANGUAGE_ENGLISH:
                    return "en";
            }
        } catch (Exception e) {
            return "zh_cn";
        }
    }

    public static byte[] File2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static File getApplicationCacheDir() {
        return MAppliaction.getApp().takePhotoCacheDir;
    }

    public static void openFile(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void setMarketUpDownTvColor(MarketListEntity entity, TextView textView) {

        try {
            Double.valueOf(entity.getChange());
        } catch (Exception e) {
            textView.setTextColor(0xff656565);//空值
            return;
        }

        if (ToolUtils.String2Double(entity.getChange()) >= 0) {
            textView.setTextColor(0xff01cb8f);//跌
        } else {
            textView.setTextColor(0xffff5b4b);//涨
        }
    }

    public static void setMarketUpDownTvColor(String change, TextView textView) {

        try {
            Double.valueOf(change);
        } catch (Exception e) {
            textView.setTextColor(0xff656565);//空值
            return;
        }

        if (ToolUtils.String2Double(change) >= 0) {
            textView.setTextColor(0xff01cb8f);//跌
        } else {
            textView.setTextColor(0xffff5b4b);//涨
        }
    }

    public static int getUpDownColor(Context context, int type) {
        if (type == 0) {
            return Color.parseColor("#00c882");
//            return Color.parseColor("#01cb8f");
        } else {
            return Color.parseColor("#e86e42");
//            return Color.parseColor("#ff5b4b");
        }
    }


//    public static void setTradeUpDownTvColor(TradeListEntity.Trade entity, TextView textView) {
//
//        try {
//             Double.valueOf(entity.getChange());
//        } catch (Exception e) {
//            textView.setTextColor(0xffff5b4b);//涨
//            return ;
//        }
//
//        if (ToolUtils.String2Double(entity.getChange()) >= 0) {
//            textView.setTextColor(0xffff5b4b);//涨
//        } else {
//            textView.setTextColor(0xff01cb8f);//跌
//        }
//    }


    public static CustomSelectDialog showDialog(Context context, CustomSelectDialog.SelectDialogListener listener,
                                                List<String> names) {
        CustomSelectDialog dialog = new CustomSelectDialog(((Activity) context),
                R.style.transparentFrameWindowStyle, listener, names);
        dialog.setItemColor(R.color.colorAccent, R.color.colorPrimary);
        //判断activity是否finish
        if (!((Activity) context).isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


    /**
     * encodeBase64File:(将文件转成base64 字符串). <br/>
     *
     * @param path 文件路径
     * @return
     * @throws Exception
     * @author guhaizhou@126.com
     * @since JDK 1.6
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }

    public static String getCurrency(Context context) {
        String currency = ACache.get(context).getAsString(ACEConstant.CURRENCY);
        if (TextUtils.isEmpty(currency)) {
            currency = "CNY";
        }
        return currency;
    }

    public static String putCurrency(Context context, String currency) {
        ACache.get(context).put(ACEConstant.CURRENCY, currency);
        return currency;
    }

    public static String getSocketNet(Context context, String defaultValue) {
        String socket = ACache.get(context).getAsString(ACEConstant.SOCKET_NET);
        if (TextUtils.isEmpty(socket)) {
            socket = defaultValue;
            return socket;
        }
        return "wss://" + socket;
    }

    public static String getSocketChat(Context context, String defaultValue) {
        String socket = ACache.get(context).getAsString(ACEConstant.SOCKET_Chat);
        if (TextUtils.isEmpty(socket)) {
            socket = defaultValue;
            return socket;
        }
        return "wss://" + socket;
    }

    public static String plusOneAtLast(String value, int position) {
        StringBuilder builder = new StringBuilder(value);
        if (position == -1) {
            builder.insert(0, "1");
            return builder.toString();
        }
        String v = String.valueOf(value.charAt(position));
        if (v.equals(".")) {
            return plusOneAtLast(value, position - 1);
        }
        int i = Integer.parseInt(v);
        if (i >= 9) {
            builder.replace(position, position + 1, "0");
            return plusOneAtLast(builder.toString(), position - 1);
        } else {
            builder.replace(position, position + 1, String.valueOf(i + 1));
            return builder.toString();
        }
    }

    public static String subtractOneAtLast(String value, int position) {
        StringBuilder builder = new StringBuilder(value);
        if (position == -1) {
            return "0";
        }
        String v = String.valueOf(value.charAt(position));
        if (v.equals(".")) {
            return subtractOneAtLast(value, position - 1);
        }
        int i = Integer.parseInt(v);
        if (i <= 0) {
            builder.replace(position, position + 1, "9");
            return subtractOneAtLast(builder.toString(), position - 1);
        } else {
            builder.replace(position, position + 1, String.valueOf(i - 1));
            return builder.toString();
        }
    }

    /**
     * 默认XRecyclerView样式
     */
    public static XRecyclerView DXRecyclerView(XRecyclerView xRecyclerView, Activity activity) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //改变刷新的样式
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //改变加载的样式
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.LineSpinFadeLoader);
        //更换刷新箭头
        xRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        xRecyclerView.setLayoutManager(layoutManager);
        return xRecyclerView;
    }

    public static void setButtone(final View animView, EditText[] editTexts) {
        animView.setTag(editTexts);
        for (EditText editText : editTexts) {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!ToolUtils.isNull(s.toString())) {
                        boolean f = true;
                        EditText[] ets = (EditText[]) animView.getTag();
                        for (EditText editText : ets) {
                            if (ToolUtils.isNull(editText.getText().toString()) && editText.isShown()) {
                                f = false;
                                break;
                            }
                        }
                        if (f) {
                            animView.setSelected(true);
                        } else {
                            animView.setSelected(false);
                        }
                    } else {
                        animView.setSelected(false);
                    }
                }
            });

        }

    }
    /***
     * 启动K线
     * @param context
     * @param entity
     */
    public static void startKLineAct(Context context, MarketListEntity entity) {
        TradeChat3Act.startAct(context, entity);
    }

    public static TradeChat3Act getKLineActType(Context context) throws NullPointerException {
        return (TradeChat3Act) context;
    }

}
