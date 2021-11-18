package com.tryIt.mapper;

import org.apache.ibatis.annotations.Param;

import com.tryIt.domain.KKY_MemberVO;

public interface KKY_MemberMapper {

	public void createMember(KKY_MemberVO memberVO);
	
	public KKY_MemberVO loginMember(@Param("user_id") String user_id, @Param("user_pw") String user_pw);
	
	public void updateMember(KKY_MemberVO memberVO);
	
	public String overLappedID(@Param("user_id") String user_id);
	
	public String overLappedNickName(@Param("user_nickname") String user_nickname);

	public KKY_MemberVO findByUserId(Long member_id);
}
