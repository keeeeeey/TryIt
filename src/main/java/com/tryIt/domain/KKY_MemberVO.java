package com.tryIt.domain;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class KKY_MemberVO {

	private int id;
	private String user_id;
	private String user_pw;
	private String user_email;
	private String user_name;
	private String user_nickname;
	private String user_address;
	private String user_detail_address;
	private String user_zipcode;
	private String user_phonenum;
	private Date user_join_date;
	private int user_yn;
	private String role;
	
}
