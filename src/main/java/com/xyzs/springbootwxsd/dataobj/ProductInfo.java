package com.xyzs.springbootwxsd.dataobj;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息
 */
@Entity
@Data
public class ProductInfo {
    /**
     * 商品ID
     */
    @Id
    private String productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 单价
     */
    private BigDecimal productPrice;
    /**
     * 库存
     */
    private int productStock;
    /**
     * 描述
     */
    private String productDescription;//` varchar(64) comment '描述',
    /**
     * 小图
     */
    private String productIcon;//` varchar(512) comment '小图',
    /**
     * 商品状态
     */
    private int productStatus;//` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
    /**
     * 类目编号
     */
    private int categoryType;//` int not null comment '类目编号',
    /**
     * 创建时间
     */
    private Date create_time;//` timestamp not null default current_timestamp comment '创建时间',
    /**
     * 修改时间
     */
    private Date update_time;//` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
}
