package com.xyzs.springbootwxsd.service.impl;

import com.xyzs.springbootwxsd.dataobj.OrderDetail;
import com.xyzs.springbootwxsd.dto.OrderDTO;
import com.xyzs.springbootwxsd.enums.OrderStatusEnum;
import com.xyzs.springbootwxsd.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    private final String BUYER_OPENID = "1101110";
    private final String ORDER_ID = "7b75272c-416d-4da1-b370-2f2d97bda49f";
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("cyzs");
        orderDTO.setBuyerAddress("地址");
        orderDTO.setBuyerPhone("12345678912");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("755e5086-6e7e-44f1-8135-08d61481b762");
        orderDetail.setProductQuantity(5);
        orderDetailList.add(orderDetail);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO dto = orderService.create(orderDTO);
        System.out.println(dto);

    }

    @Test
    public void findOns() {
        OrderDTO orderDTO = orderService.findOns(ORDER_ID);
        System.out.println("信息：" + orderDTO);
        Assert.assertEquals(ORDER_ID, orderDTO.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, pageRequest);
        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOns(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertNotEquals(OrderStatusEnum.CANCEL.getCade(), result.getOrderStatus());
    }

    @Test
    public void finish() {

        OrderDTO orderDTO = orderService.findOns(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCade(), result.getOrderStatus());
    }

    @Test
    public void paid() {

        OrderDTO orderDTO = orderService.findOns(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCade(), result.getPayStatus());
    }
}