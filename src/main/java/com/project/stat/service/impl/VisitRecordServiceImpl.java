package com.project.stat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.stat.entity.VisitRecord;
import com.project.stat.mapper.VisitRecordMapper;
import com.project.stat.service.IVisitRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 访问记录表 类
 *
 * @author tangmingxuan
 * @since 2019-10-09
 */
@Service
public class VisitRecordServiceImpl extends ServiceImpl<VisitRecordMapper, VisitRecord> implements IVisitRecordService {

    @Autowired
    private VisitRecordMapper visitRecordMapper;


    /**
     * 统计用户登录 服务类
     *
     * @param
     * @param
     * @return
     */
    @Override
    public void addVisitRecord(String userId, String visitType) {
        VisitRecord visitRecord = visitRecordMapper.selectByUserIdAndVisitType(userId, visitType);

        if (visitRecord == null) {
            visitRecord = new VisitRecord(userId, visitType);
            visitRecordMapper.insert(visitRecord);
        } else {
            visitRecord.addVisitNum();
            visitRecordMapper.updateByUserIdAndVisitType(visitRecord);
        }

    }

}