package com.xyzs.springbootwxsd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
