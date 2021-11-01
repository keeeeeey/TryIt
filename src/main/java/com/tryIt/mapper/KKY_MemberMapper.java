package com.tryIt.mapper;

import org.apache.ibatis.annotations.Param;

import com.tryIt.domain.KKY_MemberVO;

public interface KKY_MemberMapper {

//	public KKY_MemberVO getMember(@Param("user_id") String user_id) throws Exception;

	public void createMember(KKY_MemberVO memberVO);
	
	public int loginMember(@Param("user_id") String user_id);
	
}
