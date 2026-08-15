package com.pearl.o2o.pojo;

public class Page {
    private int pageSize;//分页大小
    private int total;//总条目
    private int pageNum;//分页总数目
    private int pageNow;//当前页数
    private int start;//开始位置
    private int end;//结束位置

    public Page(int pageSize, int total, int pageNow) {
        super();
        this.pageSize = pageSize;
        this.total = total;
        this.pageNow = pageNow;
        this.pageNum = (this.total + this.pageSize - 1) / this.pageSize;//分页总数目
        this.pageNow = this.pageNow < 1 ? 1 : this.pageNow;
        this.pageNow = this.pageNow > this.pageNum ? this.pageNum : this.pageNow;
        this.start = (this.pageNow - 1) * this.pageSize;
        this.end = this.pageNow * this.pageSize - 1;
        this.end = this.end>this.total? this.total:this.end;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageNow() {
        return pageNow;
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
