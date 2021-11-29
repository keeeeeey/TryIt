package com.tryIt.controller;

import com.tryIt.domain.*;
import com.tryIt.service.NYJ_CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tryIt.service.JSW_OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

@Controller
@RequiredArgsConstructor
public class JSW_OrderController {

	private final NYJ_CartService cartService;
	private final JSW_OrderService orderService;
	
	@GetMapping("/order-address")
	public String toOrderPage(HttpServletRequest request, Model model) {
		HttpSession httpSession = request.getSession();
		KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
		if(memberVO == null){
			return "redirect:/login-register";
		}

		//if cart is empty
		if(cartService.getCartList((long)memberVO.getId()).size()==0){
			model.addAttribute("msg","적어도 하나의 상품이 있어야 주문가능합니다");
		}
		model.addAttribute("cartTotal",cartService.getTotalPrice((long) memberVO.getId()));
		model.addAttribute("memberVO",memberVO);
		return "shop-checkout-address";
	}

	@PostMapping("/insertOrder")
	public String insertOrder(JSW_OrderVO orderVO,HttpServletRequest request){

		HttpSession httpSession = request.getSession();
		KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
		orderVO.setOrder_user_id((long) memberVO.getId());

		//generate random order sequence
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		char ch = (char) ((Math.random() * 26) + 97);
		String order_seq = ch + Integer.toString(rand.nextInt(100000));
		orderVO.setOrder_seq(order_seq);

		orderService.insertOrder(orderVO);

		httpSession.setAttribute("order_seq",order_seq);
		return "redirect:/order-review";
	}

	@GetMapping("/order-payment")
	public String toPaymentPage(HttpServletRequest request,Model model){
		HttpSession httpSession = request.getSession();
		KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
		model.addAttribute("cartTotal",cartService.getTotalPrice((long) memberVO.getId()));
		System.out.println((String) httpSession.getAttribute("order_seq"));
		JSW_OrderVO orderVO = orderService.getByOrderSeq((String) httpSession.getAttribute("order_seq"));
		Long order_id = orderVO.getId();
		for(NYJ_CartListVO cartVO:cartService.getCartList((long) memberVO.getId())){
			orderService.insertOrderProduct(new NYJ_OrderProductVO(order_id,cartVO.getProduct_id(),cartVO.getProduct_num(),0));
		}
		return "shop-checkout-payment";
	}

	@GetMapping("/order-review")
	public String toReviewPage(HttpServletRequest request, Model model, RedirectAttributes rttr){
		HttpSession httpSession = request.getSession();
		KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");

		if(memberVO == null){
			return "redirect:/login-register";
		}

		List<NYJ_CartListVO> cartlist = cartService.getCartList((long) memberVO.getId());
		model.addAttribute("cartList",cartlist);
		model.addAttribute("cartTotal",cartService.getTotalPrice((long) memberVO.getId()));

		return "shop-checkout-review";
	}

	@GetMapping("/order-shipping")
	public String toShippingPage(){
		return "shop-checkout-shipping";
	}

	@GetMapping("/order-complete")
	public String toCheckOutCompletePage(){
		return "checkout-complete";
	}

	@GetMapping("/order-tracking")
	public String toOrderTracking(){
		return "order-tracking";
	}


	@GetMapping("/account-orders")
	public String toAccountOrders(HttpServletRequest request,Model model){
		HttpSession httpSession = request.getSession();
		KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
		if(memberVO == null){
			return "redirect:/login-register";
		}
		model.addAttribute("orderList",orderService.getMemberOrder((long) memberVO.getId()));
		return "/account-orders";
	}

	@GetMapping("/order/{order_id}")
	public String toOrderDetail(@PathVariable("order_id") Long order_id,Model model){

		model.addAttribute("orderinfo",orderService.getOrderById(order_id));
		model.addAttribute("orderProductList",orderService.getOrderProductList(order_id));
		return "order-tracking-detail";

	}
}
