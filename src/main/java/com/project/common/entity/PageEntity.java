package com.project.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页实体
 *
 * @author zhuyifa
 * @since 2019-10-14
 */
@Data
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
    public final Page<T> getMpPage() {
        if (page == null) {
            page = 1;
        }
        if (limit == null) {
            limit = 10;
        }

        return new Page<>(page, limit);
    }

}