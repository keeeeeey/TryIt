package com.tryIt.domain;

import lombok.Data;

@Data
public class NYJ_OrderProductVO {

    private Long order_id;
    private Long product_id;
    private int product_num;
    private int product_deliver;

    public NYJ_OrderProductVO(Long order_id,Long product_id,int product_num,int product_deliver){
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_num = product_num;
        this.product_deliver = product_deliver;
    }
}
