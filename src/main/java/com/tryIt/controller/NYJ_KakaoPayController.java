package com.tryIt.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_OrderProductVO;
import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.service.JSW_OrderService;
import com.tryIt.service.NYJ_CartService;
import com.tryIt.service.NYJ_KakaoPayService;
import com.tryIt.service.NYJ_ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;
import lombok.extern.java.Log;

@Log
@Controller
public class NYJ_KakaoPayController {

    @Setter(onMethod_ = @Autowired)
    private NYJ_KakaoPayService kakaopay;

    @Setter(onMethod_ = @Autowired)
    private JSW_OrderService orderService;

    @Setter(onMethod_ = @Autowired)
    private NYJ_ProductService productservice;

    @Setter(onMethod_ = @Autowired)
    private NYJ_CartService cartservice;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @GetMapping("/ready")
    public String kakaoPay( HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        log.info("kakaoPay post............................................"+httpSession.getAttribute("order_seq").toString());
        JSW_OrderVO orderVO = orderService.getByOrderSeq((String) httpSession.getAttribute("order_seq"));

        int price = 0;
        int qty = 0;
        for(NYJ_OrderProductVO orderProductVO:orderService.getOrderProducts(orderVO.getId())){
            NYJ_ProductVO productVO = productservice.findProduct(orderProductVO.getProduct_id());
            price = price + (orderProductVO.getProduct_num() * (productVO.getProduct_price()));
            qty = qty + orderProductVO.getProduct_num();
        }

        orderVO.setTotal_price(price);
        orderVO.setQty(qty);
        return "redirect:" + kakaopay.kakaoPayReady(orderVO);

    }


    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model,HttpServletRequest request) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        HttpSession httpSession = request.getSession();
        orderService.updateOrder("Y",(String) httpSession.getAttribute("order_seq"));
        KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
        cartservice.deleteAll((long) memberVO.getId());

        return "redirect:/order-complete";
    }

    @GetMapping("/kakaoPayCancel")
    public String kakaoPayCancel(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        return "redirect:/";
    }
}

