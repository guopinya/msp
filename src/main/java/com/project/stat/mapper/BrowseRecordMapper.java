package com.project.stat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.stat.entity.BrowseRecord;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * Mapper 接口
 *
 * @author tangmingxuan
 * @since 2019-09-29
 */
@Repository
public interface BrowseRecordMapper extends BaseMapper<BrowseRecord> {

    /**
     * 通过用户ID和帖子ID判断存在
     *
     * @param userId 用户ID
     * @param
     * @return
     */
    BrowseRecord selectByUserIdAndPostId(String userId, String postId);

    /**
     * 通过帖子ID获取记录
     *
     * @param userId 用户ID
     * @param
     * @return
     */
    List<BrowseRecord> selectBrowseRecord(String postId);

    /**
     * 通过用户ID和帖子ID更新
     *
     * @param userId 用户ID
     * @param
     * @return
     */
    void updateByUserIdAndPostId(BrowseRecord browseRecord);

}