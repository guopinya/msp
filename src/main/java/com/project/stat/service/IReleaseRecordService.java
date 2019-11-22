package com.project.stat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.stat.entity.ReleaseRecord;


/**
 * 统计帖子发布 服务类
 *
 * @author tangmingxuan
 * @since 2019-10-08
 */
public interface IReleaseRecordService extends IService<ReleaseRecord> {


    /**
     * 增加发布记录
     *
     * @param
     * @param
     * @return
     */
    void addReleaseRecord(String postsType, String action);

}