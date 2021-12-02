package com.tryIt.mapper;
import java.util.List;

import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.domain.NYJ_Criteria;

public interface JSW_AdminProductMapper {
	
	List<NYJ_ProductVO> productlist();
	List<NYJ_ProductVO> productlistWithPaging(NYJ_Criteria cri);
	List<NYJ_ProductVO> findProduct(String find_product_id);
	
	public void deleteProduct(String delete_product_id);
	int countProductlistNum();
	int countFindProductNum(String find_product_name);

}
