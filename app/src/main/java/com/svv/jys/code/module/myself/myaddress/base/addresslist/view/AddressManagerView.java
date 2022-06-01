package com.svv.jys.code.module.myself.myaddress.base.addresslist.view;

import com.svv.jys.code.common.base.mvp.BaseView;
import com.svv.jys.code.common.entity.AddressEntity;

import java.util.List;

/**
 * Created by js on 2018/5/19.
 */

public interface AddressManagerView extends BaseView{
    void setAddress(List<AddressEntity> list);
    void deleteSuccese(int position);

    void loadMoreAddress(List<AddressEntity> list);
}
