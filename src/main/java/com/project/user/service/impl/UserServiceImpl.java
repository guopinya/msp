package com.project.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.common.exception.BusinessException;
import com.project.common.utils.HttpUtils;
import com.project.user.entity.User;
import com.project.user.entity.UserAuth;
import com.project.user.mapper.UserAuthMapper;
import com.project.user.mapper.UserMapper;
import com.project.user.service.IUserService;
import io.rong.RongCloud;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户表 服务实现类
 *
 * @author zhuyifa
 * @since 2019-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Value("${server.domain}")
    private String serverDomain;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 通过手机号获取
     *
     * @param mobile 手机号
     * @return 用户
     */
    @Override
    public User getByMobile(String mobile) {
        User u = new User();
        u.setMobile(mobile);

        return userMapper.selectOne(new QueryWrapper<>(u));
    }

    /**
     * 查询所有
     *
     * @see Wrappers#emptyWrapper()
     */
    @Override
    public List<User> list(Wrapper<User> user) {
        return userMapper.list(user);
    }

    /**
     * 按用户条件分页
     *
     * @param page 分页条件
     * @param user 用户条件
     * @return 分页对象
     */
    @Override
    public IPage<User> pageByUserCond(IPage<User> page, User user) {
        // 账号类型
        String userType = user.getUserType();
        // 用户名
        String username = user.getUsername();
        // 手机号码
        String mobile = user.getMobile();
        // 用户状态
        String status = user.getStatus();

        Wrapper<User> wrapper = new QueryWrapper<User>().lambda()
                .eq(StringUtils.isNotBlank(userType), User::getUserType, userType)
                .like(StringUtils.isNotBlank(username), User::getUsername, username)
                .like(StringUtils.isNotBlank(mobile), User::getMobile, mobile)
                .eq(StringUtils.isNotBlank(status), User::getStatus, status);
        //return userMapper.selectPage(page, wrapper);
        return userMapper.pageByUserCond(page, wrapper);
    }

    /**
     * 注册用户
     *
     * @param user     用户
     * @param password 密码
     * @return 用户
     */
    @Override
    public User registerUser(User user, String password) {
        transactionTemplate.execute(a -> {
            // 用户ID
            String userId = IdWorker.getIdStr();

            // 新增用户
            user.setUserId(userId);
            user.setRyToken(getRyToken(user));
            user.setCreateTime(LocalDateTime.now());
            userMapper.insert(user);

            return null;
        });

        return user;
    }

    private String getRyToken(User user) {
        RongCloud rongCloud = RongCloud.getInstance("25wehl3u2gyzw", "MI20WdWCrw");

        String avatarPath = user.getAvatarPath();
        if (!avatarPath.startsWith("http://") && !avatarPath.startsWith("https://")) {
            avatarPath = serverDomain + "/api" + avatarPath;
        }
        //注册用户，生成用户在融云的唯一身份标识 Token
        UserModel userModel = new UserModel()
                .setId(user.getUserId())
                .setName(user.getUsername())
                .setPortrait(avatarPath);
        TokenResult result;
        try {
            result = rongCloud.user.register(userModel);
        } catch (Exception e) {
            throw new BusinessException("注册失败");
        }
        System.out.println("getToken:  " + result.toString());
        return result.getToken();
    }

    /**
     * 通过用户ID获取密码
     *
     * @param userId 用户ID
     * @return 密码
     */
    @Override
    public String getPasswordByUserId(String userId) {
        UserAuth ua = new UserAuth();
        ua.setUserId(userId);
        ua.setAuthType(UserAuth.TYPE_PASSWORD);
        ua = userAuthMapper.selectOne(new QueryWrapper<>(ua));

        if (ua != null) {
            return ua.getAuthSecret();
        }
        return null;
    }

    /**
     * 修改密码
     *
     * @param userId   用户ID
     * @param password 密码
     */
    @Override
    public void modifyPassword(String userId, String password) {
        UserAuth ua = new UserAuth();
        ua.setUserId(userId);
        ua.setAuthType(UserAuth.TYPE_PASSWORD);
        ua = userAuthMapper.selectOne(new QueryWrapper<>(ua));

        if (ua != null) {
            ua.setAuthSecret(password);
            userAuthMapper.updateById(ua);
        }
    }

    /**
     * 通过用户ID获取
     *
     * @param userId 用户ID
     * @return 用户
     */
    @Override
    public User getByUserId(String userId) {
        return userMapper.selectById(userId);
    }

    /**
     * 翻页查询
     *
     * @param page 分页条件
     * @param word 关键字
     * @return 用户分页
     */
//    @Override
//    public IPage searchUserByWord(IPage page, String word) {
//        return userMapper.selectUserByWord(page, word);
//    }

    /**
     * 判断能否发布
     */
    @Override
    public Boolean isPutAllow() {
        Object attr = HttpUtils.getSessionAttribute(User.LABEL_USER_LAST_PUT_TIME);
        if (attr == null) {
            savePutTime();
            return Boolean.TRUE;
        }

        Long lastPutTimestamp = (Long) attr;

        if ((System.currentTimeMillis() - lastPutTimestamp) > 10 * 1000) {
            savePutTime();
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 保存发布时间
     */
    @Override
    public void savePutTime() {
        HttpUtils.setSessionAttribute(User.LABEL_USER_LAST_PUT_TIME, System.currentTimeMillis());
    }

    /**
     *
     */
    @Override
    public Boolean isDuplicateName(String userId, String name) {

        List<User> list = userMapper.selectUserByName(name);

        if (list.size() > 1) {
            return Boolean.TRUE;
        } else if (list.size() == 1) {
            User user = list.get(0);
            if (userId.equals(user.getUserId())) {
                return Boolean.FALSE;
            } else {
                return Boolean.TRUE;
            }

        }

        return Boolean.FALSE;
    }
}
