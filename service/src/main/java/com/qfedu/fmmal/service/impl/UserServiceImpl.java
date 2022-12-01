package com.qfedu.fmmal.service.impl;

import com.qfedu.fmmal.dao.UsersMapper;
import com.qfedu.fmmal.entity.Users;
import com.qfedu.fmmal.service.UserService;
import com.qfedu.fmmal.utils.Base64Utils;
import com.qfedu.fmmal.utils.MD5Utils;
import com.qfedu.fmmal.vo.ResStatus;
import com.qfedu.fmmal.vo.ResultVO;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
//@Scope(description = "singleton")
public class UserServiceImpl implements UserService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    @Transactional
    public ResultVO userRegist(String name, String pwd) {
        synchronized (this) {
            // 1. 根据用户注册信息查询，这个用户是否已经被注册了
            Example example = new Example(Users.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("username", name);
            List<Users> users = usersMapper.selectByExample(example);
//            System.out.println(users);
//            System.out.println(users.size());

            // 2. 如果没有被注册则进行保存操作
            if (users.size() == 0) {  // 没有查询到同名的人
                String md5Pwd = MD5Utils.md5(pwd);
                // 在java实例化一个对象
                Users user = new Users();
                user.setUsername(name);
                user.setPassword(md5Pwd);
                user.setUserImg("img/default.png");
                user.setUserRegtime(new Date());
                user.setUserModtime(new Date());
                // 要把对象保存在数据库中
                int i = usersMapper.insertUseGeneratedKeys(user);
                if (i > 0) {
                    return new ResultVO(ResStatus.OK, "注册成功", user);
                } else {
                    return new ResultVO(ResStatus.NO, "注册失败", null);
                }
            } else {
                return new ResultVO(ResStatus.NO, "what", null);
                //return new ResultVO(1234, "用户已经被注册了！！！", null);
            }
        }
    }

    @Override
    public ResultVO checkLogin(String name, String pwd) {

        // 1. 根据账号查询用户信息
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", name);
        List<Users> users = usersMapper.selectByExample(example);

        // 2. 判断
        if (users.size() == 0) {
            // 用户名不存在
            return new ResultVO(ResStatus.NO, "用户名不存在", null);
        } else {
            // 3. 对输入的密码pwd进行加密
            String md5Pwd = MD5Utils.md5(pwd);
            // 使用加密后的密码和suer中的密码进行匹配
            if (users.get(0).getPassword().equals(md5Pwd)) {
                // 验证成功
                // 如果登录验证成功，则需要生成令牌token。
                // 【token版本1】
//                String token = Base64Utils.encode(name + "dxy123456");
                // 【token版本2】
                JwtBuilder builder = Jwts.builder();
                HashMap<String, Object> map = new HashMap<>();
                map.put("key1", "value1");
                map.put("key2", "value2");

                String token = builder.setSubject(name)  // 主体,就是token中携带的数据
                        .setIssuedAt(new Date())  // 设置token的生成时间
                        .setId(users.get(0).getUserId() + "")  // 设置用户id为token id
                        .setClaims(map)  // map中可以存放用户的角色权限信息
                        .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))  // 设置过期时间
                        .signWith(SignatureAlgorithm.HS256, "dxy123456")  // 设置加密方式和加密密码
                        .compact();
                return new ResultVO(ResStatus.OK, token, users.get(0));  // 返回给前端
            } else {
                // 密码错误
                return new ResultVO(ResStatus.NO, "密码错误", null);
            }
        }
    }
}


