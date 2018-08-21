package com.xyzs.springbootwxsd.dto;

import com.xyzs.springbootwxsd.dataobj.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * DTO:各层传输用对象
 * 订单
 */
@Data
public class OrderDTO {
    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家手机
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信OpenID
     */
    private String buyerOpenid;
    /**
     * 订单金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态，默认为新下单
     */
    private Integer orderStatus;
    /**
     * 支付状态
     */
    private Integer payStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 订单详情列表
     */
    private List<OrderDetail> orderDetailList;
}
