package com.tryIt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.domain.NYJ_Criteria;

public interface JSW_AdminProductService {
	
	List<NYJ_ProductVO> productlist();
	List<NYJ_ProductVO> productlistWithPaging(NYJ_Criteria cri);	
	List<NYJ_ProductVO> findproduct(@Param("find_product_id") String find_product_id);
	
	public void deleteProduct(@Param("deleteproductlist") String deleteproductlist);
	int countproductlistNum();
	int countFindProductNum(@Param("find_product_id") String find_product_id);

}
