package com.tryIt.service;

import java.io.IOException;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.mapper.KKY_MemberMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class KKY_MemberServiceImpl implements KKY_MemberService {

	private final KKY_MemberMapper mapper;
	
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
	public void deleteMember(String user_id, String user_pw) {
		mapper.deleteMember(user_id, user_pw);
	}

	@Override
	public KKY_MemberVO readMember(String user_id) {
		KKY_MemberVO memberVO = mapper.readMember(user_id);
		return memberVO;
	}
	
	@Override
	public KKY_MemberVO readMember2(String user_name, String user_email) {
		KKY_MemberVO memberVO = mapper.readMember2(user_name, user_email);
		return memberVO;
	}

	@Override
	public void sendEmail(KKY_MemberVO memberVO, String div) {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "sseioul@gmail.com";
		String hostSMTPpwd = "kp23156385#";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "sseioul@gmail.com";
		String fromName = "tryIt";
		String subject = "";
		String msg = "";

		if(div.equals("findpw")) {
			subject = "try'it 임시 비밀번호 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += memberVO.getUser_id() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
			msg += "<p>임시 비밀번호 : ";
			msg += memberVO.getUser_pw() + "</p></div>";
		}

		// 받는 사람 E-Mail 주소
		String mail = memberVO.getUser_email();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSLOnConnect(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setStartTLSEnabled(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}
	
	@Override
	public void sendEmail2(KKY_MemberVO memberVO, String div) {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "sseioul@gmail.com";
		String hostSMTPpwd = "kp23156385#";

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "sseioul@gmail.com";
		String fromName = "tryIt";
		String subject = "";
		String msg = "";

		if(div.equals("findid")) {
			subject = "try'it 아이디 정보 입니다.";
			msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
			msg += "<h3 style='color: blue;'>";
			msg += memberVO.getUser_name() + "님의 아이디 정보 입니다.</h3>";
			msg += "<p>아이디 : ";
			msg += memberVO.getUser_id().substring(0, memberVO.getUser_id().length() - 4) + "***";
		}

		// 받는 사람 E-Mail 주소
		String mail = memberVO.getUser_email();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSLOnConnect(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465);

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setStartTLSEnabled(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
		} catch (Exception e) {
			System.out.println("메일발송 실패 : " + e);
		}
	}

	//아이디찾기
	@Override
	public void findId(String user_name, String user_email) throws IOException {
		KKY_MemberVO memberVO = mapper.readMember2(user_name, user_email);
		// 아이디 정보 메일 발송
		sendEmail2(memberVO, "findid");
	}
 	
	//비밀번호찾기
	@Override
	public void findPw(String user_id, String user_email) throws IOException {
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			// 비밀번호 변경
			mapper.updatePw(user_id, pw);
			KKY_MemberVO memberVO = mapper.readMember(user_id);
			// 비밀번호 변경 메일 발송
			sendEmail(memberVO, "findpw");
	}

	@Override
	public KKY_MemberVO findByUserId(Long member_id) {
		return mapper.findByUserId(member_id);
	}

}
