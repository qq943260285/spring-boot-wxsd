package com.xyzs.springbootwxsd.service.impl;

import com.xyzs.springbootwxsd.converter.OrderMastr2OrderDTOConverter;
import com.xyzs.springbootwxsd.dataobj.OrderDetail;
import com.xyzs.springbootwxsd.dataobj.OrderMaster;
import com.xyzs.springbootwxsd.dataobj.ProductInfo;
import com.xyzs.springbootwxsd.dto.CartDTO;
import com.xyzs.springbootwxsd.dto.OrderDTO;
import com.xyzs.springbootwxsd.enums.OrderStatusEnum;
import com.xyzs.springbootwxsd.enums.PayStatusEnum;
import com.xyzs.springbootwxsd.enums.ResultEnum;
import com.xyzs.springbootwxsd.exception.SellException;
import com.xyzs.springbootwxsd.repository.OrderDetailRepository;
import com.xyzs.springbootwxsd.repository.OrderMasterRepository;
import com.xyzs.springbootwxsd.service.OrderService;
import com.xyzs.springbootwxsd.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 订单
 */
@Slf4j
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
            ProductInfo one = productService.findOne(orderDetail.getProductId());
            if (one == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //2、计算总价
            orderAmount = one.getProductPrice()
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
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCade());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCade());
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMasterRepository.save(orderMaster);
        //4、扣库存
        productService.decreaseStock(cartDTOLint);
        return orderDTO;
    }

    @Override
    public OrderDTO findOns(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).get();
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        List<OrderDTO> orderDTOList = OrderMastr2OrderDTOConverter.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
//        List<OrderMaster> orderMasterList;
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        OrderMaster orderMaster = new OrderMaster();
        //1 判断订单状态（完结订单不可取消）
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCade())) {
            log.error("[取消订单] 订单状态不正确，OrderId={},OrderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2 修改
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCade());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster saveOrderMaster = orderMasterRepository.save(orderMaster);
        if (saveOrderMaster == null) {
            log.error("[取消订单] 更新失败，orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //3 返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[取消订单]订单详情为空,orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream()
                .map(v -> new CartDTO(v.getProductId(), v.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //4 已支付需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)) {
            //TODO:退款
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //1 订单转台
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCade())) {
            log.error("[完结订单] 订单状态不正确  orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2 修改状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCade());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("[完结订单] 更新失败orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCade())) {
            log.error("[订单支付] 订单状态不正确 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCade())) {
            log.error("[订单支付] 支付状态不正确 orderDTO={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态

        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCade());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("[订单支付] 更新失败orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
