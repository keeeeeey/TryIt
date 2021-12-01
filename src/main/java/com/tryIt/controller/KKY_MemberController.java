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

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.service.KKY_MemberService;

@Controller
@RequestMapping(value="/member")
public class KKY_MemberController {

	@Autowired
	private KKY_MemberService memberService;
	
	@GetMapping("/conditions.do")
	public String conditions() {
		return "privacy-policy";
	}
	
	@GetMapping("/deleteForm.do")
	public String deleteForm() {
		return "account-drop";
	}
	
	@GetMapping("/admin-deleteForm.do")
	public String admin_deleteForm() {
		return "account-admin-drop";
	}
	
	@GetMapping("/account-recovery")
	public String accountRecovery() {
		return "account-recovery";
	}
	
	@GetMapping("/id-recovery")
	public String idRecovery() {
		return "account-id-recovery";
	}
	
	@GetMapping("/password-recovery")
	public String passwordRecovery() {
		return "account-password-recovery";
	}
	
	@PostMapping("/findId")
	public void findId(String user_name, String user_email, HttpServletResponse response) throws IOException {
		KKY_MemberVO memberVO = memberService.readMember2(user_name, user_email);
		PrintWriter writer = response.getWriter();
		// 회원정보가 없으면
		if (memberVO == null) {
			writer.print("null_memberVO");
		} 
		// 회원정보가 있으면
		else {
			memberService.findId(user_name, user_email);
			writer.print("successSendEmail2");
		}
	}
	
	@PostMapping("/findPw")
	public void findPw(String user_id, String user_email, HttpServletResponse response) throws IOException {
		KKY_MemberVO ck = memberService.readMember(user_id);
		PrintWriter writer = response.getWriter();
		// 가입된 아이디가 없으면
		if (memberService.overLappedID(user_id).equals("false")) {
			writer.print("null_id");
		}
		// 가입된 이메일이 아니면
		else if (!user_email.equals(ck.getUser_email())) {
			writer.print("null_email");
		} 
		// 정보가 일치하면
		else {
			memberService.findPw(user_id, user_email);
			writer.print("successSendEmail");
		}
	}
	
	@PostMapping("/delete.do")
	public void delete(String user_id, String user_pw, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		PrintWriter writer = response.getWriter();
		KKY_MemberVO memberVO = (KKY_MemberVO)session.getAttribute("memberVO");
		if (memberVO.getUser_pw().equals(user_pw)) {
			memberService.deleteMember(user_id, user_pw);
			session.removeAttribute("memberVO");
			writer.print("deleteSuccess");
		} else {
			writer.print("deleteFail");
		}
		
	}
	
	@PostMapping("/register.do")
	public String register(KKY_MemberVO memberVO) {
		memberService.createMember(memberVO);
		return "redirect:/login-register";
	}
	
	@PostMapping("/login.do")
	public void login(String user_id, String user_pw, HttpServletRequest request, HttpServletResponse response) throws IOException {
		KKY_MemberVO memberVO = memberService.loginMember(user_id, user_pw);
		HttpSession session = request.getSession();
		PrintWriter writer = response.getWriter();
		if (memberVO != null) {
			session.setAttribute("memberVO", memberVO);
			writer.print("loginsuccess");
		} else {
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
		session.removeAttribute("memberVO");
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
