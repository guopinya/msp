package com.project.stat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.stat.entity.BrowseRecord;
import com.project.stat.mapper.BrowseRecordMapper;
import com.project.stat.service.IBrowseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 浏览记录表 类
 *
 * @author tangmingxuan
 * @since 2019-09-29
 */
@Service
public class BrowseRecordServiceImpl extends ServiceImpl<BrowseRecordMapper, BrowseRecord> implements IBrowseRecordService {

    @Autowired
    private BrowseRecordMapper browseRecordMapper;


    /**
     * 增加浏览记录
     *
     * @param
     * @param
     * @return
     */
    @Override
    public void addBrowseRecord(String userId, String postId, String postType) {
        BrowseRecord browseRecord = browseRecordMapper.selectByUserIdAndPostId(userId, postId);

        if (browseRecord == null) {
            browseRecord = new BrowseRecord(userId, postId, postType);
            browseRecordMapper.insert(browseRecord);
        } else {
            browseRecord.addBrowseNum();
            browseRecord.setPostType(postType);
            browseRecordMapper.updateByUserIdAndPostId(browseRecord);
        }

    }

    /**
     * 统计浏览记录 服务类
     *
     * @param
     * @param
     * @return
     */
    @Override
    public Integer getBrowseRecord(String postId) {
        List<BrowseRecord> list = browseRecordMapper.selectBrowseRecord(postId);

        Integer result = 0;

        if (list != null) {
            for (int k = 0; k < list.size(); k++) {
                result += list.get(k).getBrowseNum();
            }
            return result;
        }

        return 0;

    }

}