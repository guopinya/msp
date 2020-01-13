package com.project.sq.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.sq.entity.Crowd;
import com.project.sq.entity.CrowdMaster;

/**
 * 众筹服务
 *
 * @author zhuyifa
 */
public interface ICrowdService extends IService<Crowd> {

    /**
     * 查询众筹分页
     *
     * @param p  分页对象
     * @param p2 众筹对象
     * @return 查询到的众筹分页对象
     */
    IPage<Crowd> pageByEntity(IPage<Crowd> p, Crowd p2);

    /**
     * 查询众筹达人分页
     *
     * @param p  分页对象
     * @param p2 达人对象
     * @return 查询到的达人分页对象
     */
    IPage<CrowdMaster> pageMaster(IPage<CrowdMaster> p, CrowdMaster p2);

    /**
     * 保存众筹达人
     *
     * @param p 达人对象
     */
    void saveMaster(CrowdMaster p);
}
