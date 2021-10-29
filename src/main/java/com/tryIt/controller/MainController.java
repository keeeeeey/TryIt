package com.tryIt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @GetMapping("/")
    public ModelAndView toMain(){
        ModelAndView mav = new ModelAndView("home-shop-1");
        return mav;
    }


}
