package com.tryIt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.mapper.JSW_OrderMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JSW_OrderServiceImpl implements JSW_OrderService{
	
	private final JSW_OrderMapper purchaseMapper;
	
	@Override
	public void insertOrder() {
		purchaseMapper.insertOrder();
	}

	@Override
	public List<JSW_OrderVO> getAllOrder() {
		return purchaseMapper.getAllOrder();
	}
	
	@Override
	public void deleteOrder(int id) {
		purchaseMapper.deleteOrder(id);
	}
	
	@Override
	public List<JSW_OrderVO> getOrderWithPaging(NYJ_Criteria cri){
		return purchaseMapper.getOrderWithPaging(cri);
	}
	
	@Override
	public int countOrderNum() {
		return purchaseMapper.countOrderNum();
	}
}
