package com.project.sq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.sq.entity.CrowdMaster;
import org.springframework.stereotype.Repository;

/**
 * 众筹达人映射器
 *
 * @author zhuyifa
 */
@Repository
public interface CrowdMasterMapper extends BaseMapper<CrowdMaster> {

    /**
     * 查询是否存在
     *
     * @param crowdId  众筹ID
     * @param masterId 达人ID（用户ID）
     * @return 是否存在
     */
    boolean exists(String crowdId, String masterId);

    /**
     * 查询众筹达人分页
     *
     * @param p  分页对象
     * @param p2 达人对象
     * @return 查询到的达人分页对象
     */
    IPage<CrowdMaster> selectPage(IPage<CrowdMaster> p, CrowdMaster p2);
}