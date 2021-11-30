package com.tryIt.mapper;


import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_ProductVO;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface NYJ_ProductMapper {
    NYJ_ProductVO getProduct(Long product_id);
    List<NYJ_ProductVO> getAllProducts();
    void insertProduct();
    void updateProduct(NYJ_ProductVO nyj_productVO);
    void deleteProduct(Long product_id);
    List<NYJ_ProductVO> getRelateProducts(String category);
    List<NYJ_ProductVO> getSearchProducts(@Param("resultKeyword") List<String> resultKeyword);
    List<NYJ_ProductVO> getSearchProductsCategory(@Param("resultKeyword") List<String> keyword, @Param("category") String category);
    List<NYJ_ProductVO> getAllProductsWithPaging(NYJ_Criteria cri);
    List<NYJ_ProductVO> getBestProducts();
    List<NYJ_ProductVO> getHighRatedProducts();
    List<NYJ_ProductVO> getRecentProducts();
    int countProductNum();
    
}
