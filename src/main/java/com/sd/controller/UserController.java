package com.sd.controller;


import cn.hutool.jwt.JWTUtil;
import com.sd.common.MetaData;
import com.sd.common.Response;
import com.sd.common.CodeImage;
import com.sd.common.TabBar;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


@Api(tags = "用户管理接口")
@RestController
@RequestMapping("")
public class UserController {

    @Resource
    private UserService userService;

    private String tokenKey = "Ambrose";

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public Response<User> UserLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse httpServletResponse) {

        String userAccount = userLoginRequest.getAccount();
        String userPassword = userLoginRequest.getPassword();

        Long result = userService.userLogin(userAccount, userPassword);
        if (result == -1) {
            MetaData metaData = new MetaData(402, "账号不存在");
            return new Response<>(200, metaData);
        } else if (result == -2) {
            MetaData metaData = new MetaData(403, "密码错误");
            return new Response<>(200, metaData);
        }
        User user = userService.getById(result);
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("accout",user.getUserAccount());
        tokenMap.put("id",user.getUserId());
        // 生成token
        String token = JWTUtil.createToken(tokenMap,tokenKey.getBytes());
        MetaData metaData = new MetaData(200, "登录成功",token);
        return new Response<>(200, user, metaData);

    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public Response<User> UserRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        String userAccount = userRegisterRequest.getAccount();
        String userPassword = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String code = userRegisterRequest.getCode();

        Long result = userService.userRegister(userAccount, userPassword, checkPassword, code);

        if (result == -1) {
            MetaData metaData = new MetaData(500, "出错，注册失败");
            return new Response<>(200, metaData);
        } else if (result == -2) {
            MetaData metaData = new MetaData(401, "该账号已存在");
            return new Response<>(200, metaData);
        }
        MetaData metaData = new MetaData(200, "注册成功");
        User user = userService.getById(result);
        return new Response<>(200, user, metaData);
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
