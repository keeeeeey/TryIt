package com.tryIt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryIt.domain.KTE_FaqVO;
import com.tryIt.domain.KTE_NoticeVO;
import com.tryIt.domain.KTE_QnAVO;
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
	
	@Override
	public void updateNotice(KTE_NoticeVO noticeVO) {
		serviceCenterMapper.updateNotice(noticeVO);
	}
	
	@Override
	public void insertQnA(KTE_QnAVO qnaVO) {
		serviceCenterMapper.insertQnA(qnaVO);
	}
	
	@Override
	public List<KTE_QnAVO> selectAllQnA(){
		return serviceCenterMapper.selectAllQnA();
	}
	
	@Override
	public List<KTE_QnAVO> selectQnA(@Param("qna_category") int qna_category){
		return serviceCenterMapper.selectQnA(qna_category);
	}
	
	@Override
	public int countQnANum() {
		return serviceCenterMapper.countQnANum();
	}
	@Override
	public List<KTE_QnAVO> getQnAWithPaging(NYJ_Criteria cri){
		return serviceCenterMapper.getQnAWithPaging(cri);
	}
	@Override
	public KTE_QnAVO detailQnA(@Param("qna_id") int qna_id) {
		return serviceCenterMapper.detailQnA(qna_id);
	}

	@Override
	public void insertReply(KTE_QnAVO qnaVO) {
		serviceCenterMapper.insertReply(qnaVO);
	}
	
	@Override
	public int countQnACategoryNum(@Param("qna_category") String qna_category) {
		return serviceCenterMapper.countQnACategoryNum(qna_category);
	}
	
	@Override
	public List<KTE_QnAVO> getQnACategoryWithPaging(int pageNum, int amount, @Param("qna_category")String qna_category){
		return serviceCenterMapper.getQnACategoryWithPaging(pageNum, amount, qna_category);
	}
	
	@Override
	public void deleteQnA(@Param("qna_id")Long qna_id) {
		serviceCenterMapper.deleteQnA(qna_id);
	}
	
	@Override
	public void updateQnA(KTE_QnAVO qnaVO) {
		serviceCenterMapper.updateQnA(qnaVO);
	}
	
	@Override
	public void deleteReply(KTE_QnAVO qnaVO) {
		serviceCenterMapper.deleteReply(qnaVO);
	}
}

