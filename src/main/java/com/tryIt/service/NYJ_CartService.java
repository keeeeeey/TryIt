package com.tryIt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tryIt.domain.NYJ_CartListVO;

public interface NYJ_CartService {
    void insertCart( @Param("member_id") Long member_id,@Param("product_id") Long product_id, int product_num);
    void deleteCart(Long cart_id);
    List<NYJ_CartListVO> getCartList(Long member_id);
    void deleteAll(Long member_id);
    int updateNum(@Param("cart_id") Long cart_id,@Param("product_change_num") int product_change_num);
    Long findById(@Param("product_id") Long product_id, @Param("member_id") Long member_id);
    int getTotalPrice(@Param("member_id") Long member_id);
}
