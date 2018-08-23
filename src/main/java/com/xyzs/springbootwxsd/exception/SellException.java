package com.xyzs.springbootwxsd.exception;

import com.xyzs.springbootwxsd.enums.ResultEnum;

/**
 * 自定义异常
 */
public class SellException extends RuntimeException {
    /**
     * 状态码
     */
    private Integer cade;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.cade = resultEnum.getCade();
//        this.cade = cade;
//        this.message = message;
    }
}

