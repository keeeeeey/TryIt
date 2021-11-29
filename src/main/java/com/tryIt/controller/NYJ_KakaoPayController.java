package com.tryIt.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_OrderProductVO;
import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.service.JSW_OrderService;
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
    private JSW_OrderService orderservice;

    @Setter(onMethod_ = @Autowired)
    private NYJ_ProductService productservice;

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {

    }

    @GetMapping("/ready")
    public String kakaoPay( HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        log.info("kakaoPay post............................................"+httpSession.getAttribute("order_seq").toString());
        JSW_OrderVO orderVO = orderservice.getByOrderSeq(httpSession.getAttribute("order_seq").toString());
        int total = 0;
        for(NYJ_OrderProductVO orderProductVO:orderVO.getOrderProductList()){
            NYJ_ProductVO productVO = productservice.findProduct(orderProductVO.getProduct_id());
            total += (orderProductVO.getProduct_num() * (productVO.getProduct_price()));
        }
        orderVO.setTotal_price(total);
        return "redirect:" + kakaopay.kakaoPayReady(orderVO);

    }


    @GetMapping("/kakaoPaySuccess")
    public String kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        //update method
        return "redirect:/";
    }

    @GetMapping("/kakaoPayCancel")
    public String kakaoPayCancel(@RequestParam("pg_token") String pg_token, Model model) {
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        return "redirect:/";
    }
}

