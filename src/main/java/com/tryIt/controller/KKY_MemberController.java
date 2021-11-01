package com.tryIt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.service.KKY_MemberService;

@Controller
@RequestMapping(value="/member")
public class KKY_MemberController {

	@Autowired
	private KKY_MemberService memberService;
	
	@PostMapping("/register.do")
	public String register(KKY_MemberVO memberVO) {		
		memberService.createMember(memberVO);
		return "redirect:/login-register";
	}
	
	@PostMapping("/login.do")
	public String login(String user_id, HttpServletRequest request) {
		if(memberService.loginMember(user_id)==0) {	
			return "";
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("member", user_id);
			return "redirect:/";
		}
		
	}
}
