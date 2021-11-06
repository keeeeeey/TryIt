package com.tryIt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView login(String user_id, String user_pw, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		KKY_MemberVO memberVO = memberService.loginMember(user_id, user_pw);
		HttpSession session = request.getSession();
		if (memberVO != null) {
			if (memberVO.getUser_pw().equals(user_pw)) {
				session.removeAttribute("id");
				session.setAttribute("memberVO", memberVO);
				mav.setViewName("redirect:/");
			}
		} else {
			mav.setViewName("redirect:/login-register");
		}
		return mav;
	}
		
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
//		log.info("logout...");
		session.invalidate();
		return "redirect:/";
	}
	 
}
