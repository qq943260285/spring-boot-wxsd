package com.xyzs.springbootwxsd.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
    /**
     * 姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;
    /**
     * 手机
     */
    @NotEmpty(message = "手机必填")
    private String phone;
    /**
     * 地址
     */
    @NotEmpty(message = "地址必填")
    private String address;
    /**
     * Openid
     */
    @NotEmpty(message = "Openid必填")
    private String openid;
    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}

