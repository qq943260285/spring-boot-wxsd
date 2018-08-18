package com.xyzs.springbootwxsd.repository;

import com.xyzs.springbootwxsd.dataobj.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.soap.Addressing;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(UUID.randomUUID().toString());
        orderMaster.setBuyerName("小宇专属");
        orderMaster.setBuyerPhone("12345678912");
        orderMaster.setBuyerAddress("地址");
        orderMaster.setBuyerOpenid("943260285");
        orderMaster.setOrderAmount(new BigDecimal(666));
        orderMaster.setCreateTime(new Date());
        orderMaster.setUpdateTime(new Date());
        orderMaster = orderMasterRepository.save(orderMaster);
        Assert.assertNotNull(orderMaster);
    }

//    @Test
//    public void findByBuyerOpenId() {
//    }
}