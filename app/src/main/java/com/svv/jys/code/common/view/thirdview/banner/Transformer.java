package com.svv.jys.code.common.view.thirdview.banner;

import android.support.v4.view.ViewPager.PageTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.AccordionTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.BackgroundToForegroundTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.CubeInTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.CubeOutTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.DefaultTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.DepthPageTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.FlipHorizontalTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.FlipVerticalTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.ForegroundToBackgroundTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.RotateDownTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.RotateUpTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.ScaleInOutTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.ScaleRightTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.ScaleTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.StackTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.TabletTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.ZoomInTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.ZoomOutSlideTransformer;
import com.svv.jys.code.common.view.thirdview.banner.transformer.ZoomOutTranformer;

public class Transformer {

    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Scale = ScaleTransformer.class;
    public static Class<? extends PageTransformer> ScaleRight = ScaleRightTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;

}
