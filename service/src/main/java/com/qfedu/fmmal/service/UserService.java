package com.qfedu.fmmal.service;

import com.qfedu.fmmal.vo.ResultVO;

public interface UserService {

    // 用户注册
    public ResultVO userRegist(String name, String pwd);

    // 用户登录
    public ResultVO checkLogin(String name, String pwd);
}
