package com.tryIt.domain;

import lombok.Data;

import java.util.Date;

@Data
public class NYJ_KakaoPayReadyVO {

    //response
    private String tid, next_redirect_pc_url;
    private Date created_at;

}
