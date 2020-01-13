package com.project.sq.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.common.utils.SecurityUtils;
import com.project.sq.entity.Post;
import com.project.sq.entity.PostMaster;
import com.project.sq.service.IPostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 帖子控制器
 *
 * @author zhuyifa
 */
@RestController
@RequestMapping(value = "/sq/posts")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class PostController extends BaseController {

    private final IPostService postService;

    /**
     * 获取帖子列表
     *
     * @param p  分页对象
     * @param p2 帖子对象
     * @return api请求结果
     */
    @GetMapping
    public JsonResult obtain(PageEntity<Post> p, Post p2) {
        // 通过实体参数分页查询
        IPage<Post> page = postService.page(p.getMpPage(), p2);

        // 总帖子数
        long total = page.getTotal();
        // 当前帖子列表
        List<Post> records = page.getRecords();

        return JsonResult.ok("获取帖子列表成功", total, records);
    }

    /**
     * 获取单个帖子
     *
     * @param p 帖子ID
     * @return api请求结果
     */
    @GetMapping(value = "/{p}")
    public JsonResult obtain(@PathVariable String p) {
        // 通过帖子ID查询
        Post post = postService.getById(p);

        return JsonResult.ok("获取单个帖子成功", post);
    }

    /**
     * 添加帖子
     *
     * @param p 帖子对象
     * @return api请求结果
     */
    @PostMapping
    public JsonResult addTo(Post p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();

        // 设置默认值
        p.setDefaultValue();
        // 设置用户ID
        p.setUserId(userId);
        // 保存帖子
        postService.save(p);

        return JsonResult.ok("添加帖子成功", null);
    }

    /**
     * 获取浏览/点赞/评论列表
     *
     * @param p  分页对象
     * @param p2 帖子达人
     * @return api请求结果
     */
    @GetMapping(value = "/masters")
    public JsonResult obtainMaster(PageEntity<PostMaster> p, PostMaster p2) {
        // 查询帖子达人分页
        IPage<PostMaster> page = postService.pageMaster(p.getMpPage(), p2);

        // 获取操作名称
        String actName = p2.obtainActName();
        // 帖子达人总数
        long total = page.getTotal();
        // 帖子达人列表
        List<PostMaster> records = page.getRecords();

        return JsonResult.ok(String.format("获取%s列表成功", actName), total, records);
    }

    /**
     * 添加浏览/点赞/评论
     *
     * @param p 帖子达人
     * @return api请求结果
     */
    @PostMapping(value = "/masters")
    public JsonResult addToMaster(PostMaster p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();
        // 获取操作名称
        String actName = p.obtainActName();

        // 设置达人ID（用户ID）
        p.setMasterId(userId);
        // 保存帖子达人
        postService.saveMaster(p);

        return JsonResult.ok(String.format("添加%s成功", actName), null);
    }

    /**
     * 删除浏览/点赞/评论
     *
     * @param p 达人对象
     * @return api请求结果
     */
    @DeleteMapping(value = "/masters")
    public JsonResult deleteMaster(PostMaster p) {
        // 登录用户
        String userId = SecurityUtils.getUserId();
        // 获取操作名称
        String actName = p.obtainActName();

        // 设置达人ID（用户ID）
        p.setMasterId(userId);
        // 删除帖子达人
        postService.removeMaster(p);

        return JsonResult.ok(String.format("删除%s成功", actName), null);
    }

}
