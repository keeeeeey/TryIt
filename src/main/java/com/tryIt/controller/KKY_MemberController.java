package com.tryIt.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public void login(String user_id, String user_pw, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		ModelAndView mav = new ModelAndView();
		KKY_MemberVO memberVO = memberService.loginMember(user_id, user_pw);
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		if (memberVO != null) {
//			if (memberVO.getUser_pw().equals(user_pw)) {
//				session.removeAttribute("loginFail");
				session.setAttribute("memberVO", memberVO);
//				mav.setViewName("redirect:/");
				writer.print("loginsuccess");
//			}
		} else {
//			session.invalidate();
//			request.setAttribute("loginFail", "존재하지 않는 아이디이거나 비밀번호가 일치하지 않습니다.");
//			mav.setViewName("redirect:/login-register");
//			response.sendRedirect("/login-register");
			writer.print("loginFail");
		}
	
	}
	
	@PostMapping("/update.do")
	public String update(KKY_MemberVO memberVO, HttpServletRequest request) {
		memberService.updateMember(memberVO);
		HttpSession session = request.getSession();
		session.removeAttribute("memberVO");
		return "redirect:/login-register";
	}
		
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
//		log.info("logout...");
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("overlappedID.do")
	public void overlappedID(String user_id, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		String result = memberService.overLappedID(user_id);
		if (result.equals("true")) {
			writer.print("not_usable");
		} else {
			writer.print("usable");
		}
			
	}
	
	@PostMapping("overlappedNickName.do")
	public void overlappedNickName(String user_nickname, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		String result = memberService.overLappedNickName(user_nickname);
		if (result.equals("true")) {
			writer.print("not_usable");
		} else {
			writer.print("usable");
		}
			
	}
}
