package com.qfedu.fmmal.service.impl;

import com.qfedu.fmmal.dao.CategoryMapper;
import com.qfedu.fmmal.entity.CategoryVO;
import com.qfedu.fmmal.service.CategoryService;
import com.qfedu.fmmal.vo.ResStatus;
import com.qfedu.fmmal.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询分类列表（包含三个级别的分类）
     * @return
     */
    @Override
    public ResultVO listCategories() {
        List<CategoryVO> categoryVOS = categoryMapper.selectAllCategories();
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", categoryVOS);
        return resultVO;
    }

    /**
     * 查询一级分类
     * @return
     */
    @Override
    public ResultVO listFirstLevelCategory() {
        List<CategoryVO> categoryVOS = categoryMapper.selectFirstLevelCategories();
        ResultVO resultVO = new ResultVO(ResStatus.OK, "success", categoryVOS);
        return resultVO;
    }
}
