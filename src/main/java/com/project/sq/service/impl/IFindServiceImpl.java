package com.project.sq.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.sq.entity.Find;
import com.project.sq.mapper.FindMapper;
import com.project.sq.service.IFindService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 搜索服务实现
 *
 * @author zhuyifa
 */
@Service
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class IFindServiceImpl extends ServiceImpl<FindMapper, Find> implements IFindService {

    private final FindMapper findMapper;

    @Override
    public boolean save(Find entity) {
        Wrapper<Find> wrapper = new LambdaQueryWrapper<Find>()
                .eq(Find::getType, entity.getType())
                .eq(Find::getUserId, entity.getUserId())
                .eq(Find::getKeyword, entity.getKeyword());
        Find find = findMapper.selectOne(wrapper);
        if (find == null) {
            return super.save(entity);
        }

        return super.updateById(find);
    }

    @Override
    public IPage<Find> page(Page<Find> p, Find p2) {
        // 条件构造器
        LambdaQueryWrapper<Find> wrapper = new LambdaQueryWrapper<Find>()
                .eq(Find::getType, p2.getType());
        // 操作名称 populars热门搜索 histories搜索历史
        String opName = p2.getOpName();

        // 热门搜索
        if ("populars".equalsIgnoreCase(opName)) {
            // 关键字分组
            wrapper.groupBy(Find::getKeyword);
            // 统计数量排序
            p.addOrder(OrderItem.desc("count(*)"));
        }

        // 搜索历史
        if ("histories".equalsIgnoreCase(opName)) {
            // 用户ID匹配
            wrapper.eq(Find::getUserId, p2.getUserId());
            // 更新时间排序
            p.addOrder(OrderItem.desc("update_time"));
        }

        // 查询搜索分页
        return findMapper.selectPage(p, wrapper);
    }

}
