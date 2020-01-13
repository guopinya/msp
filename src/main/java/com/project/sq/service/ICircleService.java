package com.project.sq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.sq.entity.Circle;
import com.project.sq.entity.CircleMaster;

/**
 * 圈子服务
 *
 * @author zhuyifa
 */
public interface ICircleService extends IService<Circle> {

    /**
     * 查询圈子分页
     *
     * @param p  分页对象
     * @param p2 圈子对象
     * @return 查询到的圈子分页对象
     */
    IPage<Circle> page(Page<Circle> p, Circle p2);

    /**
     * 查询圈子达人分页
     *
     * @param p  分页对象
     * @param p2 达人对象
     * @return 查询到的达人分页对象
     */
    IPage<CircleMaster> pageMaster(IPage<CircleMaster> p, CircleMaster p2);

    /**
     * 保存圈子达人
     *
     * @param p 达人对象
     */
    void saveMaster(CircleMaster p);

    /**
     * 删除圈子达人
     *
     * @param p 达人对象
     */
    void removeMaster(CircleMaster p);
}
