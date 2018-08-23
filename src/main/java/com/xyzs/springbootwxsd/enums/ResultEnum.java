package com.xyzs.springbootwxsd.enums;

import lombok.Getter;

/**
 * 返回前端提示语
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "库存不正确");
    /**
     * 状态码
     */
    private Integer cade;
    /**
     * 信息
     */
    private String message;

    ResultEnum(Integer cade, String message) {
        this.cade = cade;
        this.message = message;
    }
}
