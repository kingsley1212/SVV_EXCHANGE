package com.svv.jys.code.common.entity;

/**
 * Created by js on 2018/6/9.
 */

public class PopEntity {
    private String name;
    private String id;
    private boolean isSelect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public PopEntity(String name, String id, boolean isSelect) {
        this.name = name;
        this.id = id;
        this.isSelect = isSelect;
    }
}
