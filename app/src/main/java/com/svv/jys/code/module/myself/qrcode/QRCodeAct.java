package com.svv.jys.code.module.myself.qrcode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.WriterException;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.entity.FUserInfoEntity;
import com.svv.jys.code.common.utils.GlideUtils;
import com.svv.jys.code.common.utils.T;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.common.view.camera.CameraRollManager;
import com.svv.jys.code.common.view.popup.PopupDialogView;
import com.svv.jys.code.module.myself.inviterecord.InviteRecordAct;
import com.svv.jys.code.module.myself.qrcode.adapter.ImageAdapter;
import com.svv.jys.code.module.myself.qrcode.model.IQRCodeModel;
import com.svv.jys.code.module.myself.qrcode.presenter.QRCodePresenter;
import com.svv.jys.code.module.myself.qrcode.view.IQRCodeView;
import com.svv.jys.code.module.net.u.NET_URL;
import com.svv.jys.code.module.zxing.encoding.EncodingHandler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class QRCodeAct extends MvpActivity<IQRCodeView, IQRCodeModel, QRCodePresenter> implements IQRCodeView {
    String IMAGE_URL;
    ImageView iv_ercode;
    private TextView tv_lianjie, tv_fuzhi,invite_code_tv,right_tv;
    private FUserInfoEntity fUserInfoEntity;
    private ImageView user_logo;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public QRCodePresenter initPresenter() {
        return new QRCodePresenter();
    }

    @Override
    public void baseInitialization() {
        fUserInfoEntity = (FUserInfoEntity) getIntent().getSerializableExtra("user");
    }

    @Override
    public int setR_Layout() {
        return R.layout.act_qrcode;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.homefragment_invite_friends));
        right_tv = findViewById(R.id.right_tv);
        right_tv.setText(getString(R.string.qrcode_record));
        iv_ercode = findViewById(R.id.iv_ercode);
        tv_lianjie = findViewById(R.id.tv_lianjie);
        tv_fuzhi = findViewById(R.id.tv_fuzhi);
        user_logo = findViewById(R.id.user_logo);
        invite_code_tv = findViewById(R.id.invite_code_tv);
        invite_code_tv.setText(getString(R.string.MyselfFragment_yqCode)+fUserInfoEntity.getInvite_code());
        tv_fuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(lianjie);
                T.showShort(QRCodeAct.this, getString(R.string.assat24));
            }
        });
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ToolUtils.isFastClick(v.getId())){
                    gotoActivity(InviteRecordAct.class);
                }

            }
        });
        if (TextUtils.isEmpty(fUserInfoEntity.getLogo()))
            user_logo.setImageResource(R.mipmap.ic_portrait);
        else
//            GlideUtils.loadUserLogo(getMContext(), NET_URL.getInstance().getUserHeadImgUrl(fUserInfoEntity.getLogo()), user_logo);
            GlideUtils.loadUserLogo(getMContext(), fUserInfoEntity.getLogo(), user_logo);

        String Url = NET_URL.getInstance().getInviteLink(fUserInfoEntity.getInvite_code());
        tv_lianjie.setText(Url);
        setInviteImages(Url);
    }

    String lianjie;

    @Override
    public void setInviteImages(String s) {
        tv_lianjie.setText(s);
        lianjie = s;
        Bitmap mBitmap = null;
        try {
            if (mBitmap == null) {
                mBitmap = EncodingHandler.createQRCode(s, 500);
            }
            iv_ercode.setImageBitmap(mBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        int mSpace;

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.left = mSpace;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.left = 0;
            }

        }

        public SpaceItemDecoration(int space) {
            this.mSpace = space;
        }
    }

    @Override
    public void doBusiness() {

    }

    public void setInviteImages(final List<String> list) {
        ImageAdapter adapter = new ImageAdapter(this, list);
//        rc_InviteImages.setAdapter(adapter);
        adapter.setOnLongItemClickListener(new ImageAdapter.OnRecyclerViewLongItemClickListener() {
            @Override
            public void onLongItemClick(View view, final int position) {
                PopupDialogView view1 = new PopupDialogView(getMContext(), new PopupDialogView.MClickLisnener() {
                    @Override
                    public void leftBtn() {

                    }

                    @Override
                    public void rightBtn() {
                        presenter.showLoading(QRCodeAct.this);
                        loadImage(list.get(position));
                        IMAGE_URL = list.get(position);
                    }
                });
                view1.showPop(view, getString(R.string.qrcode_pic_baocun), getString(R.string.qrcode_tishi), getString(R.string.qrcode_no), getString(R.string.qrcode_pic_baocun));

            }
        });

    }

    private void loadImage(String path) {
        DownImageTask task = new DownImageTask();
        task.execute(path);
    }

    private class DownImageTask extends AsyncTask<String, Long, Bitmap> {
        private long contentLength;

        public DownImageTask() {
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            BufferedInputStream bis = null;
            ByteArrayOutputStream bos = null;
            try {
                bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[512];
                long total = 0;
                int len;
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                this.contentLength = conn.getContentLength();
                bis = new BufferedInputStream(conn.getInputStream());
                while ((len = bis.read(buffer)) != -1) {
                    total += len;
                    bos.write(buffer, 0, len);
                    bos.flush();
                }
                bitmap = BitmapFactory.decodeByteArray(bos.toByteArray(), 0, bos.toByteArray().length);
                saveBitmapToDisk(bos, params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (bos != null) {
                        bos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bitmap;
        }

        private void saveBitmapToDisk(final ByteArrayOutputStream baos, final String url) {
            new Thread() {
                @Override
                public void run() {
                    BufferedOutputStream bos = null;
                    try {
                        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                            return;
                        }
                        byte[] bytes = baos.toByteArray();
                        File fileDir = new File(getApplication().getExternalCacheDir(), "images");
                        if (fileDir == null || !fileDir.isDirectory()) {
                            fileDir.mkdir();
                        }
                        File file = new File(fileDir.getAbsolutePath() + "/" + url.hashCode() + ".png");
                        file.createNewFile();
                        bos = new BufferedOutputStream(new FileOutputStream(file));
                        bos.write(bytes);
                        bos.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (bos != null) {
                            try {
                                bos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }.start();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                File fileDir = new File(getApplication().getExternalCacheDir(), "images");
                File file = new File(fileDir.getAbsolutePath() + "/" + IMAGE_URL.hashCode() + ".png");
                if (file != null && file.length() > 0) {
                    CameraRollManager rollManager = new CameraRollManager(QRCodeAct.this, Uri.parse(file.getAbsolutePath()));
                    rollManager.execute();
                }
                presenter.dismissLoading(QRCodeAct.this);
            }
        }
    }
}
