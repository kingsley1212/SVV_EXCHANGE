package com.svv.jys.code.common.base.net;

import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzj on 2018/5/28.
 */

public class MPageResponse<T extends BaseEntity> extends BaseResponse {

    public String total;
    public int offset;
    public int limit;
    public List<T> rows;

    public void fromJSON(String content) {
        super.fromJSON(content);
        try {
            JSONObject json = JSONObject.parseObject(datas);
            this.offset = json.getIntValue("offset");
            this.limit = json.getIntValue("limit");
            this.total = json.getString("total");
//            Type type = this.getClass().getGenericSuperclass();
//            Class<T>  entityClass = (Class<T>) type.getActualTypeArguments()[0];
//            T te = entityClass.newInstance();
//            this.rows = (List<T>) T.fromJSONListAuto(json.getString("rows"), te.getClass());
        } catch (Exception e) {
            this.offset = 0;
            this.limit = 0;
            this.total = "0";
            this.rows = new ArrayList<>();
        }
    }

}
