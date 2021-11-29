package com.tryIt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_PageDTO;
import com.tryIt.service.KKY_AdminService;

@Controller
@RequestMapping(value="/admin")
public class KKY_AdminController {

	@Autowired
	private KKY_AdminService adminService;
	
	@PostMapping("/findId")
	public ModelAndView findId(String find_id) {
		
		ModelAndView mav = new ModelAndView("account-admin-userlist");
		List<KKY_MemberVO> userlist = adminService.finduser(find_id);
		mav.addObject("userlist", userlist);
		return mav;
	}
	
	@PostMapping("/delete.do")
	public String adminCheckPage(String userlist_id, Model model, @RequestParam(defaultValue = "1") int page) {
		String[] deletelist = userlist_id.split(",");
		
		for (int i = 0; i < deletelist.length; i++) {
			adminService.deleteUser(deletelist[i]);			
		}
		
		int totalListCnt = adminService.countUserlistNum();

        NYJ_Criteria cri = new NYJ_Criteria(page,10);
		
		List<KKY_MemberVO> userlist = adminService.userlistWithPaging(cri);
		
		model.addAttribute("userlist", userlist);
		model.addAttribute("pageMaker",new NYJ_PageDTO(cri,totalListCnt));
		return "account-admin-userlist";
	}
	
	@GetMapping("/userlist")
	public String userListForm(Model model, @RequestParam(defaultValue = "1") int page) {
		int totalListCnt = adminService.countUserlistNum();

        NYJ_Criteria cri = new NYJ_Criteria(page,10);
		
//		KKY_MemberVO userlist = adminService.userlist();
		
		List<KKY_MemberVO> userlist = adminService.userlistWithPaging(cri);
		
		model.addAttribute("userlist", userlist);
		model.addAttribute("pageMaker",new NYJ_PageDTO(cri,totalListCnt));
		return "account-admin-userlist";
	}
	
}
