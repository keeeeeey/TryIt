package com.tryIt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.service.NYJ_ProductService;

@Controller
public class KTE_AgeGenderController{
	
	@Autowired
	private  NYJ_ProductService nyj_productService;
	
	
	@GetMapping("/ageGender")
	public String ageGender() {
		return "ageGender";
	}
	
	@PostMapping("/ageGenderFind")
	public String ageGenderFindPost(String age, String gender, Model model) {
		System.out.println("age = "+age+"gender = "+gender);
		
		long AGE = Math.round(Double.parseDouble(age));
		List<NYJ_ProductVO> productList = new ArrayList<>();
		
		if(AGE<10)
		{	//키즈 안경
			productList = nyj_productService.getRelateProducts("키즈선글라스");
		}
		else if(gender.equals("male")) {
			//남성 안경
			productList = nyj_productService.getRelateProducts("남성선글라스");
		}
		else {
			//여성 안경
			productList = nyj_productService.getRelateProducts("여성선글라스");
		}
		
		model.addAttribute("productList",productList);
		
		return "AR";
	}
}
