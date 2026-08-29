package com.pde.uweb.admin.easyui.data;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-8-26
 * Time: 上午2:06
 * To change this template use File | Settings | File Templates.
 * <p/>
 * datagrid 数据包装体 ，用于同easyui中datagrid 交换数据
 * <p/>
 * total字段，只有在做分页时需要人为指定，其他情况不需指定
 */
@JsonAutoDetect
public class Grid<R, F> {
    private int total;
    private Collection<R> rows;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Collection<F> footer;

    public Grid() {
        this(new ArrayList<R>(), new ArrayList<F>());
    }

    /**
     * @param rows
     */
    public Grid(Collection<R> rows) {
        this(rows, new ArrayList<F>());
    }

    /**
     * @param rows
     * @param footer
     */
    public Grid(Collection<R> rows, Collection<F> footer) {
        this.rows = rows;
        this.footer = footer;
        this.total = rows.size();
    }

    /**
     * 用于分页
     *
     * @param rows
     * @param total
     */
    public Grid(Collection<R> rows, int total) {
        this.rows = rows;
        this.total = total;
    }

    public Grid(List<R> rows, int page, int pageSize) {
        this(rows, new ArrayList<F>(), page, pageSize);
    }

    /**
     * 用于分页
     * rows为全部数据
     *
     * @param rows
     * @param footer
     * @param page
     * @param pageSize
     */
    public Grid(List<R> rows, Collection<F> footer, int page, int pageSize) {
        this.total = rows.size();
        if (pageSize > 0 && page > 0) {
            int startIndex = (page - 1) * pageSize;
            int endIndex = page * pageSize;
            if (startIndex < rows.size()) {
                this.rows = rows.subList(startIndex, endIndex > rows.size() ? rows.size() : endIndex);
            } else {
                this.rows = new ArrayList<R>();
            }
        } else {
            this.rows = rows;
        }
        this.footer = footer;
    }

    public void addRow(R row) {
        this.rows.add(row);
        this.total = rows.size();
    }

    public void addFooter(F footer) {
        this.footer.add(footer);
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public Collection<R> getRows() {
        return rows;
    }

    public Collection<F> getFooter() {
        return footer;
    }
}
