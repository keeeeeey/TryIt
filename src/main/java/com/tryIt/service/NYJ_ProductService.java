package com.tryIt.service;

import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_ProductVO;

import java.util.List;
import java.util.Map;

public interface NYJ_ProductService {
    public NYJ_ProductVO findProduct(Long product_id);
    public List<NYJ_ProductVO> findAllProducts();
    public void updateProduct(NYJ_ProductVO nyj_productVO);
    public void deleteProduct(Long product_id);
    public void insertProduct();
    List<NYJ_ProductVO> getRelateProducts(String category);
    List<NYJ_ProductVO> getSearchProducts(String keyword);
    List<NYJ_ProductVO> getProductsWithPaging(NYJ_Criteria cri);
    List<NYJ_ProductVO> getBestProducts();
    List<NYJ_ProductVO> getHighRatedProducts();
    List<NYJ_ProductVO> getRecentProducts();
    int countProductNum();
    List<NYJ_ProductVO> getProductsByCateogry(String category);
    List<NYJ_ProductVO> getSearchProductsCategory(String keyword,String category);
}
