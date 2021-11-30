package com.tryIt.mapper;

import java.util.List;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.NYJ_Criteria;

public interface JSW_OrderMapper {
	void insertOrder();
	List<JSW_OrderVO> getAllOrder();
	void deleteOrder(int id);
	List<JSW_OrderVO> getOrderWithPaging(NYJ_Criteria cri);
	int countOrderNum();
}
