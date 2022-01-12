package com.tryIt.service;

import com.tryIt.domain.NYJ_ReviewVO;

import java.util.List;

public interface NYJ_ReviewService {

    List<NYJ_ReviewVO> getProductReviews(Long product_id);
    List<NYJ_ReviewVO> getMemberReviews(Long member_id);
    void insertReview(NYJ_ReviewVO reviewVO);
    void deleteReview(Long review_id);
    NYJ_ReviewVO findByReviewId(Long review_id);
}
