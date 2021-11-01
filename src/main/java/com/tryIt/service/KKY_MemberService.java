package com.tryIt.service;

import org.apache.ibatis.annotations.Param;

import com.tryIt.domain.KKY_MemberVO;

public interface KKY_MemberService {

	//signup
	public void createMember(KKY_MemberVO memberVO);
	
	//login
	public int loginMember(@Param("user_id") String user_id);
		
	
}
