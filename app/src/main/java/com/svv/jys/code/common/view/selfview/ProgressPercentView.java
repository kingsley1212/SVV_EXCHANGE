package com.svv.jys.code.common.view.selfview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.svv.jys.R;

/**
 * 进度条
 * Created by Administrator on 2018/4/18 0018.
 */

public class ProgressPercentView extends FrameLayout {
    private int progressPercent = 0;
    private RelativeLayout backgroundView, percentView;


    public ProgressPercentView(@NonNull Context context) {
        this(context, null);
    }

    public ProgressPercentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressPercentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        backgroundView = new RelativeLayout(context);
        backgroundView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        percentView = new RelativeLayout(context);

        backgroundView.setBackground(getResources().getDrawable(R.drawable.shape_percent_bg));
        percentView.setBackground(getResources().getDrawable(R.drawable.shape_percent_value_bg));
        addView(backgroundView);
        addView(percentView);

        this.post(new Runnable() {
            @Override
            public void run() {
                if(getWidth()>0){
                    int perc = (int) (getWidth() / 100.0 * progressPercent);
                    ViewGroup.LayoutParams params = percentView.getLayoutParams();
                    params.width = perc;
                    percentView.setLayoutParams(params);
                    postInvalidate();
                }
            }
        });

    }


    /**
     * 范围1--100
     *
     * @param percent
     */
    public void setPercent(int percent) {
        if (percent >= 0 && percent <= 100) {
            progressPercent = percent;
        }
    }

}
