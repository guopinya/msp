package com.project.sq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.sq.entity.CircleMaster;
import com.project.sq.entity.PostMaster;
import org.springframework.stereotype.Repository;

/**
 * 圈子达人映射器
 *
 * @author zhuyifa
 */
@Repository
public interface CircleMasterMapper extends BaseMapper<CircleMaster> {

    /**
     * 查询是否存在
     *
     * @param circleId   圈子ID
     * @param masterId 达人ID（用户ID）
     * @return 是否存在
     */
    boolean exists(String circleId, String masterId);

    /**
     * 删除圈子达人
     *
     * @param circleId   圈子ID
     * @param masterId 达人ID（用户ID）
     * @return 是否成功
     */
    boolean delete(String circleId, String masterId);

    /**
     * 圈子达人分页
     *
     * @param p  分页对象
     * @param p2 达人对象
     * @return 获取到的圈子达人分页对象
     */
    IPage<CircleMaster> selectPage(IPage<CircleMaster> p, CircleMaster p2);
}