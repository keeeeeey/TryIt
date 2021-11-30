package com.tryIt.domain;

import lombok.Data;

@Data
public class JSW_OrderVO {
	private Long id;
	private String order_date;
	private String order_address;
	private String order_postalcode;
	private String order_address_detail;
	private String order_message;
	private String order_seq;
	private String order_success;
	private String order_receiver;
	private String order_sender;
	
	//추가
	private String order_status;
}
