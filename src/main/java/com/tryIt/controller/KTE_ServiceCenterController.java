package com.tryIt.controller;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.KTE_NoticeVO;
import com.tryIt.domain.KTE_QnAVO;
import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_OrderProductVO;
import com.tryIt.domain.NYJ_PageDTO;
import com.tryIt.service.JSW_OrderService;
import com.tryIt.service.KTE_ServiceCenterService;
import com.tryIt.service.NYJ_ProductService;

@Controller
 public class KTE_ServiceCenterController {

	@Autowired
	private KTE_ServiceCenterService serviceCenterService;
	@Autowired
	private JSW_OrderService orderService;
	@Autowired
	private NYJ_ProductService productService;
	
	@GetMapping("/admin/orderlist")
	public String admin_orderlist(Model model, @RequestParam(defaultValue = "1") int page) {
		
		int totalListCnt= orderService.countOrderNum();
	    NYJ_Criteria cri = new NYJ_Criteria(page,7);
		List<JSW_OrderVO> orderList = orderService.getOrderWithPaging(cri);

		/* long num = totalListCnt-((page-1)*7); */
		
		
		for(JSW_OrderVO order : orderList) {
			
			order.setOrder_date(order.getOrder_date().split(" ")[0]);
			/*
			 * order.setNum(num); num--;
			 */
		}
		
		model.addAttribute("orderList",orderList);
	    model.addAttribute("pageMaker",new NYJ_PageDTO(cri,totalListCnt));
	    
	    
		return "admin-orderlist";
	}
	
	@GetMapping("/admin/orderlist/delete")
	public String order_delete(Long id) {
		
		orderService.deleteOrderProduct(id);
		orderService.deleteOrder(id);
		return "redirect:/admin/orderlist";
	}
	
	
	
	@GetMapping("/admin/orderlist/view")
	public String admin_order_detail(Long id, Model model, String modify) {
		
		String[] deliver = {"주문접수","입금확인","출고처리중", "출고완료", "배송시작","배송완료","구매확정","결제오류","주문취소"};
		
		JSW_OrderVO order = orderService.getOrderById(id);
		model.addAttribute("seq","#"+order.getOrder_seq());
		
		List<NYJ_OrderProductVO> order_productList = orderService.getOrderProducts(id);
		
		
		for(NYJ_OrderProductVO orderProduct : order_productList) {
			orderProduct.setProduct_deliver_message(deliver[orderProduct.getProduct_deliver()]);
			orderProduct.setProduct_name(productService.findProduct(orderProduct.getProduct_id()).getProduct_name());
		}
		model.addAttribute("id",id);
		model.addAttribute("order_productList",order_productList);

		if(modify.equals("true")) 
			model.addAttribute("modify",modify);
		else
			model.addAttribute("modify","false");
		
			
		return "admin-order-detail";
	}
	
	@PostMapping("modify_order")
	public void modify_order(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		Long order_id = Long.parseLong(request.getParameter("order_id"));
		Long product_id = Long.parseLong(request.getParameter("product_id"));
		String product_deliver= request.getParameter("product_deliver");
		
		String product_deliver_num = "";
		

		switch(product_deliver) {
		
		case "주문접수":
			product_deliver_num = "0";
			break;
		case "입금확인":
			product_deliver_num = "1";
			break;
		case "출고처리중":
			product_deliver_num = "2";
			break;
		case "출고완료":
			product_deliver_num = "3";
			break;
		case "배송시작":
			product_deliver_num = "4";
			break;
		case "배송완료":
			product_deliver_num = "5";
			break;
		case "구매확정":
			product_deliver_num = "6";
			break;
		case "결제오류":
			product_deliver_num = "7";
			break;
		case "주문취소":
			product_deliver_num = "8";
			break;
			
		}
		
		orderService.updateOrderProduct(order_id, product_id, product_deliver_num);
		}

	
	
	
	
	
	@GetMapping("/account_orders")
	public String account_orders() {
		
		
		return "account-orders";
	}
	
	@PostMapping("write")
	public String write(KTE_NoticeVO noticeVO) {

		serviceCenterService.insertNotice(noticeVO);
		return "redirect:/service-center";
	}

	
	@PostMapping("qna_write")
	public String qna_write(KTE_QnAVO qnaVO, HttpSession session) {
		KKY_MemberVO memberVO = (KKY_MemberVO)session.getAttribute("memberVO");
		
		Long memberId = Long.valueOf(memberVO.getId());
		qnaVO.setUser_id(memberId);
		qnaVO.setQna_writer(memberVO.getUser_nickname());
		serviceCenterService.insertQnA(qnaVO);
		return "redirect:/service-center_qna";
		
	}
	
	@PostMapping("reply_write")
	public String reply_write(KTE_QnAVO qnaVO) {

		serviceCenterService.insertReply(qnaVO);
		return "redirect:/service-center_qna/view?idx="+ qnaVO.getQna_id();
	}
	
	@PostMapping("reply_delete")
	public String reply_delete(KTE_QnAVO qnaVO) {

		serviceCenterService.deleteReply(qnaVO);
		return "redirect:/service-center_qna/view?idx="+ qnaVO.getQna_id();
	}
	
	
	
	@PostMapping("modify")
	public String modify(KTE_NoticeVO noticeVO) {

		serviceCenterService.updateNotice(noticeVO);
		return "redirect:/service-center";
	}
	
	@PostMapping("qna_modify")
	public String qna_modify(KTE_QnAVO qnaVO) {

		serviceCenterService.updateQnA(qnaVO);
		return "redirect:/service-center_qna";
	}


	@GetMapping("/service-center/write")
    public String toServiceCenter_write() {
    	return "service-center_write";
    }
	
	
	@GetMapping("/service-center_qna/write")
    public String toQnA_write(HttpSession session) {
		KKY_MemberVO memberVO = (KKY_MemberVO)session.getAttribute("memberVO");
		if(memberVO == null)
			return "redirect:/login-register";
		else
			return "service-center_qna_write";
		
    }
	
	@GetMapping("/service-center")
    public String toServiceCenter(Model model,@RequestParam(defaultValue = "1") int page, HttpSession session) {
		
	    int totalListCnt= serviceCenterService.countNoticeNum();
	    NYJ_Criteria cri = new NYJ_Criteria(page,7);
	    List<KTE_NoticeVO> noticeList = serviceCenterService.getNoticeWithPaging(cri);
	   
	    int num = totalListCnt-((page-1)*7);
	    
		for(KTE_NoticeVO notice : noticeList) {
			//날짜만 출력
			notice.setNotice_date(notice.getNotice_date().split(" ")[0]);
			notice.setNotice_num(num);
			num--;
		}
			    
	    model.addAttribute("noticeList",noticeList);
	    model.addAttribute("pageMaker",new NYJ_PageDTO(cri,totalListCnt));
	    
	    
	    KKY_MemberVO memberVO = (KKY_MemberVO)session.getAttribute("memberVO");
	    if(memberVO == null || memberVO.getRole().equals("user") )
	    	model.addAttribute("role","user");
	    
	    else 
	    	model.addAttribute("role","admin");
	    
    	return "service-center";
    }
	
	
	@GetMapping("/service-center/view")
	public String openNoticeDetail(@RequestParam(value="idx", required=false) int idx, Model model, HttpSession session) {

		KTE_NoticeVO notice = serviceCenterService.detailNotice(idx);
		notice.setNotice_date(notice.getNotice_date().split(" ")[0]);
	    model.addAttribute("notice",notice);
	    serviceCenterService.plusCnt(idx);
	    
	    KKY_MemberVO memberVO = (KKY_MemberVO)session.getAttribute("memberVO");
	    if(memberVO == null || memberVO.getRole().equals("user"))
	    	model.addAttribute("role","user");
	    else
	    	model.addAttribute("role","admin");
	    
		return "service-center_detail";
	}
	
	
	@GetMapping("/service-center_qna/view")
	public String openQnADetail(@RequestParam(value="idx", required=false) int idx, Model model,  HttpSession session) throws Exception {

		KTE_QnAVO qna = serviceCenterService.detailQnA(idx);
		qna.setQna_date(qna.getQna_date().split(" ")[0]);
	    model.addAttribute("qna",qna);
	    
		KKY_MemberVO memberVO = (KKY_MemberVO)session.getAttribute("memberVO");
	    if(memberVO == null)
	    	model.addAttribute("role","null");
	    else if(qna.getUser_id().equals(Long.valueOf(memberVO.getId()))) {
	    	model.addAttribute("role","writer");
	    }
	    else if(memberVO.getRole().equals("admin")) {
	    	model.addAttribute("role","admin");
	    }
	    else
	    	model.addAttribute("role","notWriter");
		    
		
		return "service-center_qna_detail";
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
	public String toQnA(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(value="qna_category", required=false) String qna_category, HttpSession session) {
	
		int totalListCnt= serviceCenterService.countQnANum();
		NYJ_Criteria cri = new NYJ_Criteria(page,7);
	    List<KTE_QnAVO> qnaList = serviceCenterService.getQnAWithPaging(cri);
		KKY_MemberVO memberVO = (KKY_MemberVO)session.getAttribute("memberVO");
	    
		if(qna_category!=null) {
			totalListCnt= serviceCenterService.countQnACategoryNum(qna_category);
		    qnaList = serviceCenterService.getQnACategoryWithPaging(cri.getPageNum(),cri.getAmount(), qna_category);
		    model.addAttribute("qna_category",qna_category);
		}
		else {
			model.addAttribute("qna_category","전체");
		}
	    
		int num = totalListCnt-((page-1)*7);
		
		if(memberVO == null) {
			for(KTE_QnAVO qna : qnaList) {
				//날짜만 출력
				qna.setQna_date(qna.getQna_date().split(" ")[0]);
				qna.setQna_num(num);
				num--;
				if(qna.getQna_secret().equals("Y"))
					qna.setQna_viewYn("N");
				else
					qna.setQna_viewYn("Y");
			}
		}
		else if(memberVO.getRole().equals("admin")) {
			for(KTE_QnAVO qna : qnaList) {
				//날짜만 출력
				qna.setQna_date(qna.getQna_date().split(" ")[0]);
				qna.setQna_num(num);
				num--;
				qna.setQna_viewYn("Y");
			}
		}
		else {
			for(KTE_QnAVO qna : qnaList) {
				//날짜만 출력
				qna.setQna_date(qna.getQna_date().split(" ")[0]);
				qna.setQna_num(num);
				num--;
				if(qna.getUser_id().equals(Long.valueOf(memberVO.getId()))||qna.getQna_secret().equals("N"))
					qna.setQna_viewYn("Y");
				else
					qna.setQna_viewYn("N");
			}
		}
		
		model.addAttribute("pageMaker",new NYJ_PageDTO(cri,totalListCnt));
		model.addAttribute("qnaList",qnaList);
		
		return "service-center_qna";
	}
	
	
	
	@PostMapping("/service-center/delete")
	public String deleteNotice(KTE_NoticeVO noticeVO) {
		
		serviceCenterService.deleteNotice(noticeVO.getNotice_id());
		return "redirect:/service-center";
	}
	
	@PostMapping("/service-center_qna/delete")
	public String deleteQnA(KTE_QnAVO qnaVO) {
		
		serviceCenterService.deleteQnA(qnaVO.getQna_id());
		return "redirect:/service-center_qna";
	}
	
	@PostMapping("/service-center/modify")
	public String modifyNotice(KTE_NoticeVO noticeVO,Model model) {
		model.addAttribute("notice",noticeVO);
		return "service-center_modify";
	}
	
	@PostMapping("/service-center_qna/modify")
	public String modifyQnA(KTE_QnAVO qnaVO,Model model) {
		model.addAttribute("qna",qnaVO);
		return "service-center_qna_modify";
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
