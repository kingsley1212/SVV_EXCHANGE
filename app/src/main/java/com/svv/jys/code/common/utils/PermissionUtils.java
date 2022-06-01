package com.svv.jys.code.common.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.svv.jys.R;
import com.svv.jys.code.common.constant.IETConstant;


/**
 * Created by lzj on 2017/9/9.
 */

public class PermissionUtils {

    // 判断权限集合
    public static boolean lacksPermissions(Context mContext, String... permissions) {
        for (String permission : permissions) {
            if (lacksPermission(mContext, permission)) {
                return true;
            }
        }
        return false;
    }

    // 判断是否缺少权限
    private static boolean lacksPermission(Context mContext, String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_DENIED;
    }

    /**
     * 打开扫描二维码
     * @param mContext
     * @return
     */
    public static boolean check2dCameraPermission(Context mContext) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            //6.0以上需要WRITE_EXTERNAL_STORAGE和CAMERA两个权限才能打开相机
            int cameraPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);
            int writeExternalPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE);
            if (writeExternalPermission != PackageManager.PERMISSION_GRANTED || cameraPermission != PackageManager
                    .PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mContext,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        0);
                T.showLong(mContext, mContext.getString(R.string.permission_1));
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * Android 版本低于6.0时，或权限都满足时，返回true
     * 选择“拍照”时，需要的权限
     *
     * @return
     */
    public static boolean checkTakePhotoPermission(Context mContext) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            //6.0以上需要WRITE_EXTERNAL_STORAGE和CAMERA两个权限才能打开相机
            int cameraPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA);
            int writeExternalPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE);
            if (writeExternalPermission != PackageManager.PERMISSION_GRANTED || cameraPermission != PackageManager
                    .PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mContext,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        IETConstant.TAKEPHOTO_PERMISSION_REQUESTCODE);
                T.showShort(mContext, mContext.getString(R.string.permission_2));
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * Android 版本低于6.0时，或权限都满足时，返回true
     * 从相册选择图片时，需要的权限
     *
     * @return
     */
    public static boolean checkAlbumStroagePermission(Context mContext) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int writeExternalPermission = ActivityCompat.checkSelfPermission(mContext, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE);
            if (writeExternalPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) mContext,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        IETConstant.ALBUM_PERMISSION_REQUESTCODE);
                T.showShort(mContext, mContext.getString(R.string.permission_2));
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


}
