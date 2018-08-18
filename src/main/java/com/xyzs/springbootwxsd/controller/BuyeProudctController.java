package com.xyzs.springbootwxsd.controller;

import com.xyzs.springbootwxsd.VO.ProductInfoVO;
import com.xyzs.springbootwxsd.VO.ProductVO;
import com.xyzs.springbootwxsd.VO.ResultVO;
import com.xyzs.springbootwxsd.dataobj.ProductCategory;
import com.xyzs.springbootwxsd.dataobj.ProductInfo;
import com.xyzs.springbootwxsd.service.CategoryService;
import com.xyzs.springbootwxsd.service.ProductService;
import com.xyzs.springbootwxsd.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buye/proudct")
public class BuyeProudctController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public ResultVO list() {
        //1.查询所有上架商品
        List<ProductInfo> upAll = productService.findUpAll();
        //2.查询商品类目（查询所有）
        List<Integer> categoryTypeList = new ArrayList<>();
        //===传统方式===
//        for (ProductInfo p : upAll) {
//            categoryTypeList.add(p.getCategoryType());
//        }
        upAll.forEach(p -> categoryTypeList.add(p.getCategoryType()));
        //===精简写法===

        List<ProductCategory> all = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOs = new ArrayList<>();
        all.forEach(productCategory -> {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOs = new ArrayList<>();
            upAll.forEach(p -> {
                if (p.getCategoryType() == productCategory.getCategoryType()) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(p, productInfoVO);
                    productInfoVOs.add(productInfoVO);
                }
            });
            productVO.setProductInfoVOList(productInfoVOs);
            productVOs.add(productVO);
        });

//        ResultVO resultVO = new ResultVO();
//        resultVO.setCode(200);
//        resultVO.setMsg("成功");
//        resultVO.setData(productVOs);
//        ProductVO productVO = new ProductVO();
//        ProductInfoVO productInfoVO = new ProductInfoVO();
//        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));

//        resultVO.setData(Arrays.asList(productVO));
        return ResultVOUtil.success(productVOs);
    }
}
