package com.tryIt.controller;

import com.tryIt.domain.NYJ_Criteria;
import com.tryIt.domain.NYJ_PageDTO;
import com.tryIt.domain.NYJ_ProductVO;
import com.tryIt.service.NYJ_ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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
        model.addAttribute("productlist",relateProducts);
        return "shop-product-detail";
    }

    @GetMapping("/productlist")
    public String toProductList(Model model,@RequestParam(defaultValue = "1") int page){
        int totalListCnt = nyj_productService.countProductNum();

        NYJ_Criteria cri = new NYJ_Criteria(page,10);
        //criteria, pagedto => criteria amount 를 custom해서 사용!!
        model.addAttribute("productlist",nyj_productService.getProductsWithPaging(cri));
        model.addAttribute("pageMaker",new NYJ_PageDTO(cri,totalListCnt));
        return "shop-product-list";
    }

    @GetMapping("/product/search")
    public String toProductSearch(@RequestParam(value = "keyword") String keyword,Model model){
        model.addAttribute("productlist",nyj_productService.getSearchProducts(keyword));
        return "shop-product-list";
    }

    @GetMapping("/productComment")
    public String toProductComment(){
        return "product-comment";
    }
}
