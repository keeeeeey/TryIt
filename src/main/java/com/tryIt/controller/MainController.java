package com.tryIt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.service.NYJ_ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final NYJ_ProductService nyj_productService;

    @GetMapping("/")
    public ModelAndView toMain(){
        ModelAndView mav = new ModelAndView("home-shop-2");

        List<NYJ_ProductVO> ten_recent_products = nyj_productService.getRecentProducts();
        List<NYJ_ProductVO> three_recent_products = ten_recent_products.subList(0,3);

        List<NYJ_ProductVO> ten_best_products = nyj_productService.getBestProducts();
        List<NYJ_ProductVO> three_best_products = ten_best_products.subList(0,3);

        List<NYJ_ProductVO> ten_high_products = nyj_productService.getHighRatedProducts();
        List<NYJ_ProductVO> three_high_products = ten_high_products.subList(0,3);

        mav.addObject("recent10",ten_recent_products);
        mav.addObject("best10",ten_best_products);
        mav.addObject("high10",ten_high_products);
        mav.addObject("recent3",three_recent_products);
        mav.addObject("best3",three_best_products);
        mav.addObject("high3",three_high_products);

        return mav;
    }

    @GetMapping("/login-register")
    public String toLoginForm() {
    	return "login-register";
    }
    
    @GetMapping("/mypage")
    public String toMyPage() {
    	return "account-profile";
    }
    
    @GetMapping("/kakaoMypage")
    public String toKakaoMyPage() {
    	return "account-kakao-profile";
    }
    
    @GetMapping("/adminpage")
    public String AdminPage() {
    	return "account-admin-profile";
    }

    @GetMapping("/test/1")
    public String toTest(){
        return "test";
    }
    
    @GetMapping("/ar")
    public String toArPage() {
    	return "AR";
    }
    
}
