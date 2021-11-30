package com.tryIt.controller;

import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_PageDTO;
import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.service.NYJ_ProductService;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class NYJ_ProductController {

    private final NYJ_ProductService nyj_productService;

    @RequestMapping("/products/{product_id}")
    public String toProductDetail(Model model,@PathVariable("product_id") int product_id){
        NYJ_ProductVO productVO2 = nyj_productService.findProduct((long) product_id);
        model.addAttribute("product",productVO2);
        String category = productVO2.getProduct_category();
        List<NYJ_ProductVO> relateProducts = nyj_productService.getRelateProducts(category);
        for(NYJ_ProductVO productVO : relateProducts){
            if(productVO.getId()==productVO2.getId()){
                relateProducts.remove(productVO);
                break;
            }
        }
        model.addAttribute("product_id",product_id);
        model.addAttribute("productlist",relateProducts);
        return "shop-product-detail";
    }

    @GetMapping("/productlist")
    public String toProductList(Model model,@RequestParam(defaultValue = "1") int page){
        int totalListCnt = nyj_productService.countProductNum();

        NYJ_Criteria cri = new NYJ_Criteria(page,10);
        //criteria, pagedto => criteria amount 를 custom해서 사용!!
        //buffer가 느리게 오도록 => response를 지연시켜야
        //현재: buffer채워지기 전에 client로 온 것 같음
        model.addAttribute("pageMaker",new NYJ_PageDTO(cri,totalListCnt));
        model.addAttribute("productlist",nyj_productService.getProductsWithPaging(cri));

        return "shop-product-list";
    }

    @GetMapping("/categoryProduct")
    public String toCategoryProduct(){
        return null;
    }

    @GetMapping("/productsearch")
    public String toProductSearch(@RequestParam(defaultValue = "1") int page,@RequestParam(name = "keyword") String keyword,@RequestParam(name = "category") String category, Model model){
        List<NYJ_ProductVO> productVOList = new ArrayList<NYJ_ProductVO>();
        if(category.equals("all")){
            productVOList = nyj_productService.getSearchProducts(keyword);
        }else{
            productVOList = nyj_productService.getSearchProductsCategory(keyword, category);
        }
        NYJ_Criteria cri = new NYJ_Criteria(page,10);
        if(productVOList.size()==0){
            model.addAttribute("emptyList","검색 조건에 해당하는 상품이 없습니다.");
            model.addAttribute("recommend","이런 상품은 어때요?");
            List<NYJ_ProductVO> ten_best_products = nyj_productService.getBestProducts();
            List<NYJ_ProductVO> three_best_products = ten_best_products.subList(0,3);
            model.addAttribute("productlist",three_best_products);
        }else {
            model.addAttribute("productlist", productVOList);
        }
        model.addAttribute("pageMaker", new NYJ_PageDTO(cri,10));
        return "shop-product-list";
    }
    
    @GetMapping("/ar")
    public String toArPage(Model model) {
        model.addAttribute("productlist", nyj_productService.findAllProducts());
    	return "AR";
    }


}
