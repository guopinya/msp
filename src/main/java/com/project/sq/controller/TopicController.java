package com.project.sq.controller;

import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.sq.entity.Topic;
import com.project.sq.service.ITopicService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 话题控制器
 *
 * @author zhuyifa
 */
@RestController
@RequestMapping(value = "/sq/topics")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class TopicController extends BaseController {

    private final ITopicService topicService;

    /**
     * 获取话题列表
     *
     * @param p 话题对象
     * @return api请求结果
     */
    @GetMapping
    public JsonResult obtain(Topic p) {
        // 通过实体参数获取列表
        List<Topic> list = topicService.list(p);

        // 循环查询子话题列表
        list.forEach(topic -> {
            p.setParentId(topic.getId());
            // 设置子话题列表
            topic.setChildren(topicService.list(p));
        });
        return JsonResult.ok("获取话题列表成功", list);
    }

    /**
     * 获取单个话题
     *
     * @param p 话题ID
     * @return api请求结果
     */
    @GetMapping(value = "/{p}")
    public JsonResult obtain(@PathVariable String p) {
        // 通过话题ID查询
        Topic topic = topicService.getById(p);

        return JsonResult.ok("获取单个话题成功", topic);
    }

    /**
     * 添加话题
     *
     * @param p 话题对象
     * @return api请求结果
     */
    @PostMapping
    public JsonResult addTo(Topic p) {
        // 设置默认值
        p.setDefaultValue();
        // 保存话题
        topicService.save(p);

        return JsonResult.ok("添加话题成功", p);
    }

}
