package com.tryIt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tryIt.domain.JSW_OrderVO;
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
}
