package com.pearl.fcw.gm.pojo;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.SYS)
public class WSysBannedWord extends DmModel {

    private static final long serialVersionUID = -39952520716623382L;

    private Integer id;

    private String bannedWord;

    private String deleted;

    private String includeInWord;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannedWord() {
        return bannedWord;
    }

    public void setBannedWord(String bannedWord) {
        this.bannedWord = bannedWord == null ? null : bannedWord.trim();
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted == null ? null : deleted.trim();
    }

    public String getIncludeInWord() {
        return includeInWord;
    }

    public void setIncludeInWord(String includeInWord) {
        this.includeInWord = includeInWord == null ? null : includeInWord.trim();
    }

    @Override
    public boolean getIsRemoved() {
        return "Y".equals(deleted);
    }

    @Override
    public void setIsRemoved(boolean isRemoved) {
        deleted = isRemoved ? "Y" : "N";
    }
}