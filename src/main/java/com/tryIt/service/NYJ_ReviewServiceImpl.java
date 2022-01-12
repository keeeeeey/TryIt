package com.tryIt.service;

import com.tryIt.domain.NYJ_ReviewVO;
import com.tryIt.mapper.NYJ_ReviewMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NYJ_ReviewServiceImpl implements  NYJ_ReviewService{

    private final NYJ_ReviewMapper reviewMapper;

    public List<NYJ_ReviewVO> getProductReviews(Long product_id) {
        return reviewMapper.getProductReviews(product_id);
    }


    public List<NYJ_ReviewVO> getMemberReviews(Long member_id) {
        return reviewMapper.getProductReviews(member_id);
    }

    public void insertReview(NYJ_ReviewVO reviewVO) {
        reviewMapper.insertReview(reviewVO);
    }


    public void deleteReview(Long review_id) {
        reviewMapper.deleteReview(review_id);
    }

    @Override
    public NYJ_ReviewVO findByReviewId(Long review_id) {
        return reviewMapper.findByReviewId(review_id);
    }
}
