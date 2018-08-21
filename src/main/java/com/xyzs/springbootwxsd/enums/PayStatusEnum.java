package com.xyzs.springbootwxsd.enums;

import lombok.Getter;

/**
 * 支付状态
 */
@Getter
public enum PayStatusEnum {
    /**
     * 等待支付
     */
    WAIT(0, "等待支付"),
    /**
     * 支付成功
     */
    SUCCESS(1, "支付成功");

    /**
     * 状态码
     */
    private Integer cade;
    /**
     * 信息
     */
    private String message;

    PayStatusEnum(Integer cade, String message) {
        this.cade = cade;
        this.message = message;
    }
}
