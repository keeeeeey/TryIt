package com.tryIt.controller;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_CartListVO;
import com.tryIt.domain.NYJ_CartVO;
import com.tryIt.service.NYJ_CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                model.addAttribute("cartTotal"," ");
            }else{
                model.addAttribute("cartList",cartlist);
                model.addAttribute("cartTotal",cartService.getTotalPrice((long) memberVO.getId()));
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

    @ResponseBody
    @RequestMapping("/insertCart")
    public Map insertCart(HttpServletRequest request,String productId){
        HttpSession httpSession = request.getSession();
        int product_id = Integer.parseInt(productId);
        KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
        Map<String, Object> map = new HashMap<String, Object>();
        if(memberVO == null){
            map.put("msg","로그인 해야 이용가능합니다.");
            return map;
        }else{
            if(cartService.findById((long) product_id, (long) memberVO.getId())==null){
                cartService.insertCart((long) memberVO.getId(), (long) product_id,1);
                map.put("msg","성공적으로 추가되었습니다.");
            }else{
                map.put("msg","장바구니에 이미 존재하는 상품입니다.");
            }
            return map;
        }
    }

    @ResponseBody
    @RequestMapping("/upCart")
    public Map upCart(HttpServletRequest request,String cartId){
        HttpSession httpSession = request.getSession();
        int cart_id = Integer.parseInt(cartId);
        KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
        Map<String, Object> map = new HashMap<String, Object>();
        if(memberVO == null){
            map.put("msg","로그인 세션이 만료되었습니다.");
            return map;
        }else{
            cartService.updateNum((long)cart_id,1);
            map.put("ordertotal",cartService.getTotalPrice((long) memberVO.getId()));
            return map;
        }
    }

    @ResponseBody
    @RequestMapping("/downCart")
    public Map downCart(HttpServletRequest request,String cartId){
        HttpSession httpSession = request.getSession();
        int cart_id = Integer.parseInt(cartId);
        KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
        Map<String, Object> map = new HashMap<String, Object>();
        if(memberVO == null){
            map.put("msg","로그인 세션이 만료되었습니다.");
            return map;
        }else{
            cartService.updateNum((long)cart_id,-1);
            map.put("ordertotal",cartService.getTotalPrice((long) memberVO.getId()));
            return map;
        }
    }


    @GetMapping("/emptyCart")
    public String emptyCart(RedirectAttributes redirectAttributes,HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
        Map<String, Object> map = new HashMap<String, Object>();
        if(memberVO == null){
            return "redirect:/login-register";
        }else{
            cartService.deleteAll((long) memberVO.getId());
            redirectAttributes.addFlashAttribute("okList", "AA BB CC");
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        }

    }


}
