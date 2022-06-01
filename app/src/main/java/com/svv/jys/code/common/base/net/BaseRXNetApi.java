package com.svv.jys.code.common.base.net;


import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.BaseEntity;
import com.svv.jys.code.common.utils.RSAUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.app.MAppliaction;
import com.svv.jys.code.module.net.exception.NeedLoginException;
import com.svv.jys.code.module.net.exception.NetException;
import com.svv.jys.code.module.net.u.NET_CODE;
import com.svv.jys.code.module.net.u.NET_URL;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;


/**
 * Created by lzj on 2017/6/7.
 */

public class BaseRXNetApi {

//    public static final String NETAPI = "e7b457808cd76172248e65607820be4a";
    public static final String NETAPI = "i91oc68jrol1f0kalaet4sa3inq7en27";

    public enum RXExecuteType {GET, POST}


    public static final OkHttpClient createClient() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .writeTimeout(1200, TimeUnit.SECONDS)
                .readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS)
                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory(),SSLSocketClient.getX509TrustManager())
                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .build();
        return okHttpClient;
    }

    /***
     *  * 构建一般post请求Req
     * @param API_METHOD
     * @param baseRequest
     * @return
     */
//    public static final Request doPostReq(String API_METHOD, BaseRequest baseRequest) {
//        String url = NET_URL.getInstance().getUrl(API_METHOD);
//        FormBody.Builder builder = new FormBody.Builder();
//        String sign = "";
//        if (baseRequest != null) {
//            Map<String, String> mapParams = baseRequest.bulitReqMap();
//            if (mapParams != null) {
//                for (String key : mapParams.keySet()) {
//                    if (mapParams.get(key) != null) {
//                        builder.add(key, mapParams.get(key));
//                    }
////                    builder.add(key, mapParams.get(key) == null ? "" : mapParams.get(key));
//                }
//            }
//
//            sign = getMd5Value(getApiStr2(mapParams) + NETAPI);
//        }
//        String userToken = "";
//        try {
//            userToken = ToolUtils.getToken(MAppliaction.getApp(), false).token;
//            if (ToolUtils.isNull(userToken)) {
//                userToken = "";
//            }
//        } catch (NeedLoginException e) {
//            userToken = "";
//        }
//        String language = ToolUtils.getAppLanguageApi();
//        String currency = ToolUtils.getCurrency(MAppliaction.getApp());
//        Request request = new Request.Builder()
//                .url(url)
//                .post(builder.build())
//                .addHeader("Isapp", "true").addHeader("Usertoken", userToken).addHeader("Sign", sign).addHeader
//                        ("Language", language).addHeader("Currency",currency)
//                .addHeader("Dsv6","1").build();
//        return request;
//    }
    public static final Request doPostReq(String API_METHOD, BaseRequest baseRequest) {
        String url = NET_URL.getInstance().getUrl(API_METHOD);
        FormBody.Builder builder = new FormBody.Builder();
        String sign = "";

        String userToken = "", userKey = "";
        try {
            userToken = ToolUtils.getToken(MAppliaction.getApp(), false).token;
            if (ToolUtils.isNull(userToken)) {
                userToken = "";
            }
            userKey = ToolUtils.getToken(MAppliaction.getApp(), false).getKey();
            if (ToolUtils.isNull(userKey)) {
                userKey = NETAPI;
            }
        } catch (NeedLoginException e) {
            userToken = "";
            userKey = NETAPI;
        }

        if (baseRequest == null) {
            baseRequest = new BaseRequest();
        }
        Map<String, String> mapParams = baseRequest.bulitReqMap();
        if (mapParams != null) {
            mapParams.put("t", String.valueOf(new Date().getTime()));
            String value = JSONObject.toJSONString(mapParams);
            try {
                value = RSAUtils.encrypt(value);
            } catch (Exception e) {
                value = "";
                e.printStackTrace();
            }
            builder.add("data", value);
        }

        sign = getMd5Value(getApiStr2(mapParams) + userKey);

         sign = sign + "/"+ getMd5Value(String.valueOf(Long.parseLong(mapParams.get("t"))/1000));

        String language = ToolUtils.getAppLanguageApi();
        String currency = ToolUtils.getCurrency(MAppliaction.getApp());
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .addHeader("Isapp", "true").addHeader("Usertoken", userToken)
                .addHeader("Sign", sign)
                .addHeader("Language", language).addHeader("Currency", currency)
                .addHeader("Dsv6", "1").build();
        return request;
    }
    /**
     * * 构建一般get请求Req
     *
     * @param API_METHOD
     * @param baseRequest
     * @return
     */
//    public static final Request doGetReq(String API_METHOD, BaseRequest baseRequest) {
//        String url = NET_URL.getInstance().getUrl(API_METHOD);
//        String sign = "";
//        if (baseRequest != null) {
//            Map<String, String> mapParams = baseRequest.bulitReqMap();
//            if (mapParams != null) {
//                for (String key : mapParams.keySet()) {
//                    if (mapParams.get(key) != null) {
//                        if (url.indexOf("?") == -1) {
//                            url += ("?" + key + "=" + mapParams.get(key));
//                        } else {
//                            url += ("&" + key + "=" + mapParams.get(key));
//                        }
//                    }
//                }
//            }
//            sign = getMd5Value(getApiStr2(mapParams) + NETAPI);
//        }
//
//        Request.Builder requestBuilder = new Request.Builder().url(url);
//        //可以省略，默认是GET请求
//        requestBuilder.method("GET", null);
//
//        String userToken = "";
//        try {
//            userToken = ToolUtils.getToken(MAppliaction.getApp(), false).token;
//            if (ToolUtils.isNull(userToken)) {
//                userToken = "";
//            }
//        } catch (NeedLoginException e) {
//            userToken = "";
//        }
//        String language = ToolUtils.getAppLanguageApi();
//        String currency = ToolUtils.getCurrency(MAppliaction.getApp());
//        Request request = requestBuilder.addHeader("Isapp", "true").addHeader("Usertoken", userToken).addHeader
//                ("Sign", sign).addHeader
//                ("Language", language).addHeader("Currency",currency)
//                .addHeader("Dsv6","1").build();
//        return request;
//    }
    public static final Request doGetReq(String API_METHOD, BaseRequest baseRequest) {
        String url = NET_URL.getInstance().getUrl(API_METHOD);
        String sign = "";

        String userToken = "", userKey = "";
        try {
            userToken = ToolUtils.getToken(MAppliaction.getApp(), false).token;
            if (ToolUtils.isNull(userToken)) {
                userToken = "";
            }
            userKey = ToolUtils.getToken(MAppliaction.getApp(), false).getKey();
            if (ToolUtils.isNull(userKey)) {
                userKey = NETAPI;
            }
        } catch (NeedLoginException e) {
            userToken = "";
            userKey = NETAPI;
        }
        if (baseRequest == null) {
            baseRequest = new BaseRequest();
        }
        Map<String, String> mapParams = baseRequest.bulitReqMap();

        if (mapParams != null) {
            mapParams.put("t", String.valueOf(new Date().getTime()));
            String value = JSONObject.toJSONString(mapParams);
            try {
                value = RSAUtils.encrypt(value);
            } catch (Exception e) {
                value = "";
                e.printStackTrace();
            }
            url += ("?" + "data" + "=" + value);
        }
        sign = getMd5Value(getApiStr2(mapParams) + userKey);
        sign = sign + "/"+ getMd5Value(String.valueOf(Long.parseLong(mapParams.get("t"))/1000));

        Request.Builder requestBuilder = new Request.Builder().url(url);
        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);

        String language = ToolUtils.getAppLanguageApi();
        String currency = ToolUtils.getCurrency(MAppliaction.getApp());
        Request request = requestBuilder.addHeader("Isapp", "true").addHeader("Usertoken", userToken)
                .addHeader("Sign", sign)
                .addHeader("Language", language).addHeader("Currency", currency)
                .addHeader("Dsv6", "1").build();
        return request;
    }
    /***
     *  * 构建包含媒体post请求Req
     * @param API_METHOD
     * @param baseRequest
     * @return
     */
    public static final Request doMutliPostReq(String API_METHOD, BaseRequest baseRequest) {
        String url = NET_URL.getInstance().getUrl(API_METHOD);
        String sign = "";
        MultipartBody.Builder mutipartBuild = new MultipartBody.Builder().setType(MultipartBody.FORM);
        String userToken = "", userKey = "";
        try {
            userToken = ToolUtils.getToken(MAppliaction.getApp(), false).token;
            if (ToolUtils.isNull(userToken)) {
                userToken = "";
            }
            userKey = ToolUtils.getToken(MAppliaction.getApp(), false).getKey();
            if (ToolUtils.isNull(userKey)) {
                userKey = NETAPI;
            }
        } catch (NeedLoginException e) {
            userToken = "";
            userKey = NETAPI;
        }

        if (baseRequest == null) {
            baseRequest = new BaseRequest();
        }
        Map<String, String> mapParams = baseRequest.bulitReqMap();
        if (mapParams != null) {
            mapParams.put("t", String.valueOf(new Date().getTime()));
            String value = JSONObject.toJSONString(mapParams);
            try {
                value = RSAUtils.encrypt(value);
            } catch (Exception e) {
                value = "";
                e.printStackTrace();
            }
            mutipartBuild.addFormDataPart("data", value);
            sign = getMd5Value(getApiStr2(mapParams) + userKey);
            sign = sign + "/"+ getMd5Value(String.valueOf(Long.parseLong(mapParams.get("t"))/1000));
            if (baseRequest.baseMulitRequests != null) {
                for (BaseMulitRequest r : baseRequest.baseMulitRequests) {
                    if (r.file == null || !r.file.exists()) {
                        continue;
                    }
                    mutipartBuild.addFormDataPart(r.key, r.file.getName(), RequestBody.create(MediaType.parse(r
                            .contentType), r.file));
                }
            }
        }
        //构建请求体
        RequestBody requestBody = mutipartBuild.build();
        Request.Builder RequestBuilder = new Request.Builder();
        RequestBuilder.url(url);// 添加URL地址
        RequestBuilder.post(requestBody);


        String language = ToolUtils.getAppLanguageApi();
        String currency = ToolUtils.getCurrency(MAppliaction.getApp());

        Request request = RequestBuilder/*.addHeader("User-Agent", "Android")*/.addHeader("Usertoken", userToken)
                .addHeader("Isapp", "true")
                .addHeader("Dsv6", "1")
                .addHeader("Sign", sign)
                .addHeader("Language", language)
                .addHeader("Currency", currency).build();
        return request;
    }
    /***
     *  * 构建包含媒体post请求Req
     * @param API_METHOD
     * @param baseRequest
     * @return
     */
//    public static final Request doMutliPostReq(String API_METHOD, BaseRequest baseRequest) {
//        String url = NET_URL.getInstance().getUrl(API_METHOD);
//        String sign = "";
//        MultipartBody.Builder mutipartBuild = new MultipartBody.Builder().setType(MultipartBody.FORM);
//        if (baseRequest != null) {
//            Map<String, String> mapParams = baseRequest.bulitReqMap();
//
//            if (mapParams != null) {
//                for (String key : mapParams.keySet()) {
//                    mutipartBuild.addFormDataPart(key, mapParams.get(key) == null ? "" : mapParams.get(key));
//                }
//                sign = getMd5Value(getApiStr2(mapParams) + NETAPI);
//            }
//
//            if (baseRequest.baseMulitRequests != null) {
//                for (BaseMulitRequest r : baseRequest.baseMulitRequests) {
//                    if (r.file == null || !r.file.exists()) {
//                        continue;
//                    }
//                    mutipartBuild.addFormDataPart(r.key, r.file.getName(), RequestBody.create(MediaType.parse(r
//                            .contentType), r.file));
//                }
//            }
//
//        }
//
//        //构建请求体
//        RequestBody requestBody = mutipartBuild.build();
//        Request.Builder RequestBuilder = new Request.Builder();
//        RequestBuilder.url(url);// 添加URL地址
//        RequestBuilder.post(requestBody);
//
//        String userToken = "";
//        try {
//            userToken = ToolUtils.getToken(MAppliaction.getApp(), false).token;
//            if (ToolUtils.isNull(userToken)) {
//                userToken = "";
//            }
//        } catch (NeedLoginException e) {
//            userToken = "";
//        }
//        String language = ToolUtils.getAppLanguageApi();
//        String currency = ToolUtils.getCurrency(MAppliaction.getApp());
//
//        Request request = RequestBuilder.addHeader("User-Agent", "Android").addHeader("Usertoken", userToken)
//                .addHeader("Isapp", "true").addHeader
//                        ("Sign", sign).addHeader
//                        ("Language", language).addHeader("Currency",currency).build();
//        return request;
//    }

    /**
     * 上传日志file型文件
     *
     * @param API_METHOD
     * @param req
     */
    public static final void doExecuteErrorLog(final String API_METHOD, final BaseRequest req) {
        OkHttpClient client = createClient();
        Request request = doMutliPostReq(API_METHOD, req);
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bodyStr = response.body().string();
            }
        });
    }

    /**
     * @param API_METHOD
     * @param type
     * @param req
     * @param clazz
     * @param <R>
     * @return
     */
    public synchronized static final <R extends BaseResponse> Observable<R> rx_doExecuteQuick(final String API_METHOD, final RXExecuteType type, final BaseRequest req, final Class<R> clazz) {

        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(final Subscriber<? super R> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onStart();
                    OkHttpClient client = createClient();
                    Request request = null;
                    switch (type) {
                        case GET:
                            request = doGetReq(API_METHOD, req);
                            break;
                        case POST:
                            request = doPostReq(API_METHOD, req);
                            break;
                    }
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String bodyStr = response.body().string();
                            R baseResponse = null;
                            try {
                                baseResponse = clazz.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                                subscriber.onError(new NetException(baseResponse.code, ToolUtils.isNull(baseResponse
                                        .msg) ? MAppliaction.getApp().getString(com.svv.jys.R
                                        .string.fanmang) : baseResponse.msg));
                                return;
                            }
                            baseResponse.fromJSON(bodyStr);
                            switch (baseResponse.code) {

                                case NET_CODE.C_200:
                                    subscriber.onNext(baseResponse);
                                    subscriber.onCompleted();
                                    break;

                                default:
                                    subscriber.onError(new NetException(baseResponse.code, ToolUtils.isNull(baseResponse
                                            .msg) ? MAppliaction.getApp().getString(com.svv.jys.R
                                            .string.fanmang) : baseResponse.msg));
                                    break;

                            }
                        }
                    });
                }
            }
        });
    }


    /**
     * @param API_METHOD
     * @param type
     * @param req
     * @return
     */
    public static final Observable<? extends String> rx_doExecuteQuick_str(final String API_METHOD, final RXExecuteType
            type, final BaseRequest req) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onStart();
                    OkHttpClient client = createClient();
                    Request request = null;
                    switch (type) {
                        case GET:
                            request = doGetReq(API_METHOD, req);
                            break;
                        case POST:
                            request = doPostReq(API_METHOD, req);
                            break;
                    }
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String bodyStr = response.body().string();
                            subscriber.onNext(bodyStr);
                            subscriber.onCompleted();
                        }
                    });
                }
            }
        });

    }


    public synchronized static final <T extends BaseEntity> Observable<MPageResponse<T>> rx_doExecuteByPage(final String
                                                                                                                    API_METHOD, final
                                                                                                            RXExecuteType
                                                                                                                    type, final BaseRequest
                                                                                                                    req) {
        return Observable.create(new Observable.OnSubscribe<MPageResponse<T>>() {
            @Override
            public void call(final Subscriber<? super MPageResponse<T>> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onStart();
                    OkHttpClient client = createClient();
                    Request request = null;
                    switch (type) {
                        case GET:
                            request = doGetReq(API_METHOD, req);
                            break;
                        case POST:
                            request = doPostReq(API_METHOD, req);
                            break;
                    }
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String bodyStr = response.body().string();
                            MPageResponse<T> baseResponse = null;
                            try {
                                baseResponse = new MPageResponse<T>();
                            } catch (Exception e) {
                                e.printStackTrace();
                                subscriber.onError(new NetException(baseResponse.code, ToolUtils.isNull(baseResponse
                                        .msg) ? MAppliaction.getApp().getString(com.svv.jys.R
                                        .string.fanmang) : baseResponse.msg));
                                return;
                            }
                            baseResponse.fromJSON(bodyStr);
                            switch (baseResponse.code) {

                                case NET_CODE.C_200:
                                    subscriber.onNext(baseResponse);
                                    subscriber.onCompleted();
                                    break;

                                default:
                                    subscriber.onError(new NetException(baseResponse.code, ToolUtils.isNull
                                            (baseResponse
                                                    .msg) ? MAppliaction.getApp().getString(com.svv.jys.R
                                            .string.fanmang) : baseResponse.msg));
                                    break;

                            }
                        }
                    });
                }
            }
        });
    }

//        {
//            @Override
//            public void call(final Subscriber<? super R> subscriber) {
//                if (!subscriber.isUnsubscribed()) {
//                    subscriber.onStart();
//                    OkHttpClient client = createClient();
//                    Request request = null;
//                    switch (type) {
//                        case GET:
//                            request = doGetReq(API_METHOD, req);
//                            break;
//                        case POST:
//                            request = doPostReq(API_METHOD, req);
//                            break;
//                    }
//                    Call call = client.newCall(request);
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onFailure(Call call, IOException e) {
//                            subscriber.onError(e);
//                        }
//
//                        @Override
//                        public void onResponse(Call call, Response response) throws IOException {
//                            final String bodyStr = response.body().string();
//                            R baseResponse = null;
//                            try {
//                                baseResponse = clazz.newInstance();
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                subscriber.onError(new NetException(baseResponse.code, ToolUtils.isNull(baseResponse
//                                        .msg) ? "系统繁忙" : baseResponse.msg));
//                                return;
//                            }
//                            baseResponse.fromJSON(bodyStr);
//                            switch (baseResponse.code) {
//
//                                case NET_CODE.C_200:
//                                    subscriber.onNext(baseResponse);
//                                    subscriber.onCompleted();
//                                    break;
//
//                                default:
//                                    subscriber.onError(new NetException(baseResponse.code, ToolUtils.isNull
// (baseResponse
//                                            .msg) ? "系统繁忙" : baseResponse.msg));
//                                    break;
//
//                            }
//                        }
//                    });
//                }
//            }
//        });


    /**
     * 包含多媒体上传
     *
     * @param API_METHOD
     * @param req
     * @return
     */
    public static final <R extends BaseResponse> Observable<R> rx_doExecuteMedia(
            final String API_METHOD,
            final BaseRequest req, final Class<R> clazz) {
        return Observable.create(new Observable.OnSubscribe<R>() {
            @Override
            public void call(final Subscriber<? super R> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onStart();
                    OkHttpClient client = createClient();
                    Request request = doMutliPostReq(API_METHOD, req);
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final String bodyStr = response.body().string();
                            R baseResponse = null;
                            try {
                                baseResponse = clazz.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                                subscriber.onError(new NetException(baseResponse.code, ToolUtils.isNull(baseResponse
                                        .msg) ? MAppliaction.getApp().getString(com.svv.jys.R
                                        .string.fanmang) : baseResponse.msg));
                                return;
                            }
                            baseResponse.fromJSON(bodyStr);
                            switch (baseResponse.code) {
                                case NET_CODE.C_200:
                                    subscriber.onNext(baseResponse);
                                    subscriber.onCompleted();
                                    break;

                                default:
                                    subscriber.onError(new NetException(baseResponse.code, ToolUtils.isNull(baseResponse
                                            .msg) ? MAppliaction.getApp().getString(com.svv.jys.R
                                            .string.fanmang) : baseResponse.msg));
                                    break;

                            }
                        }
                    });

                }
            }
        });
    }


    /**
     * 映射得到排序的成员变量字符串
     *
     * @param hashmap
     * @return
     */
    public static String getApiStr2(Map hashmap) {
        if (hashmap != null) {
            StringBuilder result = new StringBuilder();

            Map<String, Object> map = new TreeMap<>(new Comparator<String>() {

                @Override
                public int compare(String lhs, String rhs) {
                    return lhs.compareTo(rhs);
                }

            });
            map.putAll(hashmap);

            for (TreeMap.Entry<String, Object> entry : map.entrySet()) {
                if (entry.getValue() != null) {
//                    if (result.length() > 0)
//                        result.append("&");
//
//                    result.append(entry.getKey());
//                    result.append("=");
                    result.append(entry.getValue());
                }
            }
            return result.toString();
        } else {
            return "";
        }
    }

    // md5加密，获得32位小写字符串
    public static String getMd5Value(String sSecret) {
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(sSecret.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
