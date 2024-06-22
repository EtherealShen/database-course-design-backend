package com.sd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sd.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public interface UserService extends IService<User> {

    Long userRegister(String userAccount, String userPassword, String checkPassword);

    Long userLogin(String userAccount, String userPassword,String code);

}
