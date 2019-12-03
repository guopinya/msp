package com.project.msp.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.project.common.controller.BaseController;
import com.project.common.entity.JsonResult;
import com.project.common.entity.PageEntity;
import com.project.msp.entity.Attr;
import com.project.msp.service.IAttrService;
import com.project.system.entity.Area;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("attrController")
@RequestMapping("/admin/msp/attr")
public class AttrController extends BaseController {

    @Autowired
    private IAttrService attrService;

    /**
     * 获取分类
     *
     * @param pageEntity 分页实体
     * @param attr       分类实体
     * @return JSON结果
     */
    @GetMapping("/type/{type}")
    public JsonResult getAttr(Attr attr, @PathVariable String type) {
        attr.setType(type);
        String name = attr.getAttrName();

        Wrapper<Attr> wrapper = new QueryWrapper<Attr>().lambda()
                .orderByAsc(Attr::getSortNumber)
                .eq(StringUtils.isNotBlank(type), Attr::getType, type)
                .like(StringUtils.isNotBlank(name), Attr::getAttrName, name);
        return JsonResult.ok("获取菜单成功", attrService.list(wrapper));
    }

    /**
     * 添加分类
     *
     * @param attr      分类
     * @param imageFile 图片文件
     * @return 操作结果
     */
    @PostMapping
    public JsonResult addAttr(Attr attr, MultipartFile imageFile) {
        attr.setImage(upload(imageFile));
        attrService.save(attr);
        return JsonResult.ok("添加分类成功", attr);
    }

    /**
     * 修改分类
     *
     * @param attr      分类
     * @param imageFile 图片文件
     * @return 操作结果
     */
    @PutMapping
    public JsonResult modifyAttr(Attr attr, MultipartFile imageFile) {
        attr.setImage(upload(imageFile));
        attrService.updateById(attr);
        return JsonResult.ok("修改分类成功", attr);
    }

    /**
     * 删除分类
     *
     * @param attrId 分类ID
     * @return 操作结果
     */
    @DeleteMapping("/{attrId}")
    public JsonResult deleteMenu(@PathVariable String attrId) {
        attrService.removeById(attrId);
        return JsonResult.ok("删除分类成功", null);
    }
}

