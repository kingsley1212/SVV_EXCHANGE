package com.svv.jys.code.common.view.thirdview.sortlistview;


import com.svv.jys.code.common.entity.CountryEntity;

import java.util.Comparator;

/**
 * 实现Comparable接口   用于排序
 */
public class PinyinComparator implements Comparator<CountryEntity> {

    public int compare(CountryEntity o1, CountryEntity o2) {
        if (o1.getFirst_name().equals("@")
                || o2.getFirst_name().equals("#")) {
            return -1;
        } else if (o1.getFirst_name().equals("#")
                || o2.getFirst_name().equals("@")) {
            return 1;
        } else {
            return o1.getFirst_name().compareTo(o2.getFirst_name());
        }
    }

}
