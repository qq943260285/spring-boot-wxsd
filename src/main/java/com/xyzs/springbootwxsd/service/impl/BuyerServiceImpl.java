package com.xyzs.springbootwxsd.service.impl;

import com.xyzs.springbootwxsd.dto.OrderDTO;
import com.xyzs.springbootwxsd.enums.ResultEnum;
import com.xyzs.springbootwxsd.exception.SellException;
import com.xyzs.springbootwxsd.service.BuyerService;
import com.xyzs.springbootwxsd.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {

        return checkOrderOener(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOener(openid, orderId);
        if (orderDTO == null) {
            log.error("[取消订单] 订单不存在,openid={},orderId={}", openid, orderId);
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOener(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findOns(orderId);
        if (orderDTO == null) {
            return null;
        }
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("[查询订单] 订单的openid不一致,openid={},orderId={}", openid, orderId);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
