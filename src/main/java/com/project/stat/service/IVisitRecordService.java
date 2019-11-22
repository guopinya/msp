package com.project.stat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.stat.entity.VisitRecord;


/**
 * 统计访问浏览 服务类
 *
 * @author tangmingxuan
 * @since 2019-10-09
 */
public interface IVisitRecordService extends IService<VisitRecord> {


    /**
     * 增加访问记录
     *
     * @param
     * @param
     * @return
     */
    void addVisitRecord(String userId, String visitType);

}