package com.tryIt.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class KTE_NoticeVO {
	private int notice_id;
	private String notice_title;
	private String notice_content;
	private String notice_writer;
	private int notice_viewCnt;
	private String notice_date;
}
