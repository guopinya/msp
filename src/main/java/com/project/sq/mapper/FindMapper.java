package com.project.sq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.sq.entity.Find;
import org.springframework.stereotype.Repository;

/**
 * 搜索映射器
 *
 * @author zhuyifa
 */
@Repository
public interface FindMapper extends BaseMapper<Find> {

}