package com.sd.controller;


import com.sd.common.BaseResponse;
import com.sd.common.CodeImage;
import com.sd.common.ResultsUtils;
import com.sd.common.StatusCode;
import com.sd.model.domain.UserLoginRequest;
import com.sd.model.domain.UserRegisterRequest;
import com.sd.model.entity.User;
import com.sd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public BaseResponse<User> UserLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {

        String userAccount = userLoginRequest.getAccount();
        String userPassword = userLoginRequest.getPassword();

        User user = userService.userLogin(userAccount, userPassword, request);
        if(user == null){
            return ResultsUtils.failure(StatusCode.NULL_ERROR);
        }
        return ResultsUtils.success(StatusCode.SUCCESS,user);
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public BaseResponse<Long> UserRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        String userAccount = userRegisterRequest.getAccount();
        String userPassword = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String code = userRegisterRequest.getCode();

        Long result = userService.userRegister(userAccount, userPassword, checkPassword, code);

        if(result==-1){
            return ResultsUtils.failure(StatusCode.FAILURE,"注册失败");
        } else if (result==-2) {
            return ResultsUtils.failure(StatusCode.FAILURE,"用户已存在");
        }
        return ResultsUtils.success(StatusCode.SUCCESS,result);
    }

    @ApiOperation("获取验证码接口")
    @GetMapping("/getVerifiCodeImage")
    public void getCodeImage(HttpSession session, HttpServletResponse response) throws IOException {
        BufferedImage codeImage = CodeImage.getVerifiCodeImage();
        String code = new String(CodeImage.getVerifiCode());
        redisTemplate.opsForValue().set("verifiCode", code);
        redisTemplate.expire("verifiCode", 60, TimeUnit.SECONDS);
        session.setAttribute("code", code);
        ImageIO.write(codeImage, "JPG", response.getOutputStream());

    }
}
