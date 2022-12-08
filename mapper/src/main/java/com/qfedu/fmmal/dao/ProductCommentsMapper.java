package com.qfedu.fmmal.dao;

import com.qfedu.fmmal.entity.ProductComments;
import com.qfedu.fmmal.entity.ProductCommentsVO;
import com.qfedu.fmmal.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentsMapper extends GeneralDAO<ProductComments> {

    /**
     * 根据商品id分页查询评论信息
     * @param productId 商品id
     * @param start 起始索引
     * @param limit 查询条数
     * @return
     */
    public List<ProductCommentsVO> selecetCommentsByProductId(@Param("productId") String productId,
                                                              @Param("start") int start,
                                                              @Param("limit") int limit);
}