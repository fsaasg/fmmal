package com.qfedu.fmmal.service.impl;

import com.qfedu.fmmal.dao.ProductImgMapper;
import com.qfedu.fmmal.dao.ProductMapper;
import com.qfedu.fmmal.entity.Product;
import com.qfedu.fmmal.entity.ProductImg;
import com.qfedu.fmmal.entity.ProductVO;
import com.qfedu.fmmal.service.ProductService;
import com.qfedu.fmmal.vo.ResStatus;
import com.qfedu.fmmal.vo.ResultVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImgMapper productImgMapper;

    @Override
    public ResultVO listRecommendProducts() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();

        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", productVOS);
        return resultVO;
    }

    @Override
    public ResultVO getProductBasicInfo(String productId) {
        // 1. 商品信息（tkmapper的条件查询）example -> criteria : set
        Example example = new Example(Product.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        criteria.andEqualTo("productStatus", 1);

        // 查询结果
        List<Product> products = productMapper.selectByExample(example);
        if (products.size() > 0) {
            // 2. 继续查询商品图片
            Example example1 = new Example(ProductImg.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("itemId", productId);
            List<ProductImg> productImgs = productImgMapper.selectByExample(example1);
            // 3. 商品套餐


        } else {
            return new ResultVO(ResStatus.NO, "查询的商品不存在", null);
        }


        return null;
    }
}
