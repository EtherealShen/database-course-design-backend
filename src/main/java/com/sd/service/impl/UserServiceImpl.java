package com.sd.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.sd.mapper.UserMapper;
import com.sd.model.entity.User;
import com.sd.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;


@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final String SALT = "Ambrose";

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public Long userLogin(String account, String password,String code) {
        String key = String.format("%sCode",code);
        if(redisTemplate.opsForValue().get(key).equals(code)){
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account", account);
            User user = userMapper.selectOne(queryWrapper);
            // 账号不存在
            if (user == null) {
                return -1L;
            }
            // 密码错误
            if (!encryptPassword.equals(user.getPassword())) {
                return -2L;
            }
            return user.getId();
        }
        return -3L;
    }

    @Override
    public Long userRegister(String account, String password, String checkPassword) {
        // 查看是否有重复用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        Long count = userMapper.selectCount(queryWrapper);
        // 密码不一样
        if (!password.equals(checkPassword)){
            return -3L;
        }
        if (count > 0) {
            return -2L;
        }
        // 密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 插入用户
        User user = new User();
        user.setAccount(account);
        user.setPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1L;
        }
        return user.getId();
    }
}
