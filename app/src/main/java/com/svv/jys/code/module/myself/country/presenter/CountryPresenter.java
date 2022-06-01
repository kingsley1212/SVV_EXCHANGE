package com.svv.jys.code.module.myself.country.presenter;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.BasePresent;
import com.svv.jys.code.common.base.net.BaseResponse;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.utils.ToolUtils;
import com.svv.jys.code.module.myself.country.model.CountryModel;
import com.svv.jys.code.module.myself.country.model.impl.CountryModelImpl;
import com.svv.jys.code.module.myself.country.view.CountryView;
import com.svv.jys.code.module.net.req.POST_KONG_REQ;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by LB on 2018/3/2.
 */

public class CountryPresenter extends BasePresent<CountryView, CountryModel> {
    public CountryPresenter() {
        this.model = new CountryModelImpl();
    }

    public void getAreaCode() {
        POST_KONG_REQ req=new POST_KONG_REQ();
        model.rx_getAreaCode(req)
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showLoading(view.getMContext());
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io()).map(new Func1<BaseResponse, List<CountryEntity>>() {
            @Override
            public List<CountryEntity> call(BaseResponse baseResponse) {
                List<CountryEntity> countryEntities = new ArrayList<>();
                JSONObject jsonObject = JSONObject.parseObject(baseResponse.datas);
                String common = jsonObject.getString("common");
                List<CountryEntity> list =  JSONArray.parseArray(common, CountryEntity.class);
                for(CountryEntity entity : list){
                    entity.setFirst_name(view.getMContext().getString(R.string.common));
                }
                countryEntities.addAll(list);
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
                        entity.setFirst_name(key);
                        countryEntities.add(entity);
                    }
                }
                return countryEntities;
            }
        })
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<CountryEntity>>() {
            @Override
            public void onCompleted() {
                dismissLoading(view.getMContext());
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading(view.getMContext());
                ToolUtils.doNetErroMsg(view.getMContext(), e, true);
            }

            @Override
            public void onNext(List<CountryEntity> list) {
                view.setData(list);
            }
        });
    }

    public static String getPinYin(String chines) {
        StringBuffer sb = new StringBuffer();

        sb.setLength(0);
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(nameChar[i]);
            }
        }
        return sb.toString();
    }
}
