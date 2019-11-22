package com.project.stat.controller;

import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.stat.service.IReleaseRecordService;
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
 * @since 2019-10-08
 */
@RestController
@RequestMapping("/stat/releaserecord")
public class ReleaseRecordController extends BaseController {

    @Autowired
    private IReleaseRecordService releaseRecordService;

    /**
     * 发布记录
     *
     * @param
     * @param
     * @return
     */
    @PostMapping("/addRecord")
    public JsonResult addRecord(String postsType, String action) {

        // 用户ID
        String userId = User.getLoginUserId();
        if (StringUtils.isBlank(userId)) {
            return JsonResult.fail(1001, "未登录/登录过期");
        }

        releaseRecordService.addReleaseRecord(postsType, action);

        return JsonResult.ok("记录发布数据成功");
    }


}