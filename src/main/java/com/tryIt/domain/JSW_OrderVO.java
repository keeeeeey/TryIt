package com.tryIt.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class JSW_OrderVO {
	private Long id;
	private Long order_user_id;
	private String order_date;
	private String order_address;
	private String order_detail_address;
	private String order_postalcode;
	private String order_message;
	private String order_seq;
	private String order_success;
	private String order_receiver;
	private List<NYJ_OrderProductVO> orderProductList;
	private String order_sender;

	//parameter for kakaopay
	private int total_price;
	private int qty;

	//추가
	private String order_status;
}
