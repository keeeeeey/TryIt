package com.tryIt.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.tryIt.domain.JSW_OrderVO;
import com.tryIt.domain.NYJ_KakaoPayReadyVO;

import lombok.extern.java.Log;

@Service
@Log
public class NYJ_KakaoPayService {

    private static final String HOST = "https://kapi.kakao.com";

    private NYJ_KakaoPayReadyVO kakaoPayReadyVO;

    public String kakaoPayReady(JSW_OrderVO orderVO) {
        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "39fed8855b6fb76878862f2d42d39f24");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        //get order total price

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", orderVO.getOrder_seq());
        params.add("partner_user_id", orderVO.getOrder_user_id().toString());
        params.add("item_name", orderVO.getOrder_seq());
        params.add("quantity", Integer.toString(orderVO.getQty()));
        params.add("total_amount", Integer.toString(orderVO.getTotal_price()));
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:8089/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8089/kakaoPayCancel");
        params.add("fail_url", "http://localhost:8089/kakaoPaySuccessFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            NYJ_KakaoPayReadyVO kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, NYJ_KakaoPayReadyVO.class);

            log.info("" + kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();

        } catch (RestClientException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return "/pay";

    }

}
