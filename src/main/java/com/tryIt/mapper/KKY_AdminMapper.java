package com.tryIt.mapper;

import java.util.List;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_Criteria;

public interface KKY_AdminMapper {

	List<KKY_MemberVO> userlist();
	List<KKY_MemberVO> userlistWithPaging(NYJ_Criteria cri);
	List<KKY_MemberVO> finduser(String find_id);
	
	public void deleteUser(String deletelist);
	int countUserlistNum();
	int countFindUserNum(String find_id);
}
