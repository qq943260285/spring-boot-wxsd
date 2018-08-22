package com.xyzs.springbootwxsd.service.impl;

import com.xyzs.springbootwxsd.dto.OrderDTO;
import com.xyzs.springbootwxsd.service.OrderService;
import com.xyzs.springbootwxsd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 订单
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ProductServiceImpl productService;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {

//        for (int i = 0; i < ; i++) {
//
//        }
        //1、查询商品单价 数量
//        productService.findOne()
        
        
        //2、计算总价
        //3、写入订单、订单详情
        //4、扣库存

        return null;
    }

    @Override
    public OrderDTO findOns(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
