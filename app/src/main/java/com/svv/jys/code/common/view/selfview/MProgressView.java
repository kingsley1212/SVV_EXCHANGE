package com.svv.jys.code.common.view.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.svv.jys.code.common.utils.ResourceUtils;
import com.svv.jys.code.common.utils.ToolUtils;

/**
 * Created by lzj on 2018/5/10.
 */

public class MProgressView extends View {

    private Context context;

    private final int COUNT = 5;
    public MBall[] balls = new MBall[COUNT];
    public static final int MGREEN = 0xff01cb8f;
    public static final int MORANGE = 0xffff5b4b;
    private int MGAY = 0xffe7ebee;

    private int MUSE_C = MGREEN;

    private int selfw, selfh, itemw;
    private int ox, oy;//控件x ，y
    private Paint mPaint;
    private int min_x, max_x;

    private float u_ratio = 0;//占领比例
    private float u_x = 0;//滚点x坐标
    private float u_y = 0;//滚点x坐标
    private float u_r = 30;

    private float ball_b_r = 10;
    private float ball_s_r = 5;
    private float line_width = 2;
    private MPositionListener positionListener;

    public MProgressView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public MProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public MProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    public void setMPositionListener(MPositionListener positionListener) {
        this.positionListener = positionListener;
    }


    public int getMUSE_C() {
        return MUSE_C;
    }

    public void setMUSE_C(int MUSE_C) {
        this.MUSE_C = MUSE_C;
    }

    //初始化paint，没什么可说的。
    private void initView() {
        this.u_r = ResourceUtils.getPixelsFromDp(context,12);
        this.ball_b_r = ResourceUtils.getPixelsFromDp(context,10);
        this.ball_s_r = ResourceUtils.getPixelsFromDp(context,5);
        this.line_width = ResourceUtils.getPixelsFromDp(context,2);

        mPaint = new Paint();
        mPaint.setColor(MGAY);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        selfw = getMeasuredWidth();
        selfh = getMeasuredHeight();
        itemw = (selfw - 2 * (int) u_r) / (COUNT - 1);

    }

    public void setReset(){
        u_x=0;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.translate(ox, oy);
//        canvas.drawCircle(0, 0, 50, mPaint);

        for (int i = 0; i < COUNT; i++) {
            MBall ball = balls[i];
            if (ball == null) {
                ball = new MBall();
                ball.b_r  = (int)ball_b_r;
                ball.s_r  = (int)ball_s_r;
                ball.b_x = (itemw * i + (int) u_r);
                ball.b_y = selfh / 2;
                balls[i] = ball;
            }
            if (ball.b_x > u_x) {
                drawUnCrile(canvas, ball);
            } else {
                drawSelectCrile(canvas, ball);
            }
            if (i == 0) {
                min_x = ball.b_x;
                if (u_x == 0) {
                    u_x = min_x;
                }
//                u_x = min_x;
            }
            if (i == COUNT - 1) {
                max_x = ball.b_x;
            }
        }

        for (int i = 0; i < balls.length - 1; i++) {
            MBall sball = balls[i];
            MBall eball = balls[i + 1];
            drawALine(canvas, sball.b_x + sball.b_r + 10, sball.b_y, eball.b_x - eball.b_r - 10, eball.b_y);
        }

        drawUserPoint(canvas, (int) u_x, selfh / 2);

//        postInvalidate();
    }

    public void drawUserPoint(Canvas canvas, int item_x, int item_y) {
        mPaint.setStrokeWidth(2f);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(item_x, item_y, u_r, mPaint);
        mPaint.setColor(MGAY);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(item_x, item_y, u_r - 2, mPaint);
        mPaint.setColor(MUSE_C);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(item_x, item_y, u_r / 2, mPaint);
    }

    public void drawUnCrile(Canvas canvas, MBall ball) {
        mPaint.setStrokeWidth(1f);
        mPaint.setColor(MGAY);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(ball.b_x, ball.b_y, ball.b_r, mPaint);
    }

    public void drawSelectCrile(Canvas canvas, MBall ball) {
        mPaint.setStrokeWidth(1f);
        mPaint.setColor(MUSE_C);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(ball.b_x, ball.b_y, ball.b_r, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(ball.b_x, ball.b_y, ball.s_r, mPaint);
    }

    public void drawALine(Canvas canvas, int s_x, int s_y, int e_x, int e_y) {
        if (e_x < u_x) {
            mPaint.setColor(MUSE_C);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(line_width);
            canvas.drawLine(s_x, s_y, e_x, e_y, mPaint);
        } else if (s_x > u_x) {
            mPaint.setColor(MGAY);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(line_width);
            canvas.drawLine(s_x, s_y, e_x, e_y, mPaint);
        } else {
            mPaint.setColor(MUSE_C);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(line_width);
            canvas.drawLine(s_x, s_y, u_x, selfh / 2, mPaint);

            mPaint.setColor(MGAY);
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setStrokeWidth(line_width);
            canvas.drawLine(u_x, selfh / 2, e_x, e_y, mPaint);
        }
    }

    public void setOXY(int ox, int oy) {
        this.ox = ox;
        this.oy = oy;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                // 如果正在拖拽中，那么不拦截它的事件，直接return false；
                u_x = event.getX();
                if (u_x <= min_x) {
                    u_x = min_x;
                }
                if (u_x >= max_x) {
                    u_x = max_x;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (positionListener == null) {
                    return true;
                }
                u_ratio = (u_x - min_x) / (max_x - min_x);
                Log.i("---------------   ", u_ratio + "");
                positionListener.selectMultiple(ToolUtils.String2Float(ToolUtils.doublePoint(ToolUtils.String2Double
                        (u_ratio + ""))));
                /*if (u_ratio <= 1 && positionListener != null) {
                    float multiple = (int)(u_ratio*10)/10.0f;
                    if ((int) (COUNT * multiple) == (COUNT * multiple)) {
                        positionListener.selectPosition((int) (COUNT * multiple));
                    } else {
                        positionListener.selectPosition((int) (COUNT * multiple) + 1);
                    }
                }*/
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        postInvalidate();
        return true;
    }

    public void performScroll() {
        if (positionListener == null) {
            return;
        }
        positionListener.selectMultiple(ToolUtils.String2Float(ToolUtils.doublePoint(ToolUtils.String2Double
                (u_ratio + ""))));
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public interface MPositionListener {
        void selectMultiple(float position);
    }

    class MBall {
        int b_x, b_y;
        int b_r = /*20*/26; //ResourceUtils.dipToPX();
        int s_r = /*10*/18;
//        int b_r = /*20*/ ResourceUtils.dip2px2(context,14);
//        int s_r = /*10*/ResourceUtils.dip2px2(context,12);
    }
}
