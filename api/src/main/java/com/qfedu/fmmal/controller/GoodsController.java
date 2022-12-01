package com.qfedu.fmmal.controller;

import com.qfedu.fmmal.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/goods")
@Api(value = "提供首页货物显示所需的接口", tags = "货物管理")
public class GoodsController {

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO addGoods() {
        return null;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResultVO deleteGoods() {
        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResultVO updateGoods() {
        return null;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultVO listGoods() {
        return null;
    }
}
