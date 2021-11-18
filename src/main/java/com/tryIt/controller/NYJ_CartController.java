package com.tryIt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NYJ_CartController {

    @GetMapping("/cart")
    public String toCart(){
        return "shop-cart";
    }


}
