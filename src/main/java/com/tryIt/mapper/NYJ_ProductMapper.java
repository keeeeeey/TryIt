package com.tryIt.mapper;


import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_ProductVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NYJ_ProductMapper {
    NYJ_ProductVO getProduct(Long product_id);
    List<NYJ_ProductVO> getAllProducts();
    void insertProduct();
    void updateProduct(NYJ_ProductVO nyj_productVO);
    void deleteProduct(Long product_id);
    List<NYJ_ProductVO> getRelateProducts(String category);
    List<NYJ_ProductVO> getSearchProducts(String keyword);
    List<NYJ_ProductVO> getSearchProductsCategory(@Param("keyword") String keyword, @Param("category") String category);
    List<NYJ_ProductVO> getAllProductsWithPaging(NYJ_Criteria cri);
    List<NYJ_ProductVO> getBestProducts();
    List<NYJ_ProductVO> getHighRatedProducts();
    List<NYJ_ProductVO> getRecentProducts();
    int countProductNum();
}
