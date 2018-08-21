package com.xyzs.springbootwxsd.dataobj;

import com.xyzs.springbootwxsd.enums.OrderStatusEnum;
import com.xyzs.springbootwxsd.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @Author: 小宇专属
 * @Date: 2018/8/18 10:00
 * @Modify: 无
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    /**
     * 订单ID
     */
    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCade();
    /**
     * 支付状态
     */
    private Integer payStatus = PayStatusEnum.WAIT.getCade();
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
