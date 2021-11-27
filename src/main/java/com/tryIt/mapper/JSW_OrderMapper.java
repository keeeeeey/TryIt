package com.tryIt.mapper;

import java.util.List;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.NYJ_OrderProductVO;

public interface JSW_OrderMapper {
	void insertOrder(JSW_OrderVO orderVO);
	void insertOrderProduct(NYJ_OrderProductVO orderProductVO);
	List<JSW_OrderVO> getAllOrder();
	JSW_OrderVO getOrderById(Long order_id);
	List<JSW_OrderVO> getMemberOrder(Long user_id);
	JSW_OrderVO getByOrderSeq(String order_seq);
}
