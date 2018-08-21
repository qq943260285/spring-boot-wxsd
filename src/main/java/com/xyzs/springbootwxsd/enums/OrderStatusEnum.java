package com.xyzs.springbootwxsd.enums;

import lombok.Getter;

/**
 * 订单状态
 *
 * @Author: 小宇专属
 * @Date: 2018/8/18 10:10
 * @Modify: 无
 */
@Getter
public enum OrderStatusEnum {
    /**
     * 新订单
     */
    NEW(0, "新订单"),
    /**
     * 完结订单
     */
    FINISHED(1, "完结订单"),
    /**
     * 取消订单
     */
    CANCEL(2, "取消订单");

    /**
     * 状态码
     */
    private Integer cade;
    /**
     * 信息
     */
    private String message;

    OrderStatusEnum(Integer cade, String message) {
        this.cade = cade;
        this.message = message;
    }
}
