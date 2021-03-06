package com.tryIt.mapper;

import com.tryIt.domain.NYJ_ReviewVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NYJ_ReviewMapper {
    List<NYJ_ReviewVO> getProductReviews(Long product_id);
    List<NYJ_ReviewVO> getMemberReviews(@Param("review_id") Long member_id);
    NYJ_ReviewVO findByReviewId(Long review_id);
    void insertReview(NYJ_ReviewVO reviewVO);
    void updateReview(NYJ_ReviewVO reviewVO);
    void deleteReview(Long review_id);
    void likeReview();
}
