package com.svv.jys.code.common.entity;

import com.svv.jys.code.common.base.BaseEntity;

public class CoinIntroduceEntity extends BaseEntity {
    private String issue;
    private String website;
    private String white_paper;
    private String block_link;
    private String name;
    private String description;

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWhite_paper() {
        return white_paper;
    }

    public void setWhite_paper(String white_paper) {
        this.white_paper = white_paper;
    }

    public String getBlock_link() {
        return block_link;
    }

    public void setBlock_link(String block_link) {
        this.block_link = block_link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
