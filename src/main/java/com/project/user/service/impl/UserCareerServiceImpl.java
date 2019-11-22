package com.project.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.manager.entity.Career;
import com.project.manager.mapper.CareerMapper;
import com.project.user.entity.UserCareer;
import com.project.user.mapper.UserCareerMapper;
import com.project.user.service.IUserCareerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户职业表 服务实现类
 *
 * @author tangmingxuan
 * @since 2019-08-02
 */
@Service
public class UserCareerServiceImpl extends ServiceImpl<UserCareerMapper, UserCareer> implements IUserCareerService {

    @Autowired
    private CareerMapper careerMapper;
    @Autowired
    private UserCareerMapper userCareerMapper;

    /**
     * 通过用户ID获取
     *
     * @param userId 用户ID
     * @return 用户职业
     */
    @Override
    public UserCareer getByUserId(String userId) {
        UserCareer userCareer = userCareerMapper.selectById(userId);
        if (userCareer == null) {
            return null;
        }

        // 第一职业
        String careerFirstId = userCareer.getCareerFirstId();
        if (careerFirstId != null) {
            Career career = careerMapper.selectById(careerFirstId);
            if (career != null) {
                userCareer.setCareerFirstName(career.getCareerName());
            }
        }

        // 第二职业
        String careerSecondId = userCareer.getCareerSecondId();
        if (careerSecondId != null) {
            Career career = careerMapper.selectById(careerSecondId);
            if (career != null) {
                userCareer.setCareerSecondName(career.getCareerName());
            }
        }

        // 第三职业
        String careerThirdId = userCareer.getCareerThirdId();
        if (careerThirdId != null) {
            Career career = careerMapper.selectById(careerThirdId);
            if (career != null) {
                userCareer.setCareerThirdName(career.getCareerName());
            }
        }

        return userCareer;
    }
}