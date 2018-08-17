package com.xyzs.springbootwxsd.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品包含类目
 */
@Data
public class ProductVO {
    /**
     * 商品名称
     */
    @JsonProperty("name")
    private String categoryName;
    /**
     * 商品类型
     */
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
