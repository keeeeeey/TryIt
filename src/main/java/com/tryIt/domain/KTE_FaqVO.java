package com.tryIt.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class KTE_FaqVO {
	private int faq_id;
	private String faq_title;
	private String faq_content;
	private int faq_category;
}
