package com.tryIt.service;

import java.util.List;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.NYJ_Criteria;

public interface JSW_OrderService {
	public void insertOrder();
	public List<JSW_OrderVO> getAllOrder();
	public void deleteOrder(int id);
	public List<JSW_OrderVO> getOrderWithPaging(NYJ_Criteria cri);
	public 	int countOrderNum();

}
