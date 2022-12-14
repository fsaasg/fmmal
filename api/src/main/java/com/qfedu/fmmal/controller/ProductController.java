package com.qfedu.fmmal.controller;

import com.qfedu.fmmal.service.ProductCommentsService;
import com.qfedu.fmmal.service.ProductService;
import com.qfedu.fmmal.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(value = "提供商品信息相关的接口", tags = "商品管理")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCommentsService productCommentsService;

    @ApiOperation("商品基本信息查询接口")
    @GetMapping("/detail-info/{pid}")
    public ResultVO getProductBasicInfo(@PathVariable("pid") String pid) {
        return productService.getProductBasicInfo(pid);
    }

    @ApiOperation("商品参数信息查询接口")
    @GetMapping("/detail-params/{pid}")
    public ResultVO getProductParams(@PathVariable("pid") String pid) {
        return productService.getProductParamsById(pid);
    }

    @ApiOperation("商品评论信息查询接口")
    @GetMapping("/detail-comments/{pid}")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "当前页码", required = true, example = "0"),
            @ApiImplicitParam(dataType = "int", name = "limit", value = "每页显示条数", required = true, example = "0")
    })
    public ResultVO getProductComments(@PathVariable("pid") String pid, int pageNum, int limit) {
        return productCommentsService.listCommentsByProductId(pid, pageNum, limit);
    }

    @ApiOperation("商品评论统计查询接口")
    @GetMapping("/detail-commentscount/{pid}")
    public ResultVO getProductCommentsCount(@PathVariable("pid") String pid) {
        return productCommentsService.getCommentsCountByProductId(pid);
    }
}
