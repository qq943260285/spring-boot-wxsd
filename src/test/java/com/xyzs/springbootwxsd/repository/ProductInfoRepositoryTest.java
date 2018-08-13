package com.xyzs.springbootwxsd.repository;

import com.xyzs.springbootwxsd.dataobj.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoRepositoryTest {
    @Autowired
    ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(UUID.randomUUID().toString());
        productInfo.setProductName("商品名称");
        productInfo.setProductPrice(new BigDecimal(3.3));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("商品描述信息");
        productInfo.setProductIcon("小图");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(1);

        ProductInfo result = productInfoRepository.save(productInfo);
        Assert.assertNotNull(result);

    }

    @Test
    public void findByProductStatus() {

        List<ProductInfo> list=productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,list.size());
    }
}