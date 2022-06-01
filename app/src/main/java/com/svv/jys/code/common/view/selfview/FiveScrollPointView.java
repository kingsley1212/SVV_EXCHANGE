package com.svv.jys.code.common.view.selfview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.utils.ResourceUtils;

/**
 * 五点滑动条
 * Created by Administrator on 2018/5/8 0008.
 */

public class FiveScrollPointView extends LinearLayout {

    private ImageView fiveStepImg1, fiveStepImg2, fiveStepImg3, fiveStepImg4, fiveStepImg5;
    private View fiveStepImg1_ly, fiveStepImg2_ly, fiveStepImg3_ly, fiveStepImg4_ly, fiveStepImg5_ly;
    private Context context;
    private View changesBar;
    private TextView fivePointLeftText, fivePointRightText;
    private int position;
    private MPositionListener positionListener;

    private View fiveScrollTouch_ly;

    public FiveScrollPointView(Context context) {
        this(context, null);
    }

    public FiveScrollPointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FiveScrollPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_fivescrollpoint, this);
        this.context = context;
        fiveStepImg1 = findViewById(R.id.fiveStepImg1);
        fiveStepImg2 = findViewById(R.id.fiveStepImg2);
        fiveStepImg3 = findViewById(R.id.fiveStepImg3);
        fiveStepImg4 = findViewById(R.id.fiveStepImg4);
        fiveStepImg5 = findViewById(R.id.fiveStepImg5);
        fiveStepImg1_ly = findViewById(R.id.fiveStepImg1_ly);
        fiveStepImg2_ly = findViewById(R.id.fiveStepImg2_ly);
        fiveStepImg3_ly = findViewById(R.id.fiveStepImg3_ly);
        fiveStepImg4_ly = findViewById(R.id.fiveStepImg4_ly);
        fiveStepImg5_ly = findViewById(R.id.fiveStepImg5_ly);
        changesBar = findViewById(R.id.changesBar);

        fivePointLeftText = findViewById(R.id.fivePointLeftText);
        fivePointRightText = findViewById(R.id.fivePointRightText);

        fiveScrollTouch_ly = findViewById(R.id.fiveScrollTouch_ly);
        setImageState(1, 2, 2, 2, 2);
        position = 1;

        fiveScrollTouch_ly.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float touchX = event.getX();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.i("ACTION", "ACTION_DOWN");
                        controlBar(touchX);
                        return true;
                    case MotionEvent.ACTION_UP:
                        Log.i("ACTION", "ACTION_UP");
                        controlBar(touchX);
                        if (touchX >= fiveStepImg1_ly.getLeft() && touchX <= fiveStepImg1_ly.getRight()) {
                            position = 1;
                            setImageState(1, 2, 2, 2, 2);
                        } else if (touchX >= fiveStepImg2_ly.getLeft() && touchX <= fiveStepImg2_ly.getRight()) {
                            position = 2;
                            setImageState(2, 1, 2, 2, 2);
                        } else if (touchX >= fiveStepImg3_ly.getLeft() && touchX <= fiveStepImg3_ly.getRight()) {
                            position = 3;
                            setImageState(2, 2, 1, 2, 2);
                        } else if (touchX >= fiveStepImg4_ly.getLeft() && touchX <= fiveStepImg4_ly.getRight()) {
                            position = 4;
                            setImageState(2, 2, 2, 1, 2);
                        } else if (touchX >= fiveStepImg5_ly.getLeft() && touchX <= fiveStepImg5_ly.getRight()) {
                            position = 5;
                            setImageState(2, 2, 2, 2, 1);
                        }
                        if (positionListener != null) {
                            positionListener.getPosition(position);
                        }
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        Log.i("ACTION", "ACTION_MOVE");
                        controlBar(touchX);
                        if (touchX >= fiveStepImg1_ly.getLeft() && touchX <= fiveStepImg1_ly.getRight()) {
                            position = 1;
                            setImageState(1, 2, 2, 2, 2);
                        } else if (touchX >= fiveStepImg2_ly.getLeft() && touchX <= fiveStepImg2_ly.getRight()) {
                            position = 2;
                            setImageState(2, 1, 2, 2, 2);
                        } else if (touchX >= fiveStepImg3_ly.getLeft() && touchX <= fiveStepImg3_ly.getRight()) {
                            position = 3;
                            setImageState(2, 2, 1, 2, 2);
                        } else if (touchX >= fiveStepImg4_ly.getLeft() && touchX <= fiveStepImg4_ly.getRight()) {
                            position = 4;
                            setImageState(2, 2, 2, 1, 2);
                        } else if (touchX >= fiveStepImg5_ly.getLeft() && touchX <= fiveStepImg5_ly.getRight()) {
                            position = 5;
                            setImageState(2, 2, 2, 2, 1);
                        }
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public void setMPositionListener(MPositionListener positionListener) {
        this.positionListener = positionListener;
    }

    public void setLeftText(String content) {
        fivePointLeftText.setText(content);
    }

    public void setRightText(String content) {
        fivePointRightText.setText(content);
    }

    /**
     * 控制状态条长度
     *
     * @param xPosition
     */
    private void controlBar(float xPosition) {
        changesBar.getLayoutParams().width = (int) xPosition;
        changesBar.requestLayout();
    }

    /**
     * 设置五个图片的状态
     *
     * @param img1 1--2
     * @param img2 1--2
     * @param img3 1--2
     * @param img4 1--2
     * @param img5 1--2
     */
    private void setImageState(int img1, int img2, int img3, int img4, int img5) {
        switch (img1) {
            case 1:
                fiveStepImg1.setImageResource(R.mipmap.ic_fivestep_state_1);
                fiveStepImg1.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 26), ResourceUtils.dp2px(context, 26)));
                break;
            case 2:
                fiveStepImg1.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg1.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
            default:
                fiveStepImg1.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg1.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
        }
        switch (img2) {
            case 1:
                fiveStepImg2.setImageResource(R.mipmap.ic_fivestep_state_1);
                fiveStepImg2.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 26), ResourceUtils.dp2px(context, 26)));
                break;
            case 2:
                fiveStepImg2.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg2.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
            default:
                fiveStepImg2.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg2.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
        }
        switch (img3) {
            case 1:
                fiveStepImg3.setImageResource(R.mipmap.ic_fivestep_state_1);
                fiveStepImg3.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 26), ResourceUtils.dp2px(context, 26)));
                break;
            case 2:
                fiveStepImg3.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg3.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
            default:
                fiveStepImg3.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg3.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
        }
        switch (img4) {
            case 1:
                fiveStepImg4.setImageResource(R.mipmap.ic_fivestep_state_1);
                fiveStepImg4.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 26), ResourceUtils.dp2px(context, 26)));
                break;
            case 2:
                fiveStepImg4.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg4.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
            default:
                fiveStepImg4.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg4.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
        }
        switch (img5) {
            case 1:
                fiveStepImg5.setImageResource(R.mipmap.ic_fivestep_state_1);
                fiveStepImg5.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 26), ResourceUtils.dp2px(context, 26)));
                break;
            case 2:
                fiveStepImg5.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg5.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
            default:
                fiveStepImg5.setImageResource(R.mipmap.ic_fivestep_state_3);
                fiveStepImg5.setLayoutParams(new RelativeLayout.LayoutParams(ResourceUtils.dp2px(context, 17), ResourceUtils.dp2px(context, 17)));
                break;
        }

    }


    public interface MPositionListener {
        void getPosition(int position);
    }

}
