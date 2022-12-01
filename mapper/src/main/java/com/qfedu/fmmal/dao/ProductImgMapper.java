package com.qfedu.fmmal.dao;

import com.qfedu.fmmal.entity.ProductImg;
import com.qfedu.fmmal.general.GeneralDAO;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImgMapper extends GeneralDAO<ProductImg> {
    // 根据商品id查询当前商品的图片信息（输入一个id，返回多个图片）
    public List<ProductImg> selectProductImgByProductId(int productId);
}
