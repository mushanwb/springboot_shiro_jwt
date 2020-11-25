package com.github.mushanwb.controller;

import com.github.mushanwb.entity.ResultResponse;
import com.github.mushanwb.entity.User;
import com.github.mushanwb.jwt.JWTUtil;
import com.github.mushanwb.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {

    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登录，输入账号密码后，将登陆成功，返回 token
     * @param param 登录参数
     *              {
     *              username 账号
     *              password 密码
     *              }
     * @return 登录成功后返回用户信息和 token
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultResponse<Map<String, String>> login(@RequestBody Map<String, String> param) {
        String username = param.get("username");
        String password = param.get("password");

        User user = userService.getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            Map<String, String> result = new HashMap<>();
            String token = JWTUtil.sign(username, password);
            result.put("token", token);
            return ResultResponse.result(200, "登录成功", result);
        } else {
            return ResultResponse.result(401, "账号或密码错误", null);
        }
    }

    /**
     * 这个接口不用登录就可以访问
     * 但是登录的用户 和 没登录的用户 返回的结果不一样
     * @return 返回不同的数据
     */
    @GetMapping("/article")
    @ResponseBody
    public ResultResponse<String> article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResultResponse.result(200, "你已登录，是用户", null);
        } else {
            return ResultResponse.result(200, "你没登录，是游客", null);
        }
    }

    @GetMapping("/require_auth")
    @ResponseBody
    public ResultResponse<String> requireAuth() {
        return ResultResponse.result(200, "你正在登录状态", null);
    }


}
