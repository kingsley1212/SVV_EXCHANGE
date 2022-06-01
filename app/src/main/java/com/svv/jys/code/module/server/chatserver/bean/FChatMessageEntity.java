package com.svv.jys.code.module.server.chatserver.bean;

import android.graphics.Bitmap;


import com.svv.jys.code.common.base.BaseEntity;
import com.svv.jys.code.common.utils.BitmapUtil;
import com.svv.jys.code.common.utils.ToolUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lzj on 2018/1/5.
 */

public class FChatMessageEntity extends BaseEntity {

    public static final int TX_MSG = 1;
    public static final int IMG_MSG = 2;
    public int media_type;
    public File media_image;
    public WeakReference<Bitmap> weakBitMap;
//    public Bitmap bitmap ;

    public String user_id;
    public String user_logo;
    public String to_user_id;
    public String order_id;
    public String content;
    public String all;//如果1就是系统信息
    public String add_time;
    public String id;
    public String img;

    public boolean isOnline;
    public boolean isOther;


    public void from_JSON(JSONObject json) {
        try {
            this.user_id = json.getString("user_id");
            this.to_user_id = json.getString("to_user_id");
            this.order_id = json.getString("order_id");
            this.content = json.getString("content");
            this.all = json.getString("all");
            this.add_time = json.getString("add_time");
            this.id = json.getString("id");
        } catch (Exception e) {
        }
        //使用正则表达式，排除img标签src属性值已经是base64编码的情况
        Pattern pattern = Pattern.compile("img\\[[^\\s]*\\]\\[lay\\]");
        Matcher matcher = pattern.matcher(this.content);
       if (matcher.matches()) {
            this.media_type = IMG_MSG;
            this.content = content.replaceAll("img\\[", "");
            this.content = content.replaceAll("\\]\\[lay\\]", "");
            this.weakBitMap = new WeakReference<Bitmap>(BitmapUtil.base64ToBitmap(content));
            this.img=content;
//            this.bitmap = BitmapUtil.base64ToBitmap(content);
        } else {
            this.media_type = TX_MSG;
        }
    }

    public JSONObject to_JSON() {
        JSONObject json = new JSONObject();
        switch (media_type) {
            case IMG_MSG:
                try {
                    json.put("uid", user_id);
                    json.put("to_id", to_user_id);
                    json.put("order_id", order_id);
                    if (media_image != null) {
                        this.content = ToolUtils.encodeBase64File(media_image.getPath());
                        this.weakBitMap = new WeakReference<Bitmap>(BitmapUtil.base64ToBitmap(content));
//                    this.bitmap = BitmapUtil.base64ToBitmap(content);
                        json.put("content", "img[data:image/*;base64," + content + "][lay]");
                    } else {
                        JSONObject j = new JSONObject();
                        j.put("img", content);
                        json.put("content", j);
                    }
                } catch (Exception e) {
                }
                break;
            case TX_MSG:
                try {
                    json.put("uid", user_id);
                    json.put("to_id", to_user_id);
                    json.put("order_id", order_id);
                    json.put("content", content == null ? "" : content);
                } catch (Exception e) {
                }
            default:
                break;
        }

        return json;
    }


    public static List<FChatMessageEntity> from_JARRAY(JSONArray ja) {
        List<FChatMessageEntity> fChatMessageEntities = new ArrayList<>();

        for (int i = 0; i < ja.length(); i++) {
            try {
                JSONObject json = ja.getJSONObject(i);
                FChatMessageEntity e = new FChatMessageEntity();
                e.from_JSON(json);
                fChatMessageEntities.add(e);
            } catch (Exception e) {
            }
        }

        return fChatMessageEntities;
    }
}
