package com.qfedu.fmmal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 首页类别商品推荐
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryVO2 {

    private Integer categoryId;
    private String categoryName;
    private Integer categoryLevel;
    private Integer parentId;
    private String categoryIcon;
    private String categorySlogan;
    private String categoryPic;
    private String categoryBgColor;

    private List<ProductVO> products;
}
