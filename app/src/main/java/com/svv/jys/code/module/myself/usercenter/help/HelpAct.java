package com.svv.jys.code.module.myself.usercenter.help;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.utils.GetPictureUtils;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.usercenter.help.model.IHelpModel;
import com.svv.jys.code.module.myself.usercenter.help.presenter.HelpPresenter;
import com.svv.jys.code.module.myself.usercenter.help.view.IHelpView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lzj on 2018/9/20.
 */

public class HelpAct extends MvpActivity<IHelpView, IHelpModel, HelpPresenter> implements IHelpView {

    public static final String URL_KEY = "url_key";

    public static void startAct(Context context, String url) {
        Intent intent = new Intent(context, HelpAct.class);
        intent.putExtra(URL_KEY, url);
        context.startActivity(intent);
    }

    public String token;
    public String picUrl;

    private WebView searchWebview;

    public static final int FILECHOOSER_RESULTCODE = 120;
    public ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> mUploadCallbackAboveL;
    public WebChromeClient wcci = new WebChromeClient() {

//


        @Override
        public void onProgressChanged(WebView arg0, int arg1) {
            super.onProgressChanged(arg0, arg1);
        }

        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            mUploadMessage = uploadMsg;
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("image/*");
//            startActivityForResult(Intent.createChooser(intent, "完成操作需要使用"),
//                    WebCommonAct.FILECHOOSER_RESULTCODE);
            try {
                Intent getAlbum = new Intent(Intent.ACTION_PICK);
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum,
                        FILECHOOSER_RESULTCODE);
            } catch (Exception e) {

            }
        }

        public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
            mUploadMessage = uploadMsg;
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("image/*");
//            startActivityForResult(Intent.createChooser(intent, "完成操作需要使用"),
//                    WebCommonAct.FILECHOOSER_RESULTCODE);
            try {
                Intent getAlbum = new Intent(Intent.ACTION_PICK);
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum,
                        FILECHOOSER_RESULTCODE);
            } catch (Exception e) {

            }
        }

        public void openFileChooser(ValueCallback<Uri> uploadMsg,
                                    String acceptType, String capture) {
            // TODO Auto-generated method stub
            // super.openFileChooser(arg0, arg1, arg2);
            mUploadMessage = uploadMsg;
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("image/*");
//            startActivityForResult(Intent.createChooser(intent, "完成操作需要使用"),
//                    WebCommonAct.FILECHOOSER_RESULTCODE);
            try {
                Intent getAlbum = new Intent(Intent.ACTION_PICK);
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum,
                        FILECHOOSER_RESULTCODE);
            } catch (Exception e) {

            }
        }

        // For Android 5.0+
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams
                fileChooserParams) {
//            String url = webView.getUrl().toString();
            mUploadCallbackAboveL = filePathCallback;
//            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
//            i.addCategory(Intent.CATEGORY_OPENABLE);
//            i.setType("image/*");
//            WebCommonAct.this.startActivityForResult(
//                    Intent.createChooser(i, "File Browser"),
//                    FILECHOOSER_RESULTCODE);
            try {
                Intent getAlbum = new Intent(Intent.ACTION_PICK);
                getAlbum.setType("image/*");
                startActivityForResult(getAlbum,
                        FILECHOOSER_RESULTCODE);
            } catch (Exception e) {

            }

            return true;
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }

    };

    @TargetApi(20)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent data) {
        if (requestCode != FILECHOOSER_RESULTCODE
                || mUploadCallbackAboveL == null) {
            return;
        }
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
            } else {
                String dataString = data.getDataString();
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        mUploadCallbackAboveL.onReceiveValue(results);
        mUploadCallbackAboveL = null;
        return;
    }

    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FILECHOOSER_RESULTCODE:
                // mUploadMessage = wcci.getmUploadMessage();
                if (null == mUploadMessage && null == mUploadCallbackAboveL) return;
                Uri result = data == null || resultCode != RESULT_OK ? null : Uri.fromFile(GetPictureUtils
                        .getPhotoFromIntent(data, HelpAct.this));
                if (result == null) {
                    return;
                }
                if (mUploadCallbackAboveL != null) {
                    onActivityResultAboveL(requestCode, resultCode, data);
                } else if (mUploadMessage != null) {
                    mUploadMessage.onReceiveValue(result);
                    mUploadMessage = null;
                }
                break;
        }
    }


    @Override
    public void baseInitialization() {
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_help;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        searchWebview = (WebView) findViewById(R.id.searchWebview);
    }

    @Override
    public void doBusiness() {
        presenter.getUrl(getIntent());
    }


    public void hideView() {
    }

    @Override
    public void loadUrl(final String url) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);// （这个对宿主没什么影响，建议声明）
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
                        | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        searchWebview.clearCache(true);
        searchWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        WebSettings webSettings = searchWebview.getSettings();
        // 添加自定义标签
        String ua = webSettings.getUserAgentString();
        webSettings.setUserAgentString(ua + "; DMSCAPP");
        webSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(false);
// 设置出现缩放工具
        webSettings.setBuiltInZoomControls(false);
//扩大比例的缩放
        webSettings.setUseWideViewPort(true);
//自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
//        searchWebview.addJavascriptInterface(new DemoJavaScriptInterface(), "demo");
        searchWebview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String kurl) {
                hideView();
                final Map<String, String> map = new HashMap<>();
                map.put("Isapp", "true");
                String language = ToolUtils.getAppLanguageApi();
                map.put("Language", language);
                searchWebview.loadUrl(kurl, map);
                return true;
            }


            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                hideView();
                final Map<String, String> map = new HashMap<>();
                map.put("Isapp", "true");
                String language = ToolUtils.getAppLanguageApi();
                map.put("Language", language);
                String kurl = request.getUrl().toString();
                searchWebview.loadUrl(kurl, map);
//                return super.shouldOverrideUrlLoading(view, request);
                return true;
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        searchWebview.setWebChromeClient(wcci);
        final Map<String, String> map = new HashMap<>();
        map.put("Isapp", "true");
        String language = ToolUtils.getAppLanguageApi();
        map.put("Language", language);
        searchWebview.loadUrl(url, map);
        hideView();
//        setImageClick();
    }

    /**
     * 同步一下cookie
     */
    public void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        try {
            if (!ToolUtils.isNull(token)) {
                cookieManager.setCookie(url, "key=" + token);
            }
            cookieManager.setCookie(url, "isapp=1");
            //cookies是在HttpClient中获得的cookie
            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.getInstance().sync();
            } else {
                CookieManager.getInstance().flush();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public HelpPresenter initPresenter() {
        return new HelpPresenter();
    }


    final class DemoJavaScriptInterface {
        public DemoJavaScriptInterface() {
        }

    }

    @Override
    public Context getMContext() {
        return this;
    }
//    public void setImageClick() {
//        // 长按点击事件
//        searchWebview.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                final WebView.HitTestResult hitTestResult = searchWebview.getHitTestResult();
//                // 如果是图片类型或者是带有图片链接的类型
//                if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE ||
//                        hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
//                    // 弹出保存图片的对话框
//                    AlertDialog.Builder builder = new AlertDialog.Builder(WebCommonAct.this);
//                    builder.setTitle("提示");
//                    builder.setMessage("保存图片到本地");
//                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            picUrl = hitTestResult.getExtra();//获取图片链接
//                            //保存图片到相册
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    url2bitmap(picUrl);
//                                }
//                            }).start();
//                        }
//                    });
//                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        // 自动dismiss
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                        }
//                    });
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//                    return true;
//                }
//                return false;//保持长按可以复制文字
//            }
//        });
//    }
//
//    public void url2bitmap(String url) {
//        Bitmap bm = null;
//        try {
//            URL iconUrl = new URL(url);
//            URLConnection conn = iconUrl.openConnection();
//            HttpURLConnection http = (HttpURLConnection) conn;
//            int length = http.getContentLength();
//            conn.connect();
//            // 获得图像的字符流
//            InputStream is = conn.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is, length);
//            bm = BitmapFactory.decodeStream(bis);
//            bis.close();
//            is.close();
//            if (bm != null) {
//                save2Album(bm);
//            }
//        } catch (Exception e) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(WebCommonAct.this, "保存失败", Toast.LENGTH_SHORT).show();
//                }
//            });
//            e.printStackTrace();
//        }
//    }
//
//    private void save2Album(Bitmap bitmap) {
//        File appDir = new File(Environment.getExternalStorageDirectory(), "相册名称");
//        if (!appDir.exists()) appDir.mkdir();
//        String[] str = picUrl.split("/");
//        String fileName = str[str.length - 1];
//        File file = new File(appDir, fileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//            fos.flush();
//            fos.close();
//            onSaveSuccess(file);
//        } catch (IOException e) {
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(WebCommonAct.this, "保存失败", Toast.LENGTH_SHORT).show();
//                }
//            });
//            e.printStackTrace();
//        }
//    }
//
//    private void onSaveSuccess(final File file) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
//                Toast.makeText(WebCommonAct.this, "保存成功", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
