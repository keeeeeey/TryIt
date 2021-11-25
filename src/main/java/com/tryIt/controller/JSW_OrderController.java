package com.tryIt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tryIt.service.JSW_OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JSW_OrderController {

	
	@GetMapping("/order-address")
	public String toOrderPage() {
		return "shop-checkout-address";
	}
}
