package com.tryIt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.mapper.JSW_AdminProductMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JSW_AdminProductServiceImpl implements JSW_AdminProductService {

	private JSW_AdminProductMapper mapper;
	@Override
	public List<NYJ_ProductVO> productlist() {
		List<NYJ_ProductVO> productlist = mapper.productlist();
		return productlist;
	}

	@Override
	public List<NYJ_ProductVO> productlistWithPaging(NYJ_Criteria cri) {
		List<NYJ_ProductVO> productlist = mapper.productlistWithPaging(cri);
		return productlist;
	}

	@Override
	public List<NYJ_ProductVO> findproduct(String find_product_id) {
		List<NYJ_ProductVO> productlist = mapper.findProduct(find_product_id);
		return productlist;
	}

	@Override
	public void deleteProduct(String delete_product_id) {
		mapper.deleteProduct(delete_product_id);
		
	}

	@Override
	public int countproductlistNum() {
		return mapper.countProductlistNum();
	}

	@Override
	public int countFindProductNum(String find_product_id) {
		return mapper.countFindProductNum(find_product_id);
	}

}
