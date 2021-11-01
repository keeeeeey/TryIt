package com.tryIt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.mapper.KKY_MemberMapper;

@Service
public class KKY_MemberServiceImpl implements KKY_MemberService {

	@Autowired
	private KKY_MemberMapper mapper;
	
	@Override
	public void createMember(KKY_MemberVO memberVO) {
		mapper.createMember(memberVO);
	}
	
	@Override
	public int loginMember(String user_id) {
		int result = mapper.loginMember(user_id);
		return result;
	}
	
}
