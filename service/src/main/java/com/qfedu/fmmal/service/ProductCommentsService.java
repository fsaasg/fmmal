package com.qfedu.fmmal.service;

import com.qfedu.fmmal.vo.ResultVO;

public interface ProductCommentsService {

    public ResultVO listCommentsByProductId(String productId, int pageNum, int limit);

    public ResultVO getCommentsCountByProductId(String productId);
}
