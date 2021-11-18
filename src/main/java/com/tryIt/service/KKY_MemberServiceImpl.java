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
	public void updateMember(KKY_MemberVO memberVO) {
		mapper.updateMember(memberVO);
	}
	
	@Override
	public KKY_MemberVO loginMember(String user_id, String user_pw) {
		KKY_MemberVO memberVO = mapper.loginMember(user_id, user_pw);
		return memberVO;
	}
	
	@Override
	public String overLappedID(String user_id) {
		String result = mapper.overLappedID(user_id);
		return result;
		
	}
	
	@Override
	public String overLappedNickName(String user_nickname) {
		String result = mapper.overLappedNickName(user_nickname);
		return result;
	}

	@Override
	public KKY_MemberVO findByUserId(Long member_id) {
		return mapper.findByUserId(member_id);
	}
}
