package com.tryIt.service;


import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.mapper.NYJ_ProductMapper;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    	List<String> resultKeyword = Search(keyword);
        return productMapper.getSearchProducts(resultKeyword);
    }

    @Override
    public List<NYJ_ProductVO> getProductsWithPaging(NYJ_Criteria cri) {
        return productMapper.getAllProductsWithPaging(cri);
    }

    @Override
    public List<NYJ_ProductVO> getBestProducts() {
        return productMapper.getBestProducts();
    }

    @Override
    public List<NYJ_ProductVO> getHighRatedProducts() {
        return productMapper.getHighRatedProducts();
    }

    @Override
    public List<NYJ_ProductVO> getRecentProducts() {
        return productMapper.getRecentProducts();
    }

    @Override
    public int countProductNum() {
        return productMapper.countProductNum();
    }

    @Override
    public List<NYJ_ProductVO> getSearchProductsCategory(String keyword, String category) {
    	List<String> resultKeyword = Search(keyword);
        return productMapper.getSearchProductsCategory(resultKeyword,category);
    }
    
    public static List<String> Search(String input){
    	Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
	    KomoranResult analyzeResultList = komoran.analyze(input);
	    List<Token> tokenList = analyzeResultList.getTokenList();
	    List<String> searchList = new ArrayList<>();
	    for (Token token : tokenList) {
	    	searchList.add(token.getMorph());
	        //System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
	    }
	    System.out.println(searchList);
	    return searchList;
	    
	  }
}
