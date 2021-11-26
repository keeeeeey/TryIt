package com.tryIt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tryIt.domain.KTE_FaqVO;
import com.tryIt.domain.KTE_NoticeVO;
import com.tryIt.domain.KTE_QnAVO;
import com.tryIt.domain.NYJ_Criteria;

public interface KTE_ServiceCenterMapper {

	public List<KTE_NoticeVO> selectNotice();
	public void insertNotice(KTE_NoticeVO noticeVO);
	public KTE_NoticeVO detailNotice(@Param("notice_id") int notice_id);
	public void plusCnt(@Param("notice_id") int notice_id);
	public List<KTE_FaqVO> selectFaq(@Param("faq_category") int faq_category);
	public int countNoticeNum();
	public List<KTE_NoticeVO> getNoticeWithPaging(NYJ_Criteria cri);
	public void deleteNotice(@Param("notice_id")int notice_id);
	public void updateNotice(KTE_NoticeVO noticeVO);
	
	
	public void insertQnA(KTE_QnAVO qnaVO);
	public List<KTE_QnAVO> selectAllQnA();
	public List<KTE_QnAVO> selectQnA(@Param("qna_category") int qna_category);
	public int countQnANum();
	public int countQnACategoryNum(@Param("qna_category") String qna_category);
	public List<KTE_QnAVO> getQnAWithPaging(NYJ_Criteria cri);
	public List<KTE_QnAVO> getQnACategoryWithPaging(int pageNum, int amount, @Param("qna_category")String qna_category);
	public KTE_QnAVO detailQnA(@Param("qna_id") int qna_id);
	public void insertReply(KTE_QnAVO qnaVO);
	public void deleteReply(KTE_QnAVO qnaVO);
	public void deleteQnA(@Param("qna_id")Long qna_id);
	public void updateQnA(KTE_QnAVO qnaVO);


}
