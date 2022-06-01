package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

import java.util.List;

/**
 * Created by js on 2018/5/17.
 */

public class BannerEntity extends BaseEntity {
    private List<String> pic ;

    private List<String> link ;

    private List<String> pos ;

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }

    public List<String> getLink() {
        return link;
    }

    public void setLink(List<String> link) {
        this.link = link;
    }

    public List<String> getPos() {
        return pos;
    }

    public void setPos(List<String> pos) {
        this.pos = pos;
    }
}
