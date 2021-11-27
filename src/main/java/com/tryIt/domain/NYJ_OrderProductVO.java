package com.tryIt.domain;

import lombok.Data;

@Data
public class NYJ_OrderProductVO {
    private Long order_id;
    private Long product_id;
    private int product_num;
    private int product_deliver;
}
