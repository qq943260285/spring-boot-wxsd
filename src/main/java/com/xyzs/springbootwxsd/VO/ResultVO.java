package com.xyzs.springbootwxsd.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 请求返回类型（视图对象）
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//为Null时不返还
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
