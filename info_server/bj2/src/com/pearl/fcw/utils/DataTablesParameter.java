package com.pearl.fcw.utils;

import java.util.Map;


/**
 * DataTables输入参数。
 */
public class DataTablesParameter {

    private int draw;
    private int start;
    private int length;
    private String orderColumn;
    private String orderDir;
    private String searchValue;
    private String[] orderColumns ;
    private String[] orderDirs;
    private Map<String, Object> specialSearch;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String[] getOrderColumns() {
        return orderColumns;
    }

    public void setOrderColumns(String[] orderColumns) {
        this.orderColumns = orderColumns;
    }

    public String[] getOrderDirs() {
        return orderDirs;
    }

    public void setOrderDirs(String[] orderDirs) {
        this.orderDirs = orderDirs;
    }

    public Map<String, Object> getSpecialSearch() {
        return specialSearch;
    }

    public void setSpecialSearch(Map<String, Object> specialSearch) {
        this.specialSearch = specialSearch;
    }

    @Override
    public String toString() {
        return "DataTablesParameter{" +
                "draw=" + draw +
                ", start=" + start +
                ", length=" + length +
                ", orderColumn='" + orderColumn + '\'' +
                ", orderDir='" + orderDir + '\'' +
                ", searchValue='" + searchValue + '\'' +
                '}';
    }
}
