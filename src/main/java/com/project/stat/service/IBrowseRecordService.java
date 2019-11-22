package com.project.stat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.stat.entity.BrowseRecord;


/**
 * 统计帖子浏览 服务类
 *
 * @author tangmingxuan
 * @since 2019-09-29
 */
public interface IBrowseRecordService extends IService<BrowseRecord> {


    /**
     * 增加每日登陆记录
     *
     * @param
     * @param
     * @return
     */
    void addBrowseRecord(String userId, String postId, String postType);

    /**
     * 增加每日登陆记录
     *
     * @param
     * @param
     * @return
     */
    Integer getBrowseRecord(String postId);

}