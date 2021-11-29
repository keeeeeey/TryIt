package com.tryIt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_Criteria;
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
	
	@Override
	public List<KKY_MemberVO> userlistWithPaging(NYJ_Criteria cri) {
		List<KKY_MemberVO> userlist = mapper.userlistWithPaging(cri);
		return userlist;
	}
	
	@Override
	public void deleteUser(String deletelist) {
		mapper.deleteUser(deletelist);
	}
	
	@Override
	public List<KKY_MemberVO> finduser(String find_id) {
		List<KKY_MemberVO> userlist = mapper.finduser(find_id);
		return userlist;
	}
	
	@Override
    public int countUserlistNum() {
        return mapper.countUserlistNum();
    }
	
	@Override
    public int countFindUserNum(String find_id) {
        return mapper.countFindUserNum(find_id);
    }
}
