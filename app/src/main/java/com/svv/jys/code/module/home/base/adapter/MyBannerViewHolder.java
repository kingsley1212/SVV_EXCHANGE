package com.svv.jys.code.module.home.base.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.svv.jys.code.common.entity.IndexEntity;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.thirdview.banner.holder.BannerViewHolder;
import com.svv.jys.code.module.home.article.ArticleAct;

public class MyBannerViewHolder implements BannerViewHolder<IndexEntity.BannerBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public void onBind(final Context context, final int position, final IndexEntity.BannerBean data) {

//        RoundedCorners roundedCorners= new RoundedCorners(20);
////通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
//        RequestOptions options=new RequestOptions();
//        options.transforms(new CenterCrop(), new RoundedCorners(20));
//        Glide.with(context).load(data.getPic()).apply(options) .into(imageView);
        GlideUtils.loadImageDefult(context,data.getPic(),imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())) {
                    String url = data.getLink();
                    if (!ToolUtils.isNull(url)) {
                        Intent bundle = new Intent(context, ArticleAct.class);
                        bundle.putExtra("id", url);
                        context.startActivity(bundle);
                    }
                }
            }
        });
    }
}
