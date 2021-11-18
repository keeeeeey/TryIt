package com.tryIt.domain;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class NYJ_ReviewVO {

    private Long id;
    private Long review_id;
    private Long product_id;
    private String review_content;
    private String review_title;
    private int review_score;
    private Timestamp review_date;
    private int review_like;
    private String review_img;

    public NYJ_ReviewVO(Long review_id, Long product_id,int review_score, String review_content, String review_img){
        this.review_id = review_id;
        this.product_id = product_id;
        this.review_score = review_score;
        this.review_content = review_content;
        this.review_img = review_img;
        this.review_title = "review";
    }
}
