package com.svv.jys.code.common.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.svv.jys.R;
import com.svv.jys.code.common.db.nativecache.ACache;


/**
 * 短信验证码的倒计时
 * Created by Administrator on 2018/4/9 0009.
 */

public class CountDownMsgUtils {

    private final long TIMECODE_MAX;//只赋值一次
    private String ACacheKey;
    private Context context;
    /**
     * 请求验证码的按钮
     */
    private TextView requestBtn;
    private ICountDownPostCode countDownPostCode;
    private Handler mcodeCountHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            long nowTime = System.currentTimeMillis();
            long cacheTime = ToolUtils.String2Long(ACache.get(context)
                    .getAsString(ACacheKey));
            final long c = (nowTime - cacheTime) / 1000;
            if (cacheTime == 0) {
                return;
            }
            if (cacheTime != 0 && c > TIMECODE_MAX) {
                ACache.get(context).put(ACacheKey, "0");
                requestBtn.setText(context.getResources().getString(R.string.login_get_checkcode));//获取验证码
            } else {
                mcodeCountHandler.sendMessageDelayed(new Message(), 1000);
//                requestBtn.setText(String.format(context.getString(R.string.registeract_surplus), (TIMECODE_MAX - c) + "s"));//剩余%1$s
                if ((TIMECODE_MAX - c)==0){
                    requestBtn.setText(context.getResources().getString(R.string.login_get_checkcode));
                }else {
                    requestBtn.setText(/*context.getResources().getString(R.string.login_get_shegnyu) +*/ (TIMECODE_MAX - c) + "s");//剩余%1$s
                }
            }
        }
    };

    /**
     * @param ACacheKey
     * @param context
     * @param TIMECODE_MAX
     * @param requestBtn   要绑定控件
     */
    public CountDownMsgUtils(String ACacheKey, Context context, long TIMECODE_MAX, TextView requestBtn) {
        this.ACacheKey = ACacheKey;
        this.context = context;
        this.TIMECODE_MAX = TIMECODE_MAX;
        this.requestBtn = requestBtn;
        if (this.requestBtn == null) {
            Toast.makeText(context, "requestBtn为空", Toast.LENGTH_SHORT).show();
            return;
        }
        mcodeCountHandler.sendMessage(new Message());
    }

    /**
     * 放在请求验证码的点击事件中，经内部判断后才允许向服务器请求验证码
     *
     * @param countDownPostCode
     */
    public void requestCheckCode(String checkCode, ICountDownPostCode countDownPostCode) {
        if (!ToolUtils.isNull(checkCode)) {
            this.countDownPostCode = countDownPostCode;
            long nowTime = System.currentTimeMillis();
            long cacheTime = ToolUtils.String2Long(ACache.get(context).getAsString(ACacheKey));
            final long c = (nowTime - cacheTime) / 1000;
            if (cacheTime == 0 || c > TIMECODE_MAX) {
                countDownPostCode.allowToRequestCode();
            } else {
                T.showShort(context, context.getString(R.string.msg_code));//短信已发送,请稍后点击
            }
        } else {
            T.showShort(context, context.getString(R.string.msg_code_null));//手机号码不正确或者手机号为空
        }

    }

    public void requestSuccess() {
        ACache.get(context).put(ACacheKey, System.currentTimeMillis() + "");
        mcodeCountHandler.sendMessage(new Message());
    }

    public interface ICountDownPostCode {
        /**
         * 允许请求验证码按钮点击
         */
        void allowToRequestCode();

    }
}


