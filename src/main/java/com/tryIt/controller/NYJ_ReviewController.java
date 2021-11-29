package com.tryIt.controller;

import com.tryIt.domain.KKY_MemberVO;
import com.tryIt.domain.NYJ_ReviewVO;
import com.tryIt.service.KKY_MemberService;
import com.tryIt.service.NYJ_ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class NYJ_ReviewController {

    private final NYJ_ReviewService reviewService;
    private final KKY_MemberService memberService;

    @GetMapping("/productComment/{product_id}")
    public String toProductComment(Model model, @PathVariable Long product_id){
        List<NYJ_ReviewVO> reviewVOList = reviewService.getProductReviews(product_id);
        if(reviewVOList.size()!=0) {
            for (NYJ_ReviewVO reviewVO : reviewVOList) {
                reviewVO.setReview_title(memberService.findByUserId(reviewVO.getReview_id()).getUser_id());
            }
            model.addAttribute("reviewList", reviewVOList);
        }else{
            model.addAttribute("emptyList","현재 리뷰가 없습니다. 가장 먼저 리뷰를 작성해보세요!");
        }
        model.addAttribute("product_id",product_id);
        return "product-comment";
    }

    @PostMapping("/insertReview")
    public String insertReview(RedirectAttributes redirectAttributes, MultipartFile review_img, String review_content, int review_score, int product_id, HttpServletRequest request) throws IOException {
    	HttpSession httpSession = request.getSession();
        redirectAttributes.addFlashAttribute("okList", "AA BB CC");
        String referer = request.getHeader("Referer");
        String saveName = review_img.getOriginalFilename();
        String uploadPath = "C:\\Users\\노예진\\IdeaProjects\\TryIt\\src\\main\\resources\\static\\img\\review";
        File target = new File(uploadPath, saveName);
        FileCopyUtils.copy(review_img.getBytes(),target);
        KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");

        try {
            NYJ_ReviewVO reviewVO = new NYJ_ReviewVO((long) memberVO.getId(), (long)product_id,review_score,review_content,saveName);
            reviewService.insertReview(reviewVO);
        }catch(NullPointerException e){
            redirectAttributes.addFlashAttribute("msg","로그인 해야 리뷰 작성가능합니다.");
        }
        return "redirect:"+ referer;
    }

    @RequestMapping("/deleteReview/{review_id}")
    public String deleteReview(RedirectAttributes redirectAttributes,HttpServletRequest request,@PathVariable(value="review_id")Long review_id,Model model){
        HttpSession httpSession = request.getSession();
        KKY_MemberVO memberVO = (KKY_MemberVO) httpSession.getAttribute("memberVO");
        redirectAttributes.addFlashAttribute("okList", "AA BB CC");

        String referer = request.getHeader("Referer");
        NYJ_ReviewVO reviewVO = reviewService.findByReviewId(review_id);
        try {
            Long reviewId = reviewVO.getReview_id();
            Long memberId = Long.valueOf(memberVO.getId());
            if (reviewId == memberId) {
                reviewService.deleteReview(review_id);
                redirectAttributes.addFlashAttribute("msg", "성공적으로 삭제되었습니다.");
                model.addAttribute("msg", "성공적으로 삭제되었습니다.");
            } else {
                redirectAttributes.addFlashAttribute("msg","자신이 작성한 리뷰만 삭제할 수 있습니다.");
                model.addAttribute("msg", "자신이 작성한 리뷰만 삭제할 수 있습니다.");
            }
            return "redirect:" + referer;
        }catch(NullPointerException e){
            redirectAttributes.addFlashAttribute("msg","로그인 해야 리뷰 작성 및 삭제가 가능합니다.");
            return "redirect:" + referer;
        }
    }
}
