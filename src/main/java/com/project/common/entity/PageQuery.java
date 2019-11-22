package com.project.common.entity;

import java.io.Serializable;

/**
 * 分页查询
 *
 * @author hubin
 * @since 2019-06-14
 */
public class PageQuery implements Serializable {

    /**
     * 顺序
     */
    public static final String ASC = "ASC";
    /**
     * 倒序
     */
    public static final String DESC = "DESC";
    private static final long serialVersionUID = 1L;
    /**
     * 页号 默认1
     */
    private Integer pageNumber = 1;

    /**
     * 页面大小 默认10
     */
    private Integer pageSize = 10;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序方式 ASC顺序 DESC倒序
     */
    private String orderMode = ASC;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderMode() {
        return orderMode;
    }

    public void setOrderMode(String orderMode) {
        this.orderMode = orderMode;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", orderBy='" + orderBy + '\'' +
                ", orderMode='" + orderMode + '\'' +
                '}';
    }
}
