package com.tryIt.service;

import java.util.List;

import com.tryIt.domain.JSW_OrderVO;

public interface JSW_OrderService {
	public void insertOrder();
	public List<JSW_OrderVO> getAllOrder();
}
