package com.tryIt.service;

import java.util.List;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.NYJ_OrderProductListVO;
import com.tryIt.domain.NYJ_OrderProductVO;
import com.tryIt.domain.NYJ_Criteria;

public interface JSW_OrderService {
	void insertOrder(JSW_OrderVO orderVO);
	void insertOrderProduct(NYJ_OrderProductVO orderProductVO);
	List<JSW_OrderVO> getAllOrder();
	JSW_OrderVO getOrderById(Long order_id);
	List<JSW_OrderVO> getMemberOrder(Long user_id);
	List<NYJ_OrderProductVO> getOrderProducts(Long order_id);
	List<NYJ_OrderProductListVO> getOrderProductList(Long order_id);
	JSW_OrderVO getByOrderSeq(String order_seq);
	void updateOrder(String order_success, String order_seq);

	void deleteOrder(Long id);
	List<JSW_OrderVO> getOrderWithPaging(NYJ_Criteria cri);
	int countOrderNum();
	void deleteOrderProduct(Long order_id);
	void updateOrderProduct(Long order_id, Long product_id, String product_deliver_num);


}
