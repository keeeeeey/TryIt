package com.tryIt.domain;

import lombok.Data;

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

}
