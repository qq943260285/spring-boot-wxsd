package com.xyzs.springbootwxsd.repository;

import com.xyzs.springbootwxsd.dataobj.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    /**
     * 添加接口自动实现（in查询）
     * SELECT
     productcat0_.category_id AS category1_0_,
     productcat0_.category_name AS category2_0_,
     productcat0_.category_type AS category3_0_,
     productcat0_.create_time AS create_t4_0_,
     productcat0_.update_time AS update_t5_0_
     FROM
     product_category productcat0_
     WHERE
     productcat0_.category_type IN (2, 4, 6)
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}

