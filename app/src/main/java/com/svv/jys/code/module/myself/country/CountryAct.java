package com.svv.jys.code.module.myself.country;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.svv.jys.R;
import com.svv.jys.code.common.base.mvp.MvpActivity;
import com.svv.jys.code.common.constant.ACEConstant;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.common.view.thirdview.slide.SlideBar;
import com.svv.jys.code.common.view.thirdview.sortlistview.CharacterParser;
import com.svv.jys.code.common.view.thirdview.sortlistview.CountriesComparator;
import com.svv.jys.code.module.myself.country.adapter.CountryAdapter;
import com.svv.jys.code.module.myself.country.model.CountryModel;
import com.svv.jys.code.module.myself.country.presenter.CountryPresenter;
import com.svv.jys.code.module.myself.country.view.CountryView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by LB on 2018/3/2.
 */

public class CountryAct extends MvpActivity<CountryView, CountryModel, CountryPresenter> implements CountryView {
    private ListView sortListView;
    private TextView dialog;
    private CountryAdapter adapter;
    private SlideBar slideBar;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<CountryEntity> SourceDateList;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private CountriesComparator countriesComparator;
    private CountryEntity countriesEntity;

    @Override
    public Context getMContext() {
        return this;
    }

    @Override
    public CountryPresenter initPresenter() {
        return new CountryPresenter();
    }

    @Override
    public void baseInitialization() {

    }

    @Override
    public int setR_Layout() {
        return R.layout.act_area_code;
    }

    @Override
    public void viewInitialization() {
        setBackPress();
        setTitleTx(getString(R.string.country_area));
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        slideBar = (SlideBar) $(R.id.sidrbar);
        slideBar.setOnTouchingLetterChangedListener(new SlideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                if(adapter!=null) {
                    int position = adapter.getPositionForSection(s);
                    if (position != -1) {
                        sortListView.setSelection(position);
                    }
                }
            }
        });
        countriesComparator = new CountriesComparator();


        dialog = (TextView) $(R.id.dialog);
        sortListView = (ListView) $(R.id.country_lvcountry);
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象
                CountryEntity countriesEntity = (CountryEntity) adapter.getItem(position);
                Intent mIntent = new Intent();
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("countriesEntity", countriesEntity);
                mIntent.putExtras(mBundle);
                setResult(ACEConstant.REQUEST_OK, mIntent);
                finish();
            }
        });



    }

    @Override
    public void doBusiness() {
        presenter.getAreaCode();
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<CountryEntity> filterDateList = new ArrayList<CountryEntity>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = SourceDateList;
        } else {
            filterDateList.clear();
            for (CountryEntity sortModel : SourceDateList) {
                String name = sortModel.getChinese();
                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, countriesComparator);
        adapter.updateListView(filterDateList);
    }

    @Override
    public void setData(List<CountryEntity> list) {
        SourceDateList = list;
        if (adapter == null) {
            // 根据a-z进行排序源数据
            Collections.sort(SourceDateList, countriesComparator);
            adapter = new CountryAdapter(this, SourceDateList);
            sortListView.setAdapter(adapter);
        } else {
            adapter.updateListView(list);
        }
    }
}
