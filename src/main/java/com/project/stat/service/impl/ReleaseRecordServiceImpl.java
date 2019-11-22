package com.project.stat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.stat.entity.ReleaseRecord;
import com.project.stat.mapper.ReleaseRecordMapper;
import com.project.stat.service.IReleaseRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 浏览记录表 类
 *
 * @author tangmingxuan
 * @since 2019-09-29
 */
@Service
public class ReleaseRecordServiceImpl extends ServiceImpl<ReleaseRecordMapper, ReleaseRecord> implements IReleaseRecordService {

    @Autowired
    private ReleaseRecordMapper releaseRecordMapper;


    /**
     * 统计用户登录 服务类
     *
     * @param
     * @param
     * @return
     */
    @Override
    public void addReleaseRecord(String postsType, String action) {
        ReleaseRecord releaseRecord = releaseRecordMapper.selectById(postsType);

        if (releaseRecord == null) {
            releaseRecord = new ReleaseRecord(postsType);
            releaseRecordMapper.insert(releaseRecord);
        }

        switch (action) {
            case ReleaseRecord.STAT_POSTS_ACTION_ATT:
                releaseRecord.addAttempt();
                break;
            case ReleaseRecord.STAT_POSTS_ACTION_SAVE:
                releaseRecord.addSave();
                break;
            case ReleaseRecord.STAT_POSTS_ACTION_RELEASED:
                releaseRecord.addReleased();
                break;
            case ReleaseRecord.STAT_POSTS_ACTION_DRAFTRELEASED:
                releaseRecord.addReleaseDraft();
                break;
        }

        releaseRecordMapper.updateById(releaseRecord);
    }

}