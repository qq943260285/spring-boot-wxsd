package com.xyzs.springbootwxsd.service;


import com.xyzs.springbootwxsd.dataobj.ProductCategory;

import java.util.List;

/**
 * 类目
 */
public interface CategoryService {
    /**
     * 查询一条类目
     *
     * @param CategoryId
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 查询所有
     *
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 根据type查询
     *
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 新增
     *
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
