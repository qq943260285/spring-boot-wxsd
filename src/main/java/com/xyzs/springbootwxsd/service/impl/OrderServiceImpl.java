package com.xyzs.springbootwxsd.service.impl;

import com.xyzs.springbootwxsd.dataobj.OrderDetail;
import com.xyzs.springbootwxsd.dataobj.OrderMaster;
import com.xyzs.springbootwxsd.dataobj.ProductInfo;
import com.xyzs.springbootwxsd.dto.CartDTO;
import com.xyzs.springbootwxsd.dto.OrderDTO;
import com.xyzs.springbootwxsd.enums.ResultEnum;
import com.xyzs.springbootwxsd.exception.SellException;
import com.xyzs.springbootwxsd.repository.OrderDetailRepository;
import com.xyzs.springbootwxsd.repository.OrderMasterRepository;
import com.xyzs.springbootwxsd.service.OrderService;
import com.xyzs.springbootwxsd.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 订单
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = UUID.randomUUID().toString();
        //总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

        //购物车
        List<CartDTO> cartDTOLint = new ArrayList<>();
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            //1、查询商品单价 数量
            ProductInfo one = productService.findOne(orderDetail.getDetailId());
            if (one == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2、计算总价
            orderAmount = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //3、写入订单、订单详情
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(UUID.randomUUID().toString());
            BeanUtils.copyProperties(one, orderDetail);
            orderDetailRepository.save(orderDetail);

            CartDTO cartDTO = new CartDTO();
            cartDTO.setProductId(orderDetail.getProductId());
            cartDTO.setProductQuantity(orderDetail.getProductQuantity());
            cartDTOLint.add(cartDTO);
        }

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);

        //4、扣库存
        productService.decreaseStock(cartDTOLint);


        return orderDTO;
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
