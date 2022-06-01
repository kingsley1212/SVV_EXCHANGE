package com.svv.jys.code.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.svv.jys.R;


/**
 * Glide特点
 * 使用简单
 * 可配置度高，自适应程度高
 * 支持常见图片格式 Jpg png gif webp
 * 支持多种数据源  网络、本地、资源、Assets 等
 * 高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
 * 生命周期集成   根据Activity/Fragment生命周期自动管理请求
 * 高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力
 * 这里默认支持Context，Glide支持Context,Activity,Fragment，FragmentActivity
 * Created by lzj on 2017/6/7.
 */
public class GlideUtils {

    static RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.ic_loaderror).error(R.mipmap
            .ic_loaderror).fallback(R.mipmap.ic_loaderror);
    static RequestOptions userOptions = new RequestOptions().placeholder(R.mipmap.ic_portrait).error(R.mipmap
            .ic_portrait).fallback(R.mipmap.ic_portrait);

    //    //设置加载中以及加载失败图片
    public static void loadImageDefult(Context mContext, String path, ImageView mImageView) {
        if (path != null && path.contains("https:")) {
            path = path.replace("https:", "http:");
        }
        Glide.with(mContext)
                .load(path)
                .apply(requestOptions)
                .into(mImageView);
    }

    public static void loadUserImageDefult(Context mContext, String path, ImageView mImageView) {
        if (path != null && path.contains("https:")) {
            path = path.replace("https:", "http:");
        }
        Glide.with(mContext)
                .load(path)
                .apply(userOptions)
                .into(mImageView);
    }

    public static void loadUserLogo(Context mContext, String path, ImageView mImageView) {
        if (path != null && path.contains("https:")) {
            path = path.replace("https:", "http:");
        }
        Glide.with(mContext)
                .load(path)
                .apply(userOptions)
                .into(mImageView);
    }

//    //设置加载中以及加载失败图片
//    public static void loadImageDefult(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).placeholder(R.mipmap.ic_loading).dontAnimate().error(R.mipmap.ic_loaderror)
//                .into(mImageView);
//    }
//
//    public static void loadUserImageDefult(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).placeholder(R.mipmap.ic_user_default).dontAnimate().error(R.mipmap
//                .ic_user_default).dontAnimate()
//                .into(mImageView);
//    }
//
//
//    //加载单图自适应
//    public static void loadSingleImgFit(Context mContext, final int aimWidth, String path, final ImageView
//            mImageView) {
//        Glide.with(mContext).load(path).asBitmap().into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                if (mImageView != null) {
//                    FrameLayout.LayoutParams lp;
//                    int h = bitmap.getHeight();
//                    int w = bitmap.getWidth();
//                    int sh, sw;
//                    float k;
//                    k = ((float) aimWidth / (float) w);
//
//                    sh = (int) (h * k);
//                    sw = (int) (aimWidth);
//                    lp = new FrameLayout.LayoutParams(sw, sh);
//                    mImageView.setLayoutParams(lp);
//                    mImageView.setImageBitmap(bitmap);
//                }
//            }
//        });
//    }
//
//    /**
//     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
//     */
//    public static void loadIntoUseFitWidth(final Context context, final String imageUrl, final ImageView imageView) {
//        Glide.with(context)
//                .load(imageUrl)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean
//                            isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable>
//                            target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        if (imageView == null) {
//                            return false;
//                        }
//                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
//                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                        }
//                        float imageW = (float) resource.getIntrinsicWidth();
//                        float imageH = (float) resource.getIntrinsicHeight();
//                        if (imageH > imageW) {//长图
//                            float scale = imageH / imageW;
//                            int screenW = ResourceUtils.getWindowsWidth((Activity) context);
//                            int vw = (int) (screenW * 2 / 5);
//                            int vh = (int) (vw * scale);
//                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
//                            params.height = vh;
//                            params.width = vw;
//                            imageView.setLayoutParams(params);
//                        } else {//宽图
//                            float scale = imageH / imageW;
//                            int screenW = ResourceUtils.getWindowsWidth((Activity) context);
//                            int vw = (int) (screenW * 3 / 5);
//                            int vh = (int) (vw * scale);
//                            ViewGroup.LayoutParams params = imageView.getLayoutParams();
//                            params.height = vh;
//                            params.width = vw;
//                            imageView.setLayoutParams(params);
//                        }
//
//                        return false;
//                    }
//                })
//                .placeholder(R.mipmap.ic_loading)
//                .error(R.mipmap.ic_loaderror)
//                .into(imageView);
//    }
//
//    /**
//     * 设置加载Drawable中的图片
//     *
//     * @param mContext
//     * @param path
//     * @param mImageView
//     */
//    public static void loadLocalImageDefult(Context mContext, int path, ImageView mImageView) {
//        Glide.with(mContext).load(path).placeholder(R.mipmap.ic_loading).error(R.mipmap.ic_loaderror)
//                .into(mImageView);
//    }
//
//    //设置加载中以及加载失败图片
//    public static void loadImageViewDefultLoding(Context mContext, String path, ImageView mImageView, int
//            lodingImage, int errorImageView) {
//        Glide.with(mContext).load(path).placeholder(lodingImage).error(errorImageView).into(mImageView);
//    }
//
//    //默认加载
//    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).into(mImageView);
//    }
//
//    //加载指定大小
//    public static void loadImageViewSize(Context mContext, String path, int width, int height, ImageView mImageView) {
//        Glide.with(mContext).load(path).override(width, height).into(mImageView);
//    }
//
//    //设置加载中以及加载失败图片
//    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView, int lodingImage, int
//            errorImageView) {
//        Glide.with(mContext).load(path).placeholder(lodingImage).error(errorImageView).into(mImageView);
//    }
//
//
//    //设置加载中以及加载失败图片并且指定大小
//    public static void loadImageViewLodingSize(Context mContext, String path, int width, int height, ImageView
//            mImageView, int lodingImage, int errorImageView) {
//        Glide.with(mContext).load(path).override(width, height).placeholder(lodingImage).error(errorImageView).into
//                (mImageView);
//    }
//
//    //设置跳过内存缓存
//    public static void loadImageViewCache(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).skipMemoryCache(true).into(mImageView);
//    }
//
//    //设置下载优先级
//    public static void loadImageViewPriority(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).priority(Priority.NORMAL).into(mImageView);
//    }
//
//    /**
//     * 策略解说：
//     * <p>
//     * all:缓存源资源和转换后的资源
//     * <p>
//     * none:不作任何磁盘缓存
//     * <p>
//     * source:缓存源资源
//     * <p>
//     * result：缓存转换后的资源
//     */
//
//    //设置缓存策略
//    public static void loadImageViewDiskCache(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.ALL).into(mImageView);
//    }
//
//    /**
//     * api也提供了几个常用的动画：比如crossFade()
//     */
//
//    //设置加载动画
//    public static void loadImageViewAnim(Context mContext, String path, int anim, ImageView mImageView) {
//        Glide.with(mContext).load(path).animate(anim).into(mImageView);
//    }
//
//    /**
//     * 会先加载缩略图
//     */
//
//    //设置缩略图支持
//    public static void loadImageViewThumbnail(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).thumbnail(0.1f).into(mImageView);
//    }
//
//    /**
//     * api提供了比如：centerCrop()、fitCenter()等
//     */
//
//    //设置动态转换
//    public static void loadImageViewCrop(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).centerCrop().into(mImageView);
//    }
//
//    //设置动态GIF加载方式
//    public static void loadImageViewDynamicGif(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).asGif().into(mImageView);
//    }
//
//    //设置静态GIF加载方式
//    public static void loadImageViewStaticGif(Context mContext, String path, ImageView mImageView) {
//        Glide.with(mContext).load(path).asBitmap().into(mImageView);
//    }
//
//    //设置监听的用处 可以用于监控请求发生错误来源，以及图片来源 是内存还是磁盘
//
//    //设置监听请求接口
//    public static void loadImageViewListener(Context mContext, String path, ImageView mImageView,
//                                             RequestListener<String, GlideDrawable> requstlistener) {
//        Glide.with(mContext).load(path).listener(requstlistener).into(mImageView);
//    }
//
//    //项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排
//
//    //设置要加载的内容
//    public static void loadImageViewContent(Context mContext, String path, SimpleTarget<GlideDrawable> simpleTarget) {
//        Glide.with(mContext).load(path).centerCrop().into(simpleTarget);
//    }
//
//    //清理磁盘缓存
//    public static void GuideClearDiskCache(Context mContext) {
//        //理磁盘缓存 需要在子线程中执行
//        Glide.get(mContext).clearDiskCache();
//    }
//
//    //清理内存缓存
//    public static void GuideClearMemory(Context mContext) {
//        //清理内存缓存  可以在UI主线程中进行
//        Glide.get(mContext).clearMemory();
//    }

}
