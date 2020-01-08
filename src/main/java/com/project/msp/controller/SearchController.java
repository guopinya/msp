package com.project.msp.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.constants.Constants;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.common.utils.SecurityUtils;
import com.project.msp.entity.Search;
import com.project.msp.service.ISearchService;
import com.project.system.entity.Banner;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("searchController")
@RequestMapping("/msp/search")
public class SearchController extends BaseController {

    @Autowired
    private ISearchService searchService;

    /**
     * 获取用户搜索关键词(当前用户&显示)
     *
     * @param pageEntity 分页实体
     * @param search     用户搜索关键词实体
     * @return JSON结果
     */
    @GetMapping
    public JsonResult getSearch(PageEntity<Search> pageEntity, Search search) {
        search.setIsDisplay(Constants.IS_DISPLAY_NOT);
        search.setInitUser(SecurityUtils.getUserId());
        IPage<Search> page = searchService.pageBySearchCond(pageEntity.getMpPage(), search);
        return JsonResult.ok("获取用户搜索关键词成功", page.getTotal(), page.getRecords());
    }

    /**
     * 添加用户搜索关键词(当前用户&显示)
     *
     * @param search 用户搜索关键词
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addSearch(Search search) {
        if (StringUtils.isNotBlank(search.getSearchValue())) {
            search.setIsDisplay(Constants.IS_DISPLAY_NOT);
            searchService.save(search);
        } else
            return JsonResult.fail("请输入搜索内容");
        return JsonResult.ok("添加用户搜索关键词成功", search);
    }

    /**
     * 清空用户搜索关键词(当前用户&显示的全部置为隐藏)
     *
     * @return 操作结果
     */
    @DeleteMapping()
    public JsonResult deleteMenu() {
        Search search = new Search();
        search.setIsDisplay(Constants.IS_DISPLAY_YES);
        Wrapper<Search> wrapper = new QueryWrapper<Search>().lambda()
                .eq(Search::getIsDisplay, Constants.IS_DISPLAY_NOT)
                .eq(Search::getInitUser, SecurityUtils.getUserId())
                .orderByAsc(Search::getSortNumber);
        searchService.update(search, wrapper);
        return JsonResult.ok("删除用户搜索关键词成功", null);
    }
}

