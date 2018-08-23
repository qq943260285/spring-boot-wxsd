package com.xyzs.springbootwxsd.dto;

import lombok.Data;

/**
 * 购物车
 */
@Data
public class CartDTO {
    /**
     * 商品ID
     */
    private String productId;
    /**
     * 数量
     */
    private Integer productQuantity;
}
