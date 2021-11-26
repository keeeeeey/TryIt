package com.tryIt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.mapper.KKY_AdminMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KKY_AdminServiceImpl implements KKY_AdminService {

	private KKY_AdminMapper mapper;
	
	@Override
	public List<KKY_MemberVO> userlist() {
		List<KKY_MemberVO> userlist = mapper.userlist();
		return userlist;
	}
	
}
