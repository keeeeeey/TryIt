package com.tryIt.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class KTE_QnAVO {
	private Long qna_id; 
	private String qna_title;
	private String qna_content;
	private Long product_id;
	private Long user_id;
	private String qna_date;
	private String qna_category;
	private String qna_reply_yn; 
	private String qna_reply;
	private String qna_secret;
	private String qna_writer;
	private int qna_num;
	private String qna_viewYn;
}
