package com.svv.jys.code.module.net.req;

import com.svv.jys.code.common.base.net.BaseRequest;

import java.util.Map;


/**
 * Created by 74099 on 2018/7/11.
 */

public class GetC2CRecordListReq extends BaseRequest {
    private String offset;//需要传
    private String limit;//需要传
    private String search_field;
    private String type;
    private String coin;//需要传
    private String s_time;
    private String e_time;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getSearch_field() {
        return search_field;
    }

    public void setSearch_field(String search_field) {
        this.search_field = search_field;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getS_time() {
        return s_time;
    }

    public void setS_time(String s_time) {
        this.s_time = s_time;
    }

    public String getE_time() {
        return e_time;
    }

    public void setE_time(String e_time) {
        this.e_time = e_time;
    }

    @Override
    public Map<String, String> bulitReqMap() {
        Map<String, String> map = super.bulitReqMap();
        map.put("e_time", e_time);
        map.put("s_time", s_time);
        map.put("coin", coin);
        map.put("type", type);
        map.put("search_field", search_field);
        map.put("limit", limit);
        map.put("offset", offset);
        return map;
    }
}
