package com.project.msp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.msp.entity.Search;
import com.project.msp.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("searchController")
@RequestMapping("/admin/msp/search")
public class SearchController extends BaseController {

    @Autowired
    private ISearchService searchService;

    /**
     * 获取用户搜索关键词
     *
     * @param pageEntity 分页实体
     * @param search     用户搜索关键词实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getSearch(PageEntity<Search> pageEntity, Search search) {
        IPage<Search> page = searchService.pageBySearchCond(pageEntity.getMpPage(), search);
        return JsonResult.ok("获取用户搜索关键词成功", page.getTotal(), page.getRecords());
    }

    /**
     * 添加用户搜索关键词
     *
     * @param search 用户搜索关键词
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addSearch(Search search) {
        searchService.save(search);
        return JsonResult.ok("添加用户搜索关键词成功", search);
    }

    /**
     * 修改用户搜索关键词
     *
     * @param search 用户搜索关键词
     * @return 操作结果
     */
    @PutMapping
    public JsonResult modifySearch(Search search) {
        searchService.updateById(search);
        return JsonResult.ok("修改用户搜索关键词成功", search);
    }

    /**
     * 删除用户搜索关键词
     *
     * @param searchId 用户搜索关键词ID
     * @return 操作结果
     */
    @DeleteMapping("/{searchId}")
    public JsonResult deleteMenu(@PathVariable String searchId) {
        searchService.removeById(searchId);
        return JsonResult.ok("删除用户搜索关键词成功", null);
    }
}

