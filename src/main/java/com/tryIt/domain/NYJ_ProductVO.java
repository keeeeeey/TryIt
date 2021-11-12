package com.tryIt.domain;

import lombok.Data;

import java.util.Date;

@Data
public class NYJ_ProductVO {

    private Long id;
    private String product_name;
    private String product_info;
    private int product_weight;
    private int product_price;
    private String product_company;
    private Date product_reg_date;
    private String product_img;
    private String product_category;
    private int product_score;
    private int product_review_num;
    private String product_model;

}
