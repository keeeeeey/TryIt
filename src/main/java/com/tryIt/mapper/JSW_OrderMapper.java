package com.tryIt.mapper;

import java.util.List;

import com.tryIt.domain.JSW_OrderVO;

public interface JSW_OrderMapper {
	void insertOrder();
	List<JSW_OrderVO> getAllOrder();
}
