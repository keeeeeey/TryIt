package com.tryIt.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.google.gson.JsonObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tryIt.domain.KTE_NoticeVO;
import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_PageDTO;
import com.tryIt.service.KTE_ServiceCenterService;

@Controller
 public class KTE_ServiceCenterController {

	@Autowired
	private KTE_ServiceCenterService serviceCenterService;
	
	@PostMapping("write")
	public String write(KTE_NoticeVO noticeVO) {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" , Locale.KOREA );
		String str = sdf.format(timestamp);
		noticeVO.setNotice_date(str);
		serviceCenterService.insertNotice(noticeVO);
		return "redirect:/service-center";
	}
	
	@PostMapping("modify")
	public String modify(KTE_NoticeVO noticeVO) {

		serviceCenterService.updateNotice(noticeVO);
		return "redirect:/service-center";
	}

	@GetMapping("/service-center/write")
    public String toServiceCenter_write() {
    	return "service-center_write";
    }
	
	@GetMapping("/service-center_qna/write")
    public String toQnA_write() {
    	return "service-center_qna_write";
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
		
		String faqList[] = {"faqList_1","faqList_2","faqList_3","faqList_4"};
		
		for(int i=0;i<4;i++) {
			model.addAttribute(faqList[i],serviceCenterService.selectFaq(i+1));
		}
		return "service-center_faq";
	}
	
	@GetMapping("/service-center_qna")
	public String toQnA(Model model) {
	
		return "service-center_qna";
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
	
	
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
    @ResponseBody
    public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {

		
        JsonObject jsonObject = new JsonObject();

        String fileRoot = "C:\\summernote_image\\";	//저장될 파일 경로
        String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자

        // 랜덤 UUID+확장자로 저장될 savedFileName
        String savedFileName = UUID.randomUUID() + extension;	
        
        File targetFile = new File(fileRoot + savedFileName);

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
            jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
            jsonObject.addProperty("responseCode", "success");

        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);	// 실패시 저장된 파일 삭제
            jsonObject.addProperty("responseCode", "error");
            e.printStackTrace();
        }

        return jsonObject;
    }

	
	
}
