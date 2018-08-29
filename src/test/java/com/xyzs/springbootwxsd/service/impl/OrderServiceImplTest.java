package com.xyzs.springbootwxsd.service.impl;

import com.xyzs.springbootwxsd.dataobj.OrderDetail;
import com.xyzs.springbootwxsd.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {

    private final String buyerOpenid = "1101110";
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("cyzs");
        orderDTO.setBuyerAddress("地址");
        orderDTO.setBuyerPhone("12345678912");
        orderDTO.setBuyerOpenid(buyerOpenid);
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
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}