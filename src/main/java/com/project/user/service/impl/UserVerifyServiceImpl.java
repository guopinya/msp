package com.project.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.user.entity.UserVerify;
import com.project.user.mapper.UserVerifyMapper;
import com.project.user.service.IUserVerifyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户认证服务实现
 *
 * @author zhuyifa
 * @since 2019-10-31
 */
@Service
public class UserVerifyServiceImpl extends ServiceImpl<UserVerifyMapper, UserVerify> implements IUserVerifyService {

    @Autowired
    private UserVerifyMapper userVerifyMapper;

    /**
     * 按认证条件分页
     *
     * @param page   分页条件
     * @param verify 认证条件
     * @return 用户认证分页
     */
    @Override
    public IPage<UserVerify> pageByVerifyCond(IPage<UserVerify> page, UserVerify verify) {
        // 真实姓名
        String actualName = verify.getActualName();
        // 联系地址
        String contactAddr = verify.getContactAddr();
        // 详细地址
        String detailedAddr = verify.getDetailedAddr();
        // 身份证号
        String idCardNumber = verify.getIdCardNumber();

        Wrapper<UserVerify> wrapper = new QueryWrapper<UserVerify>().lambda()
                .like(StringUtils.isNotBlank(actualName), UserVerify::getActualName, actualName)
                .like(StringUtils.isNotBlank(contactAddr), UserVerify::getContactAddr, contactAddr)
                .like(StringUtils.isNotBlank(detailedAddr), UserVerify::getDetailedAddr, detailedAddr)
                .like(StringUtils.isNotBlank(idCardNumber), UserVerify::getIdCardNumber, idCardNumber);
        return userVerifyMapper.selectPage(page, wrapper);
    }
}