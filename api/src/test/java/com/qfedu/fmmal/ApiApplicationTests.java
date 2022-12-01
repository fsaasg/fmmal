package com.qfedu.fmmal;

import com.qfedu.fmmal.dao.CategoryMapper;
import com.qfedu.fmmal.dao.ProductMapper;
import com.qfedu.fmmal.entity.CategoryVO;
import com.qfedu.fmmal.entity.ProductVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
class ApiApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    void contextLoads() {
        List<CategoryVO> categoryVOS = categoryMapper.selectAllCategories2(0);
        for (CategoryVO c1: categoryVOS) {
            System.out.println(c1);
            for (CategoryVO c2: c1.getCategories()) {
                System.out.println("\t" + c2);
                for (CategoryVO c3: c2.getCategories()) {
                    System.out.println("\t" + c3);
                }
            }
        }

    }

    @Test
    public void testRecommand() {

        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        for (ProductVO p: productVOS) {
            System.out.println(p);
        }
    }

    @Test
    public void testSelectFirstLevelCategory() {
        List<CategoryVO> categoryVOS = categoryMapper.selectFirstLevelCategories();
        for (CategoryVO c: categoryVOS) {
            System.out.println(c);
        }
    }

}
