package com.tryIt.service;

import org.apache.ibatis.annotations.Param;

import com.tryIt.domain.KKY_MemberVO;

public interface KKY_MemberService {

	//signup
	public void createMember(KKY_MemberVO memberVO);
	
	//login
	public KKY_MemberVO loginMember(@Param("user_id") String user_id, @Param("user_pw") String user_pw);
		
	
}
