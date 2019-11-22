package com.project.manager.controller;


import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.manager.entity.Career;
import com.project.manager.service.ICareerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 职业接口
 *
 * @author tangmingxuan
 * @since 2019-07-18
 */
@RestController
@RequestMapping("/manager/career")
public class CareerController extends BaseController {

    @Autowired
    private ICareerService careerService;

    /**
     * 获取所有职业
     *
     * @param
     * @param
     * @param
     * @return
     */
    @GetMapping("/getCareerList")
    public JsonResult getCareerList() {

        List<Career> pageList = careerService.getAllCareer();

        return JsonResult.ok("获取职业列表成功", pageList);
    }

    /**
     * 增加职业
     *
     * @param
     * @param
     * @param
     * @return
     */
    @PostMapping("/addCareer")
    public JsonResult addCareer(Career career) {

        careerService.save(career);

        return JsonResult.ok("新增职业成功");
    }


}
