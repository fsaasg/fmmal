package com.qfedu.fmmal.service;

import com.qfedu.fmmal.vo.ResultVO;

public interface ProductService {

    ResultVO listRecommendProducts();

    ResultVO getProductBasicInfo(String productId);
}
