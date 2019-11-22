package com.project.stat.controller;

import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.utils.HttpUtils;
import com.project.stat.service.IVisitRecordService;
import com.project.user.entity.User;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 发布统计接口
 *
 * @author tangmingxuan
 * @since 2019-10-09
 */
@RestController
@RequestMapping("/stat/visitrecord")
public class VisitRecordController extends BaseController {

    @Autowired
    private IVisitRecordService visitRecordService;

    /**
     * 访问记录
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/addRecord")
    public JsonResult addRecord(String visitType) {

        // 用户ID
        String userId = User.getLoginUserId();

        if (StringUtils.isBlank(userId)) {
            userId = HttpUtils.getIpAddress();
        }

        visitRecordService.addVisitRecord(userId, visitType);

        return JsonResult.ok("记录访问数据成功");
    }

}