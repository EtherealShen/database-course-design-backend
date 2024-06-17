package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @param code    验证码
     * @return 新用户 id
     */
    Long userRegister(String userAccount, String userPassword, String checkPassword, String code);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

}
