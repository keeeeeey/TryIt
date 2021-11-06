package com.tryIt.service;

import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.mapper.NYJ_ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class NYJ_ProductServiceImpl implements NYJ_ProductService{

    private final NYJ_ProductMapper productMapper;

    @Override
    public NYJ_ProductVO findProduct(Long product_id) {
        return productMapper.getProduct(product_id);
    }

    @Override
    public List<NYJ_ProductVO> findAllProducts() {
        return productMapper.getAllProducts();
    }

    @Override
    public void updateProduct(NYJ_ProductVO nyj_productVO) {

    }

    @Override
    public void deleteProduct(Long product_id) {
        productMapper.deleteProduct(product_id);
    }

    @Override
    public void insertProduct() {
        productMapper.insertProduct();
    }

    @Override
    public List<NYJ_ProductVO> getRelateProducts(String category) {
        return productMapper.getRelateProducts(category);
    }

    @Override
    public List<NYJ_ProductVO> getSearchProducts(String keyword) {
        return productMapper.getSearchProducts(keyword);
    }
}
