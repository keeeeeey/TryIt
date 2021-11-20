package com.tryIt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryIt.domain.KTE_FaqVO;
import com.tryIt.domain.KTE_NoticeVO;
import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.mapper.KTE_ServiceCenterMapper;

@Service

public class KTE_ServiceCenterServiceImpl implements KTE_ServiceCenterService{
	@Autowired
	private KTE_ServiceCenterMapper serviceCenterMapper;
	
	@Override
	public void insertNotice(KTE_NoticeVO noticeVO) {
		serviceCenterMapper.insertNotice(noticeVO);
	}
	
	@Override
	public List<KTE_NoticeVO> selectNotice(){
		return serviceCenterMapper.selectNotice();
	}
	
	@Override
	public KTE_NoticeVO detailNotice(@Param("notice_id") int notice_id) {
		return serviceCenterMapper.detailNotice(notice_id);
	}

	@Override
	public void plusCnt(@Param("notice_id") int notice_id) {
		serviceCenterMapper.plusCnt(notice_id);
	}
	@Override
	public List<KTE_FaqVO> selectFaq(@Param("faq_category") int faq_category){
		return serviceCenterMapper.selectFaq(faq_category);
	}
	
	@Override
	public int countNoticeNum() {
		return serviceCenterMapper.countNoticeNum();
	}
	
	@Override
	public List<KTE_NoticeVO> getNoticeWithPaging(NYJ_Criteria cri){
		return serviceCenterMapper.getNoticeWithPaging(cri);
	}
	
	@Override
	public void deleteNotice(@Param("notice_id")int notice_id) {
		serviceCenterMapper.deleteNotice(notice_id);
	}
	
	public void updateNotice(KTE_NoticeVO noticeVO) {
		serviceCenterMapper.updateNotice(noticeVO);
	}
}

