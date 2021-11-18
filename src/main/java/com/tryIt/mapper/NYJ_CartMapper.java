package com.tryIt.mapper;

import com.tryIt.domain.NYJ_CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NYJ_CartMapper {

    void insertCart(@Param("product_id") Long product_id, @Param("member_id") Long member_id,int product_num);
    void deleteCart(Long cart_id);
    List<NYJ_CartVO> getCartList(Long member_id);
}
