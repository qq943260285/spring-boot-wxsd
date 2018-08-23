package com.xyzs.springbootwxsd.service;

import com.xyzs.springbootwxsd.dataobj.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    /**
     * 查询一件商品信息
     *
     * @param productId
     * @return
     */
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询所有商品（分页）
     *
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 更新商品
     *
     * @param productInfo
     * @return
     */
    ProductInfo save(ProductInfo productInfo);


//    void increaseStock(List<>)
}
