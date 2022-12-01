package com.qfedu.fmmal.controller;

import com.qfedu.fmmal.entity.Users;
import com.qfedu.fmmal.service.UserService;
import com.qfedu.fmmal.vo.ResultVO;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping("/user")
@Api(value = "提供用户的登录和注册接口", tags = "用户管理")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "username", value = "用户登录账号", required = true),
            @ApiImplicitParam(dataType = "string", name = "password", value = "用户登录密码", required = true)
    })
    @GetMapping("/login")
    public ResultVO login(String username, String password) {
        ResultVO resultVO = userService.checkLogin(username, password);
        return resultVO;
    }

//    @ApiOperation("用户注册接口")
//    @RequestMapping(value = "/regist", method = RequestMethod.POST)
//    public ResultVO regist(String username, String password) {
//        ResultVO resultVO = userService.userRegist(username, password);
//        return resultVO;
//    }

    @ApiOperation("用户注册接口")
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ResultVO regist(@RequestBody Users user) {
        System.out.println(user);
        ResultVO resultVO = userService.userRegist(user.getUsername(), user.getPassword());
        return resultVO;
    }
}
