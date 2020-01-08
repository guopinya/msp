package com.project.msp.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.constants.Constants;
import com.project.common.entity.JsonResult;
import com.project.common.utils.DateUtils;
import com.project.common.utils.SecurityUtils;
import com.project.common.utils.StrUtils;
import com.project.msp.entity.Evaluate;
import com.project.msp.entity.Order;
import com.project.msp.entity.Project;
import com.project.msp.entity.Shop;
import com.project.msp.mapper.OrderMapper;
import com.project.msp.mapper.ProjectMapper;
import com.project.msp.mapper.ShopMapper;
import com.project.msp.service.IOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ShopMapper shopMapper;

    /**
     * 生成订单
     *
     * @param order 实体条件
     */
    @Override
    public JsonResult create(Order order) {
        //------------校验项目------------
        String projectId = order.getProjectId();
        if (StrUtils.isEmpty(projectId))
            return JsonResult.fail("找不到当前项目了");
        Project project = projectMapper.selectById(projectId);
        if (project == null)
            return JsonResult.fail("找不到当前项目了");
        if (!Constants.IS_DISPLAY_NOT.equals(project.getIsDisplay()))
            return JsonResult.fail("当前项目已经失效");

        //------------校验店铺------------
        String shopId = order.getShopId();
        if (StrUtils.isEmpty(shopId))
            return JsonResult.fail("找不到当前店铺了");
        Shop shop = shopMapper.selectById(shopId);
        if (shop == null)
            return JsonResult.fail("找不到当前店铺了");
        if (!Constants.IS_DISPLAY_NOT.equals(shop.getIsDisplay()))
            return JsonResult.fail("当前店铺已经失效");

        //------------订单号------------
        String currTime = DateUtils.getCurrTime();// 14位日期
        String strRandom = StrUtils.buildRandom(2) + "";// 4位随机数
        String orderNo = "M" + currTime + strRandom;

        //------------组装订单信息------------
        order.setOrderNo(orderNo);
        order.setUserId(SecurityUtils.getUserId());
        order.setProjectId(project.getId());
        order.setAreaId(shop.getAreaId());//？？？？？？？？？根据店铺还是项目还是用户当前位置
        order.setShopId(shop.getId());
        order.setServicerId(null);//生成订单时手艺人不生成

        order.setOrderState("00");//代付款
        order.setOrderOrigin("h5");//h5

        order.setOrderPreTime(order.getOrderPreTime());
        order.setOrderPayTime(null);
        order.setOrderServiceTime(null);
        order.setOrderSuccTime(null);
        order.setOrderEvaTime(null);

        order.setOrderRemark(order.getOrderRemark());
        order.setOrderBaseAmount(project.getProjectPrice());
        order.setOrderPayAmount(null);
        order.setOrderCreditAmount(null);
        order.setOrderPayType(null);

        order.setOrderProjectImage(project.getProjectImage());
        order.setOrderProjectName(project.getProjectName());
        order.setOrderProjectTag(project.getProjectTag());
        order.setOrderProjectPrice(project.getProjectPrice());

        order.setOrderShopName(shop.getShopName());
        order.setOrderShopAddr(shop.getShopAddr());
        order.setOrderShopPhone(shop.getShopPhone());

        order.setOrderServicerName(null);

        //------------保存订单------------
        boolean result = save(order);
        return JsonResult.ok("添加订单成功", order);
    }

    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    @Override
    public List<Order> list(Wrapper<Order> order) {
        return orderMapper.list(order);
    }

    /**
     * 按条件查询分页
     *
     * @param page  分页条件
     * @param order 条件
     * @return 分页
     */
    @Override
    public IPage<Order> pageByOrderCond(IPage<Order> page, Order order) {
        return orderMapper.pageByOrderCond(page, order);
    }

}
