package com.sd.controller;


import com.mysql.cj.util.StringUtils;
import com.sd.common.BaseResponse;
import com.sd.common.ErrorCode;
import com.sd.common.ResultsUtils;
import com.sd.exception.BusinessException;
import com.sd.model.domain.UserLoginRequest;
import com.sd.model.domain.UserRegisterRequest;
import com.sd.model.entity.User;
import com.sd.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping()
    public String test(){
        return "test";
    }

    @PostMapping("/login")
    public BaseResponse<User> UserLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {

        String userAccount = userLoginRequest.getAccount();
        String userPassword = userLoginRequest.getPassword();

        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultsUtils.success(user);
    }
    @PostMapping("/register")
    public BaseResponse<Integer> UserRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        String userAccount = userRegisterRequest.getAccount();
        String userPassword = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String code = userRegisterRequest.getCode();

        int result = userService.userRegister(userAccount, userPassword, checkPassword, code);
        return ResultsUtils.success(result);
    }


}
