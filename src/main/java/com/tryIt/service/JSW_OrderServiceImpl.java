package com.tryIt.service;

import java.util.List;

import com.tryIt.domain.NYJ_OrderProductVO;
import org.springframework.stereotype.Service;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.mapper.JSW_OrderMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JSW_OrderServiceImpl implements JSW_OrderService{
	
	private final JSW_OrderMapper purchaseMapper;

	@Override
	public void insertOrder(JSW_OrderVO orderVO) {
		purchaseMapper.insertOrder(orderVO);
	}

	@Override
	public void insertOrderProduct(NYJ_OrderProductVO orderProductVO) {
		purchaseMapper.insertOrderProduct(orderProductVO);
	}

	@Override
	public List<JSW_OrderVO> getAllOrder() {
		return purchaseMapper.getAllOrder();
	}

	@Override
	public JSW_OrderVO getOrderById(Long order_id) {
		return purchaseMapper.getOrderById(order_id);
	}

	@Override
	public List<JSW_OrderVO> getMemberOrder(Long user_id) {
		return purchaseMapper.getMemberOrder(user_id);
	}

	@Override
	public JSW_OrderVO getByOrderSeq(String order_seq) {
		return purchaseMapper.getByOrderSeq(order_seq);
	}
}
