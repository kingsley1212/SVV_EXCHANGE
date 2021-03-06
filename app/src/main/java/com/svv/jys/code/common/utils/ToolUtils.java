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
     * ??????????????????
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
     * ??????
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
     * ????????????int
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
     * String ???long
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
     * String ???double
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
     * ??????
     *
     * @param v1    ??????
     * @param v2    ?????????
     * @param scale ?????????????????????
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
        //???????????????????????????
        nf.setMaximumFractionDigits(8);
        // ?????????????????????
        nf.setGroupingUsed(false);
        //????????????
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
     * ???????????????0
     *
     * @param s
     * @return
     */
    public static String trimZero(String s) {
        if (s.indexOf(".") > 0) {        // ???????????????0
            s = s.replaceAll("0+?$", "");        // ??????????????????.?????????
            s = s.replaceAll("[.]$", "");
        }
        return s;
    }

    /***
     * Cny?????????????????????
     * @param number
     * @return
     */
    public static String CnyFormat(String number) {
        return ToolUtils.doublePoint(ToolUtils.String2Double(number));
    }

    /***
     * ?????????????????????????????????
     * @param number
     * @return
     */
    public static String SzzcFormat(String number) {
        return ToolUtils.trimZero(ToolUtils.doublePoint8(ToolUtils.String2Double(number)));
    }


    /***
     * ???????????????????????????????????????
     * @param number
     * @return
     */
    public static String SzNumFormat(String number) {
        return ToolUtils.trimZero(ToolUtils.doublePoint8(ToolUtils.String2Double(number)));
    }

//    /**
//     * String ???double
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
     * String ???float
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
     * double???????????????????????????
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
     * double???????????????????????????
     *
     * @return
     */
    public static String doublePoint8(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.00000000");
        return df.format(double1);
    }

    /**
     * double???????????????????????????
     *
     * @return
     */
    public static String doublePoint6(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0.000000");
        return df.format(double1);
    }

    /**
     * double?????????????????????
     *
     * @return
     */
    public static String doublePointToInt(Double double1) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#######################0");
        return df.format(double1);
    }

    /**
     * PopupWindow?????????
     * ?????????????????????????????????????????????????????????
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
        // ??????????????????????????????????????????????????????RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(keepnum, RoundingMode.UP);
        return bg.doubleValue();
    }

    public final static String getStringDefult(String targt, String defult) {
        return isNull(targt) ? defult : targt;
    }

    /*****
     * String ?????????
     * ******/
    public final static boolean isNull(String value) {
        return value == null || value.equals("");
    }

    /**
     * MD5??????
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
     * ????????????,??????????????????
     *
     * @param activity
     * @param isopen   ???????????????????????????
     * @return
     */
    public static void saveScreemShots(Activity activity, boolean isopen) {
        //1.??????Bitmap
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Bitmap bitmap;

        //2.????????????
        View decorview = activity.getWindow().getDecorView();
        decorview.setDrawingCacheEnabled(true);
        bitmap = decorview.getDrawingCache();

        //3.????????????
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

        //4.??????bitmap
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
                T.showShort(activity, "???????????????.");
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
     * ???????????????????????????????????????????????????????????????????????????.
     *
     * @param context
     */
    public static void hideShowKeyBoard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * ??????????????????????????????,????????????
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
     * Date????????????????????????,????????????
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
     * ???HTML??????????????????HTML
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
     * ??????TextView??????
     *
     * @param context
     * @param aimTextView
     */
    public static void copyTextView(Context context, TextView aimTextView) {
        ClipboardManager clipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", aimTextView.getText().toString());
        clipboard.setPrimaryClip(clip);
        T.showShort(context, "????????????");
    }

    /**
     * ??????????????????
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
     * ??????????????????
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
     * ????????????????????????Token
     *
     * @param context
     * @param token
     */
    public static void saveToken(Context context, FToken token) {
        ACache.get(context).put(ACEConstant.ACE_FTOKEN_KEY, token);
    }

    /**
     * ????????????
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
     * ????????????????????????
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
     * ??????????????????????????????????????????null
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
     * ??????String
     *
     * @param context
     * @param text
     */
    public static void copyStringText(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager)
                context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("simple text", text);
        clipboard.setPrimaryClip(clip);
        T.showShort(context, "????????????");
    }

    /**
     * android??????????????????(???????????????)
     *
     * @param activity
     */
    public static void topBarImmerse(Activity activity) {
        Window window = activity.getWindow();
        if (21 <= Build.VERSION.SDK_INT) {
//?????????????????????,??????????????? ContentView ??????
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//?????????????????? flag ???????????? setStatusBarColor ????????????????????????
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//?????????????????????
            window.setStatusBarColor(Color.TRANSPARENT);

            ViewGroup mContentView = activity.findViewById(Window.ID_ANDROID_CONTENT);
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                //?????????????????? ContentView ??? FitsSystemWindows, ???????????? ContentView ??????????????? View . ?????????????????? View ????????????.
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }
        } else if (21 > Build.VERSION.SDK_INT && 19 <= Build.VERSION.SDK_INT) {
            ViewGroup mContentView = activity.findViewById(Window.ID_ANDROID_CONTENT);

//????????? ChildView ???????????????
            View mChildView = mContentView.getChildAt(0);
            if (mChildView != null) {
                ViewCompat.setFitsSystemWindows(mChildView, false);
            }

            //?????????????????????
            int statusBarHeight1 = -1;
            //??????status_bar_height?????????ID
            int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //????????????ID????????????????????????
                statusBarHeight1 = activity.getResources().getDimensionPixelSize(resourceId);
            }

            int statusBarHeight = statusBarHeight1;
//?????????????????? flag ?????????????????????
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//??????????????????????????????,??????????????? View
            if (mChildView != null && mChildView.getLayoutParams() != null && mChildView.getLayoutParams().height ==
                    statusBarHeight) {
                //???????????? View.
                mContentView.removeView(mChildView);
                mChildView = mContentView.getChildAt(0);
            }
            if (mChildView != null) {
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView.getLayoutParams();
                //?????? ChildView ??? marginTop ??????
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
     * ?????????????????????
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
     * ?????????????????????
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
            textView.setTextColor(0xff656565);//??????
            return;
        }

        if (ToolUtils.String2Double(entity.getChange()) >= 0) {
            textView.setTextColor(0xff01cb8f);//???
        } else {
            textView.setTextColor(0xffff5b4b);//???
        }
    }

    public static void setMarketUpDownTvColor(String change, TextView textView) {

        try {
            Double.valueOf(change);
        } catch (Exception e) {
            textView.setTextColor(0xff656565);//??????
            return;
        }

        if (ToolUtils.String2Double(change) >= 0) {
            textView.setTextColor(0xff01cb8f);//???
        } else {
            textView.setTextColor(0xffff5b4b);//???
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
//            textView.setTextColor(0xffff5b4b);//???
//            return ;
//        }
//
//        if (ToolUtils.String2Double(entity.getChange()) >= 0) {
//            textView.setTextColor(0xffff5b4b);//???
//        } else {
//            textView.setTextColor(0xff01cb8f);//???
//        }
//    }


    public static CustomSelectDialog showDialog(Context context, CustomSelectDialog.SelectDialogListener listener,
                                                List<String> names) {
        CustomSelectDialog dialog = new CustomSelectDialog(((Activity) context),
                R.style.transparentFrameWindowStyle, listener, names);
        dialog.setItemColor(R.color.colorAccent, R.color.colorPrimary);
        //??????activity??????finish
        if (!((Activity) context).isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


    /**
     * encodeBase64File:(???????????????base64 ?????????). <br/>
     *
     * @param path ????????????
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
     * ??????XRecyclerView??????
     */
    public static XRecyclerView DXRecyclerView(XRecyclerView xRecyclerView, Activity activity) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //?????????????????????
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        //?????????????????????
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.LineSpinFadeLoader);
        //??????????????????
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
     * ??????K???
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
