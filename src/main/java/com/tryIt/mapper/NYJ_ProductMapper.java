package com.tryIt.mapper;

import com.tryIt.domain.NYJ_ProductVO;

import java.util.List;

public interface NYJ_ProductMapper {
    NYJ_ProductVO getProduct(Long product_id);
    List<NYJ_ProductVO> getAllProducts();
    void insertProduct();
    void updateProduct(NYJ_ProductVO nyj_productVO);
    void deleteProduct(Long product_id);
    List<NYJ_ProductVO> getRelateProducts(String category);
    List<NYJ_ProductVO> getSearchProducts(String keyword);
}
