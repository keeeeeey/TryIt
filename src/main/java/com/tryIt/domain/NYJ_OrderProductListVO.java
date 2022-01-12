package com.tryIt.domain;

import lombok.Data;

@Data
public class NYJ_OrderProductListVO {
    private Long product_id;
    private int product_num;
    private String product_name;
    private String product_img;
    private Long product_price;
    private int total_price;
    private String product_deliver;
}
