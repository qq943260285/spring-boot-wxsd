package com.xyzs.springbootwxsd.repository;

import com.xyzs.springbootwxsd.dataobj.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest() {
        //查询
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        System.out.println(productCategory);
    }

    @Test
    @Transactional //添加数据库后会删除 只做测试
    public void saveTest() {
        //创建
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("777");
        productCategory.setCategoryType(777);
        ProductCategory save = productCategoryRepository.save(productCategory);
        Assert.assertNotNull(save);
    }

    @Test
    @Transactional //添加数据库后会删除
    public void updataTest() {
        //更新
        ProductCategory productCategory = productCategoryRepository.findById(5).get();
        productCategory.setCategoryName("555");
        productCategory.setCategoryType(555);
        ProductCategory save = productCategoryRepository.save(productCategory);

        Assert.assertNotNull(save);
        productCategory = productCategoryRepository.findById(5).get();
        System.out.println(productCategory);
    }

    @Test
    public void findByCategoryTypeInTest() {
        List<Integer> list = Arrays.asList(2, 4, 6);
        List<ProductCategory> byCategoryTypeIn = productCategoryRepository.findByCategoryTypeIn(list);
        byCategoryTypeIn.forEach(s -> System.out.println(s));
        Assert.assertNotEquals(0, byCategoryTypeIn.size());
    }
}