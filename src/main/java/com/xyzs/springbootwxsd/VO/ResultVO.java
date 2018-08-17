package com.xyzs.springbootwxsd.VO;

import lombok.Data;

/**
 * 请求返回类型（视图对象）
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 数据内容
     */
    private T data;
}
