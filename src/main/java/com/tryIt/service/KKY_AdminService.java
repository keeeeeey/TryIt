package com.tryIt.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_Criteria;

public interface KKY_AdminService {

	List<KKY_MemberVO> userlist();
	List<KKY_MemberVO> userlistWithPaging(NYJ_Criteria cri);	
	List<KKY_MemberVO> finduser(@Param("find_id") String find_id);
	
	public void deleteUser(@Param("deletelist") String deletelist);
	int countUserlistNum();
	int countFindUserNum(@Param("find_id") String find_id);
}
