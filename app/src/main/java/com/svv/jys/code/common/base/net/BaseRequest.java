package com.svv.jys.code.common.base.net;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lzj on 2017/6/3.
 */

public  class BaseRequest implements  java.io.Serializable{

    /**
     * 媒体集合
     */
    public List<BaseMulitRequest> baseMulitRequests ;

    /**
     * 丢入参数
     * @return
     */
    public Map<String,String> bulitReqMap(){
        return new HashMap<>();
    };

}
