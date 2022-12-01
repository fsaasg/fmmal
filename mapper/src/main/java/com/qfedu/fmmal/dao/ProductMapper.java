package com.qfedu.fmmal.dao;

import com.qfedu.fmmal.entity.Product;
import com.qfedu.fmmal.entity.ProductVO;
import com.qfedu.fmmal.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends GeneralDAO<Product> {

    public List<ProductVO> selectRecommendProducts();

    // 每一个函数都是一个单独的SQL查询语句

    /**
     * 查询指定一级类别下销量最高的6个商品
     * @param cid
     * @return
     */
    public List<ProductVO> selectTop6Category(int cid);
}
