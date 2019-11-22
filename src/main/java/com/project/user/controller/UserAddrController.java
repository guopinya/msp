package com.project.user.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.common.entity.JsonResult;
import com.project.user.entity.User;
import com.project.user.entity.UserAddr;
import com.project.user.service.IUserAddrService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户收货地址表 前端控制器
 * </p>
 *
 * @author tangmingxuan
 * @since 2019-08-07
 */
@RestController
@RequestMapping("/user/user-addr")
public class UserAddrController {

    @Autowired
    private IUserAddrService userAddrService;

    /**
     * 获取我的地址列表
     *
     * @return
     */
    @GetMapping("/getMyAddressList")
    public JsonResult getMyAddressList() {

        // 用户ID
        String userId = User.getLoginUserId();
        if (StringUtils.isBlank(userId)) {
            return JsonResult.fail(1001, "未登录/登录过期");
        }

        List<UserAddr> addressListIsDefault = userAddrService.getByUserIdAndIsDefaultTrue(userId, Boolean.TRUE);
        List<UserAddr> addressListOthers = userAddrService.getByUserIdAndIsDefaultTrue(userId, Boolean.FALSE);

        if (!addressListIsDefault.isEmpty()) {
            addressListOthers.add(0, addressListIsDefault.get(0));
        }
        return JsonResult.ok("获取用户地址成功", addressListOthers);
    }

    /**
     * 获取单个地址列表
     *
     * @return
     */
    @GetMapping("/getOneAddress")
    public JsonResult getOneAddress(String addrId) {

        UserAddr userAddr = userAddrService.getById(addrId);

        return JsonResult.ok("获取用户地址成功", userAddr);
    }

    /**
     * 新增修改地址
     *
     * @return
     */
    @PostMapping("/addAddress")
    public JsonResult addAddress(UserAddr userAddr) {

        // 用户ID
        String userId = User.getLoginUserId();
        if (StringUtils.isBlank(userId)) {
            return JsonResult.fail(1001, "未登录/登录过期");
        }

        userAddr.setUserId(userId);

        UserAddr oldAddress = userAddrService.getById(userAddr.getAddrId());

        List<UserAddr> addressListIsDefault = userAddrService.getByUserIdAndIsDefaultTrue(userId, Boolean.TRUE);

        UserAddr addressIsDefault = null;
        if (!addressListIsDefault.isEmpty()) {
            addressIsDefault = addressListIsDefault.get(0);
        }

        if (oldAddress == null) {
            //说明这是新增地址

            if (userAddr.getDefault()) {
                //说明新增的是默认地址

                if (addressIsDefault != null) {
                    //将原来的默认地址设为非默认的
                    addressIsDefault.setDefault(Boolean.FALSE);
                    userAddrService.updateById(addressIsDefault);
                }
            }
            //新增这次的地址
            userAddrService.save(userAddr);
        } else {
            //说明这是修改地址

            if (userAddr.getDefault()) {
                //这次新增将原来的默认地址设为非默认的
                if (addressIsDefault != null) {
                    addressIsDefault.setDefault(Boolean.FALSE);
                    userAddrService.updateById(addressIsDefault);
                }
            } else {

            }
            //更新地址
            userAddrService.updateById(userAddr);
        }


        return JsonResult.ok("修改用户地址成功");
    }

    /**
     * 删除用户地址
     *
     * @return
     */
    @DeleteMapping("/deleteAddress")
    public JsonResult deleteAddress(String addrId) {
        // 用户ID
        String userId = User.getLoginUserId();
        if (StringUtils.isBlank(userId)) {
            return JsonResult.fail(1001, "未登录/登录过期");
        }

        UserAddr userAddr = userAddrService.getById(addrId);

        if (userAddr != null) {
            userAddrService.removeById(addrId);
        } else {
            return JsonResult.fail("未找到该地址，删除失败。");
        }

        return JsonResult.ok("删除用户地址成功");
    }

}

