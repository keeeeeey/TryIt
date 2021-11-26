package com.tryIt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.service.KKY_AdminService;

@Controller
@RequestMapping(value="/admin")
public class KKY_AdminController {

	@Autowired
	private KKY_AdminService adminService;
	
	@GetMapping("/userlist")
	public ModelAndView userListForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("account-admin-userlist");
//		KKY_MemberVO userlist = adminService.userlist();
		
		List<KKY_MemberVO> userlist = adminService.userlist();
		
		mav.addObject("userlist", userlist);
		return mav;
	}
	
}
