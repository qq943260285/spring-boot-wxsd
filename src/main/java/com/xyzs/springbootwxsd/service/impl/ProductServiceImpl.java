package com.xyzs.springbootwxsd.service.impl;

import com.xyzs.springbootwxsd.dataobj.ProductInfo;
import com.xyzs.springbootwxsd.dto.CartDTO;
import com.xyzs.springbootwxsd.enums.ProductStatusEnum;
import com.xyzs.springbootwxsd.enums.ResultEnum;
import com.xyzs.springbootwxsd.exception.SellException;
import com.xyzs.springbootwxsd.repository.ProductInfoRepository;
import com.xyzs.springbootwxsd.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCade());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional//事务
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDto : cartDTOList) {
            ProductInfo productInfo = productInfoRepository.findById(cartDto.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int i = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (i < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            } else {
                productInfo.setProductStock(i);
                productInfoRepository.save(productInfo);
            }
        }
    }
}

