package com.tryIt.service;


import com.tryIt.domain.NYJ_CartListVO;
import com.tryIt.domain.NYJ_CartVO;
import com.tryIt.mapper.NYJ_CartMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NYJ_CartServiceImpl implements NYJ_CartService{

    private final NYJ_CartMapper mapper;

    @Override
    public void insertCart(Long product_id, Long member_id, int product_num) {
        mapper.insertCart(product_id,member_id,product_num);
    }

    @Override
    public void deleteCart(Long cart_id) {
        mapper.deleteCart(cart_id);
    }

    @Override
    public List<NYJ_CartListVO> getCartList(Long member_id) {
        return mapper.getCartList(member_id);
    }

    @Override
    public void deleteALl(Long member_id) {
        mapper.deleteALl(member_id);
    }

    @Override
    public int updateNum(Long cart_id,int product_change_num) {
        return mapper.updateNum(cart_id,product_change_num);
    }

    @Override
    public Long findById(Long product_id, Long member_id) {
        return mapper.findById(product_id,member_id);
    }
}
