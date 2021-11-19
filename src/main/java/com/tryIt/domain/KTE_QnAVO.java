package com.tryIt.domain;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class KTE_QnAVO {
	private int qna_id; 
	private String qna_title;
	private String qna_content;
	private int product_id;
	private int user_id;
	private Timestamp qna_date;
	private String qna_category;
	private String qna_reply_yn; 
	private String qna_reply;
	private String qna_secret;
}
