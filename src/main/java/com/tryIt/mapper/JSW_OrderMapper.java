package com.tryIt.mapper;

import java.util.List;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.NYJ_OrderProductListVO;
import com.tryIt.domain.NYJ_OrderProductVO;
import org.apache.ibatis.annotations.Param;
import com.tryIt.domain.NYJ_Criteria;

public interface JSW_OrderMapper {
	void insertOrder(JSW_OrderVO orderVO);
	void insertOrderProduct(NYJ_OrderProductVO orderProductVO);
	List<JSW_OrderVO> getAllOrder();
	JSW_OrderVO getOrderById(Long order_id);
	List<JSW_OrderVO> getMemberOrder(Long user_id);
	List<NYJ_OrderProductVO> getOrderProducts(Long order_id);
	List<NYJ_OrderProductListVO> getOrderProductList(Long order_id);
	JSW_OrderVO getByOrderSeq(String order_seq);
	void updateOrder(@Param("order_success") String order_success, @Param("order_seq") String order_seq);

	void deleteOrder(Long id);
	List<JSW_OrderVO> getOrderWithPaging(NYJ_Criteria cri);
	int countOrderNum();
	void deleteOrderProduct(Long order_id);
	void updateOrderProduct(Long order_id, Long product_id, String product_deliver_num);
}
