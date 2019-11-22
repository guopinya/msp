package com.project.user.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.project.user.entity.UserAddr;

/**
 * 用户收货地址表 服务类
 *
 * @author zhuyifa
 * @since 2019-07-02
 */
public interface IUserAddrService extends IService<UserAddr> {

    /**
     * 通过用户ID和默认为true获取
     *
     * @param userId 用户ID
     * @return
     */
    List<UserAddr> getByUserIdAndIsDefaultTrue(String userId, Boolean isDefault);


}
