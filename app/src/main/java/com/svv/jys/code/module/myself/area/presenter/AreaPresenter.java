package com.svv.jys.code.module.myself.area.presenter;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.area.model.AreaModel;
import com.svv.jys.code.module.myself.area.model.impl.AreaModelImpl;
import com.svv.jys.code.module.myself.area.view.AreaView;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by js on 2018/9/8.
 */

public class AreaPresenter extends BasePresent<AreaView,AreaModel>{
    public AreaPresenter(){
        model=new AreaModelImpl();
    }
    public void getCountries(){
        POST_KONG_REQ req=new POST_KONG_REQ();
        model.getCountries(req).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                showLoading(view.getMContext());
            }
        }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<CountryEntity>>() {
            @Override
            public  List<CountryEntity> call(BaseResponse baseResponse) {
                List<CountryEntity> countryEntities = new ArrayList<>();
                JSONObject jsonObject = JSONObject.parseObject(baseResponse.datas);
                String MapDatas = jsonObject.getString("default");
                String keys = JSONObject.parseObject(MapDatas).getString("keys");
                List<String> strings = JSONArray.parseArray(keys, String.class);
                JSONObject values = JSONObject.parseObject(MapDatas).getJSONObject("values");
                for (String key : strings){
                    JSONArray array = values.getJSONArray(key);
                    for (Object o :array){
                        JSONObject obj = (JSONObject) o;
                        String e = obj.toJSONString();
                        CountryEntity entity =  JSONObject.parseObject(e,CountryEntity.class);
                        countryEntities.add(entity);
                    }
                }
                return countryEntities;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber< List<CountryEntity>>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                ToolUtils.doNetErroMsg(view.getMContext(), e, true, true);
                dismissLoading(view.getMContext());

            }

            @Override
            public void onNext( List<CountryEntity> list) {
                view.setCountry(list);
            }
        });
    }
}
