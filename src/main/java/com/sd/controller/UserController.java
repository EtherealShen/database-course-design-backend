package com.sd.controller;


import cn.hutool.jwt.JWTUtil;
import com.sd.common.Response;
import com.sd.common.CodeImage;
import com.sd.model.dto.UserLoginRequest;
import com.sd.model.dto.UserRegisterRequest;
import com.sd.model.entity.User;
import com.sd.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    public Response UserLogin(@RequestBody UserLoginRequest userLoginRequest) {

        String account = userLoginRequest.getAccount();
        String password = userLoginRequest.getPassword();
        String code = userLoginRequest.getCode();

        Long result = userService.userLogin(account, password,code);
        if (result == -1) {
            return Response.error("用户不存在");
        } else if (result == -2) {
            return Response.error("密码错误");
        } else if (result == -3) {
            return Response.error("验证码错误");
        }
        User user = userService.getById(result);
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("account", user.getAccount());
        tokenMap.put("id", user.getId());

        String token = JWTUtil.createToken(tokenMap, tokenKey.getBytes());
        return Response.success(user, token);
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public Response UserRegister(@RequestBody UserRegisterRequest userRegisterRequest) {

        String account = userRegisterRequest.getAccount();
        String password = userRegisterRequest.getPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();

        Long result = userService.userRegister(account, password, checkPassword);
        if (result == -1) {
            return Response.error("注册失败");
        } else if (result == -2) {
            return Response.error("该账号已存在");
        } else if (result == -3) {
            return Response.error("密码不一致");
        }
        User user = userService.getById(result);
        return Response.success(user);
    }

    @ApiOperation("获取验证码接口")
    @GetMapping("/getVerifiCodeImage")
    public void getCodeImage(HttpServletResponse response) throws IOException {
        BufferedImage codeImage = CodeImage.getVerifiCodeImage();
        String code = new String(CodeImage.getVerifiCode());
        String key = String.format("%sCode",code);
        redisTemplate.opsForValue().set(key, code);
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        ImageIO.write(codeImage, "JPG", response.getOutputStream());
    }
}
