package com.pearl.fcw.info.core.persistence.config;

import java.util.ArrayList;
import java.util.List;

import com.pearl.fcw.info.core.persistence.config.ClassMetadata.FieldMetadata;

public class TableIndex {

    private String name = null;
    private int length = 0;
    private List<FieldMetadata> columns = new ArrayList<>();

    public TableIndex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<FieldMetadata> getColumns() {
        return columns;
    }

    public void add(FieldMetadata fieldMetadata) {
        columns.add(fieldMetadata);
    }

}
