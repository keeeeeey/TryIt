package com.tryIt.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tryIt.domain.KTE_FaqVO;
import com.tryIt.domain.KTE_NoticeVO;
import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_PageDTO;
import com.tryIt.service.KTE_ServiceCenterService;

@Controller
 public class KTE_ServiceCenterController {

	@Autowired
	private KTE_ServiceCenterService serviceCenterService;
	
	//공지사항 db저장
	@PostMapping("write")
	public String write(KTE_NoticeVO noticeVO) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" , Locale.KOREA );
		String str = sdf.format(timestamp);
		noticeVO.setNotice_date(str);
		serviceCenterService.insertNotice(noticeVO);
		return "redirect:/service-center";
	}
	
	//공지사항 db저장
	@PostMapping("modify")
	public String modify(KTE_NoticeVO noticeVO) {

		serviceCenterService.updateNotice(noticeVO);
		return "redirect:/service-center";
	}

	//공지사항 작성 페이지로 이동
	@GetMapping("/service-center/write")
    public String toServiceCenter_write() {
    	return "service-center_write";
    }
	
	
	@GetMapping("/service-center")
    public String toServiceCenter(Model model,@RequestParam(defaultValue = "1") int page) {
		
	    int totalListCnt= serviceCenterService.countNoticeNum();
	    NYJ_Criteria cri = new NYJ_Criteria(page,7);
		 
//	    List<KTE_NoticeVO> noticeList = serviceCenterService.selectNotice();
	    List<KTE_NoticeVO> noticeList = serviceCenterService.getNoticeWithPaging(cri);
	    
			    
	    model.addAttribute("noticeList",noticeList);
	    model.addAttribute("pageMaker",new NYJ_PageDTO(cri,totalListCnt));
    	return "service-center";
    }
	
	/*
	 * @GetMapping("/help-faq") public String toShop() {
	 * 
	 * return "help-faq"; }
	 */
	
	@GetMapping("/service-center/view")
	public String openNoticeDetail(@RequestParam(value="idx", required=false) int idx, Model model) {

		KTE_NoticeVO notice = serviceCenterService.detailNotice(idx);
	    model.addAttribute("notice",notice);
	    serviceCenterService.plusCnt(idx);
		return "service-center_detail";
	}
	
	@GetMapping("/service-center_faq")
	public String toFAQ(Model model) {
		
		List<KTE_FaqVO> faqList_1 = serviceCenterService.selectFaq(1);
		List<KTE_FaqVO> faqList_2 = serviceCenterService.selectFaq(2);
		model.addAttribute("faqList_1",faqList_1);
		model.addAttribute("faqList_2",faqList_2);
		return "service-center_faq";
	}
	
	@PostMapping("/service-center/delete")
	public String deleteNotice(KTE_NoticeVO noticeVO) {
		
		serviceCenterService.deleteNotice(noticeVO.getNotice_id());
		return "redirect:/service-center";
	}
	
	@PostMapping("/service-center/modify")
	public String modifyNotice(KTE_NoticeVO noticeVO,Model model) {
		model.addAttribute("notice",noticeVO);
		return "service-center_modify";
	}
	
}
