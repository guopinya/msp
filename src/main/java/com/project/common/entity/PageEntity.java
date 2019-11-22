package com.project.common.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.io.Serializable;

/**
 * 分页实体
 *
 * @author zhuyifa
 * @since 2019-10-14
 */
public class PageEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 页号 默认1
     */
    private Integer page;

    /**
     * 页面大小 默认10
     */
    private Integer limit;

    /**
     * 是否为空
     *
     * @return 是否为空
     */
    public final boolean isEmpty() {
        return page == null || limit == null;
    }

    /**
     * 获取 mybatisplus分页
     *
     * @return mybatisplus分页
     */
    public final IPage<T> getMpPage() {
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = Integer.MAX_VALUE;
        }

        return new Page<>(page, limit);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}