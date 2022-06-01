package com.svv.jys.code.common.view.thirdview.sortlistview;


import java.util.Comparator;

/**
 * Created by LB on 2018/1/11.
 */

public class CoinsComparator implements Comparator<String> {



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
