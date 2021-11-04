package com.tryIt.domain;

import java.sql.Timestamp;

public class KKY_MemberVO {

	private int id;
	private String user_id;
	private String user_pw;
	private String user_email;
	private String user_name;
	private String user_nickname;
	private String user_address;
	private String user_zipcode;
	private String user_phonenum;
	private Timestamp user_join_date;
	private int user_yn;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_zipcode() {
		return user_zipcode;
	}
	public void setUser_zipcode(String user_zipcode) {
		this.user_zipcode = user_zipcode;
	}
	public String getUser_phonenum() {
		return user_phonenum;
	}
	public void setUser_phonenum(String user_phonenum) {
		this.user_phonenum = user_phonenum;
	}
	public Timestamp getUser_join_date() {
		return user_join_date;
	}
	public void setUser_join_date(Timestamp user_join_date) {
		this.user_join_date = user_join_date;
	}
	public int getUser_yn() {
		return user_yn;
	}
	public void setUser_yn(int user_yn) {
		this.user_yn = user_yn;
	}
}
