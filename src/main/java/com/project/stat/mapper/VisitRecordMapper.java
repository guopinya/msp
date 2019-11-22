package com.project.stat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.stat.entity.VisitRecord;

import org.springframework.stereotype.Repository;

/**
 * Mapper 接口
 *
 * @author tangmingxuan
 * @since 2019-10-09
 */
@Repository
public interface VisitRecordMapper extends BaseMapper<VisitRecord> {

    /**
     * 通过用户ID和访问类型判断存在
     *
     * @param userId 用户ID
     * @param
     * @return
     */
    VisitRecord selectByUserIdAndVisitType(String userId, String visitType);

    /**
     * 通过用户ID和访问类型更新
     *
     * @param userId 用户ID
     * @param
     * @return
     */
    void updateByUserIdAndVisitType(VisitRecord visitRecord);

}