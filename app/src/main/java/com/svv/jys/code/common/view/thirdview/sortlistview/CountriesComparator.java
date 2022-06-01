package com.svv.jys.code.common.view.thirdview.sortlistview;


import com.svv.jys.R;
import com.svv.jys.code.common.entity.CountryEntity;
import com.svv.jys.code.module.app.MAppliaction;

import java.util.Comparator;

/**
 * Created by LB on 2018/1/11.
 */

public class CountriesComparator implements Comparator<CountryEntity> {

    public int compare(CountryEntity o1, CountryEntity o2) {
        if (o1.getFirst_name().equals("@")
                || o2.getFirst_name().equals("#")
                || o1.getFirst_name().equals(MAppliaction.getApp().getString(R.string.common))) {
            return -1;
        } else if (o1.getFirst_name().equals("#")
                || o2.getFirst_name().equals("@")
                || o2.getFirst_name().equals(MAppliaction.getApp().getString(R.string.common))) {
            return 1;
        } else {
            return o1.getFirst_name().compareTo(o2.getFirst_name());
        }
    }

    public int compare(String o1, String o2) {
        if (String.valueOf(o1.charAt(0)).equals("@")
                || String.valueOf(o2.charAt(0)).equals("#")) {
            return -1;
        } else if (String.valueOf(o1.charAt(0)).equals("#")
                || String.valueOf(o2.charAt(0)).equals("@")) {
            return 1;
        } else {
            return String.valueOf(o1.charAt(0)).compareTo(String.valueOf(o2.charAt(0)));
        }
    }
}
