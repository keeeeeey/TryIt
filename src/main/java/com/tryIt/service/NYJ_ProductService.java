package com.tryIt.service;

import com.tryIt.domain.NYJ_ProductVO;

import java.util.List;

public interface NYJ_ProductService {
    public NYJ_ProductVO findProduct(Long product_id);
    public List<NYJ_ProductVO> findAllProducts();
    public void updateProduct(NYJ_ProductVO nyj_productVO);
    public void deleteProduct(Long product_id);
    public void insertProduct();
    List<NYJ_ProductVO> getRelateProducts(String category);
    List<NYJ_ProductVO> getSearchProducts(String keyword);
}
