package com.xyzs.springbootwxsd.dataobj;

import com.xyzs.springbootwxsd.repository.OrderDetailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailTest {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(UUID.randomUUID().toString());
        orderDetail.setOrderId("123");
        orderDetail.setProductId("1");
        orderDetail.setProductName("1");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductQuantity(123);
        orderDetail.setProductPrice(new BigDecimal(66));

        OrderDetail orderDetail1 = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(orderDetail1);
    }

    @Test
    public void findByOrderIdTest() {

    }
}