package com.qfedu.fmmal.controller;

import com.qfedu.fmmal.utils.Base64Utils;
import com.qfedu.fmmal.vo.ResStatus;
import com.qfedu.fmmal.vo.ResultVO;
import io.jsonwebtoken.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shopcart")
@CrossOrigin
@Api(value = "提供购物车业务相关接口", tags = "购物车管理")
public class ShopcartController {

    @GetMapping("/list")
    @ApiImplicitParam(dataType = "string", name = "token", value = "授权令牌", required = true)
    public ResultVO listCarts(String token) {
        // 1. 获取token
        // 2. 校验token
        if (token == null) {
            return new ResultVO(ResStatus.NO, "success", null);
        } else {
            // 【验证token版本1】
//            String decode = Base64Utils.decode(token);
//            if (decode.endsWith("dxy123456")) {
//                // token校验成功
//                return new ResultVO(ResStatus.OK, "success", null);
//            } else {
//                return new ResultVO(ResStatus.NO, "登录过期，请重新登录", null);
//            }

            // 【验证token版本2】
            try {
                JwtParser parser = Jwts.parser();
                parser.setSigningKey("dxy123456");


                // 如果tokne 正确（密码正确，有效期内）则正常执行，否则抛出异常
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);

                Claims body = claimsJws.getBody();  // 获取token中用户数据
                String subject = body.getSubject();  // 获取生成token设置的subject
                String key1 = body.get("key1", String.class);  // 获取生成token时存储的claim的map中的数据。

                return new ResultVO(ResStatus.OK, "success", null);
            } catch (ExpiredJwtException e) {
                return new ResultVO(ResStatus.NO, "登录过期，请重新登录！", null);
            } catch (UnsupportedJwtException e) {
                return new ResultVO(ResStatus.NO, "Token不合法，请自重！", null);
            } catch (Exception e) {
                return new ResultVO(ResStatus.NO, "请重新登录！", null);
            }
            // 难道所有的Controller都要写这个验证方法吗？代码请移步【拦截器】
        }
    }
}
