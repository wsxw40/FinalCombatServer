package com.pearl.fcw.utils;

import java.util.ArrayList;
import java.util.List;

public class DataTablesPage<T> {

    private Integer draw;
    private Integer recordsTotal;
    private Integer recordsFiltered;
    private String error;
    private List<T> data;

    public DataTablesPage() {
        data = new ArrayList<>();
    }

    public DataTablesPage(List<T> data) {
        this.data = data;
        recordsTotal = data.size();
        recordsFiltered = data.size();
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void add(T t) {
        data.add(t);
    }


}
