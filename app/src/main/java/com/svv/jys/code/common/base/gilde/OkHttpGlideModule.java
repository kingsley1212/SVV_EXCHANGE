//package com.flb.jys.code.common.base.gilde;
//
//import android.content.Context;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.GlideBuilder;
//import com.bumptech.glide.load.model.GlideUrl;
//import com.bumptech.glide.module.GlideModule;
//import com.flb.jys.code.common.base.net.SSLSocketClient;
//
//import java.io.InputStream;
//
//import okhttp3.OkHttpClient;
//
///**
// * Created by js on 2018/8/9.
// */
//
//public class OkHttpGlideModule implements GlideModule {
//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
//    }
//
//    @Override
//    public void registerComponents(Context context, Glide glide) {
////        OkHttpClient mHttpClient = new OkHttpClient().newBuilder()
////                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(),SSLSocketClient.getX509TrustManager())
////                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
////                .build();
////        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(mHttpClient));
//        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(UnsafeOkHttpClient.getUnsafeOkHttpClient()));
//    }
//}
