package com.tryIt.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.tryIt.domain.NYJ_CartListVO;
import com.tryIt.mapper.NYJ_CartMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NYJ_CartServiceImpl implements NYJ_CartService{

    private final NYJ_CartMapper mapper;

    @Override
    public void insertCart( Long member_id, Long product_id,int product_num) {
        mapper.insertCart(member_id,product_id,product_num);
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
    public void deleteAll(Long member_id) {
        mapper.deleteAll(member_id);
    }

    @Override
    public int updateNum(Long cart_id,int product_change_num) {
        return mapper.updateNum(cart_id,product_change_num);
    }

    @Override
    public Long findById(Long product_id, Long member_id) {
        return mapper.findById(product_id,member_id);
    }

    @Override
    public int getTotalPrice(Long member_id) {
        return mapper.getTotalPrice(member_id);
    }
}
