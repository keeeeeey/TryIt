package com.tryIt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tryIt.domain.KTE_FaqVO;
import com.tryIt.domain.KTE_NoticeVO;
import com.tryIt.domain.NYJ_Criteria;

public interface KTE_ServiceCenterService {

	public List<KTE_NoticeVO> selectNotice();
	public void insertNotice(KTE_NoticeVO noticeVO);
	public KTE_NoticeVO detailNotice(@Param("notice_id") int notice_id);
	public void plusCnt(@Param("notice_id") int notice_id);
	public List<KTE_FaqVO> selectFaq(@Param("faq_category") int faq_category);
	public int countNoticeNum();
	public List<KTE_NoticeVO> getNoticeWithPaging(NYJ_Criteria cri);
	public void deleteNotice(@Param("notice_id")int notice_id);
	public void updateNotice(KTE_NoticeVO noticeVO);


}
