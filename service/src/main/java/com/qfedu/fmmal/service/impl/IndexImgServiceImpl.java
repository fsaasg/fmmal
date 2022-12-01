package com.qfedu.fmmal.service.impl;

import com.qfedu.fmmal.dao.IndexImgMapper;
import com.qfedu.fmmal.entity.IndexImg;
import com.qfedu.fmmal.service.IndexImgService;
import com.qfedu.fmmal.vo.ResStatus;
import com.qfedu.fmmal.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexImgServiceImpl implements IndexImgService {

    @Autowired
    private IndexImgMapper indexImgMapper;  // 这个红线不代表错误

    @Override
    public ResultVO listIndexImgs() {
        List<IndexImg> indexImgs = indexImgMapper.listIndexImgs();
        if (indexImgs.size() == 0) {
            return new ResultVO(ResStatus.NO, "fail", null);
        } else {
            return new ResultVO(ResStatus.OK, "success", indexImgs);
        }
    }
}
