package com.xyzs.springbootwxsd.enums;

import lombok.Getter;

/**
 * 商品状态
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在线商品"),

    DOWN(1, "下架商品");

    /**
     * 状态码
     */
    private Integer cade;
    /**
     * 信息
     */
    private String message;

    ProductStatusEnum(Integer cade, String message) {
        this.cade = cade;
        this.message = message;
    }
}
