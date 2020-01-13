package com.project.sq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.utils.SecurityUtils;
import com.project.sq.entity.CrowdMaster;
import com.project.sq.entity.Crowd;
import com.project.sq.mapper.CrowdMapper;
import com.project.sq.mapper.CrowdMasterMapper;
import com.project.sq.service.ICrowdService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 众筹服务实现
 *
 * @author zhuyifa
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ICrowdServiceImpl extends ServiceImpl<CrowdMapper, Crowd> implements ICrowdService {

    private final CrowdMapper crowdMapper;
    private final CrowdMasterMapper masterMapper;

    @Override
    public IPage<Crowd> pageByEntity(IPage<Crowd> p, Crowd p2) {
        // 判断是否查询参与的众筹
        Boolean isJoined = p2.getIsJoined();
        if (Boolean.TRUE.equals(isJoined)) {
            // 查询关注的帖子需要登录
            p2.setUserId(SecurityUtils.getUserId());
        }

        // 查询众筹分页
        return crowdMapper.selectPage(p, p2);
    }

    @Override
    public IPage<CrowdMaster> pageMaster(IPage<CrowdMaster> p, CrowdMaster p2) {
        // 查询众筹达人分页
        return masterMapper.selectPage(p, p2);
    }

    @Override
    public void saveMaster(CrowdMaster p) {
        // 查询是否存在
        boolean exists = masterMapper.exists(
                p.getCrowdId(),
                p.getMasterId());
        // 不存在则新增
        if (!exists) {
            masterMapper.insert(p);
        }
    }

}
