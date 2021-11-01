package com.tryIt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView toMain(){
        ModelAndView mav = new ModelAndView("home-shop-2");
        return mav;
    }

    @GetMapping("/login-register")
    public String toLoginForm() {
    	return "login-register";
    }
}
