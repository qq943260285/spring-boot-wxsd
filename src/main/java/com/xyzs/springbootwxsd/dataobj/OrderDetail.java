package com.xyzs.springbootwxsd.dataobj;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情
 *
 * @Author: 小宇专属
 * @Date: 2018/8/18 10:24
 * @Modify: 无
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    /**
     * 订单详情ID
     */
    @Id
    private String detailId;
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 商品ID
     */
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
     * 商品数量
     */
    private Integer productQuantity;
    /**
     * 商品小图
     */
    private String productIcon;
}
