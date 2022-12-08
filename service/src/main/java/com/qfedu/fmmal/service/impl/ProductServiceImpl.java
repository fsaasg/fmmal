package com.qfedu.fmmal.service.impl;

import com.qfedu.fmmal.dao.ProductImgMapper;
import com.qfedu.fmmal.dao.ProductMapper;
import com.qfedu.fmmal.dao.ProductParamsMapper;
import com.qfedu.fmmal.dao.ProductSkuMapper;
import com.qfedu.fmmal.entity.*;
import com.qfedu.fmmal.service.ProductService;
import com.qfedu.fmmal.vo.ResStatus;
import com.qfedu.fmmal.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductImgMapper productImgMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductParamsMapper productParamsMapper;

    @Override
    public ResultVO listRecommendProducts() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();

        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", productVOS);
        return resultVO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
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
            // 2. 只有商品信息后继续查询商品图片
            Example example1 = new Example(ProductImg.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("itemId", productId);
            List<ProductImg> productImgs = productImgMapper.selectByExample(example1);

            // 3. 商品套餐
            Example example2 = new Example(ProductSku.class);
            Example.Criteria criteria2 = example2.createCriteria();
            criteria2.andEqualTo("productId", productId);
            criteria2.andEqualTo("status", 1);
            List<ProductSku> productSkus = productSkuMapper.selectByExample(example2);

            HashMap<String, Object> basicInfo = new HashMap<>();
            basicInfo.put("product", products.get(0));
            basicInfo.put("productImgs", productImgs);
            basicInfo.put("productSkus", productSkus);
            return new ResultVO(ResStatus.OK, "success", basicInfo);


        } else {
            return new ResultVO(ResStatus.NO, "查询的商品不存在", null);
        }
    }

    @Override
    public ResultVO getProductParamsById(String productId) {
        // 构造Example，输入的是要查询表的【映射类】
        Example example = new Example(ProductParams.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        List<ProductParams> productParams = productParamsMapper.selectByExample(example);

        if (productParams.size() > 0) {
            return new ResultVO(ResStatus.OK, "success", productParams.get(0));
        } else {
            return new ResultVO(ResStatus.NO, "此商品可能为三无产品", null);
        }

    }
}
