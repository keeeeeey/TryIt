package com.tryIt.controller;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_CartListVO;
import com.tryIt.domain.NYJ_CartVO;
import com.tryIt.service.NYJ_CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class NYJ_CartController {

    private final NYJ_CartService cartService;

    @GetMapping("/cart")
    public String toCart(HttpServletRequest request, Model model){
        HttpSession httpSession = request.getSession();
        KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
        if(memberVO == null){
            return "redirect:/login-register";
        }
        else{
            List<NYJ_CartListVO> cartlist = cartService.getCartList((long) memberVO.getId());
            if(cartlist.size()==0){
                model.addAttribute("emptyList","장바구니에 상품이 없습니다");
            }else{
                model.addAttribute("cartList",cartlist);
            }
        }
        return "shop-cart";
    }

    @RequestMapping("/deleteCart/{cart_id}")
    public String deleteProduct(RedirectAttributes redirectAttributes, HttpServletRequest request, Model model, @PathVariable(value="cart_id")Long cart_id){
        HttpSession httpSession = request.getSession();
        KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
        if(memberVO == null){
            return "redirect:/login-register";
        }
        cartService.deleteCart(cart_id);
        redirectAttributes.addFlashAttribute("okList", "AA BB CC");
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }




}
