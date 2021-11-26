package com.tryIt.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tryIt.domain.KKY_KakaoOauthToken;
import com.tryIt.domain.KakaoProfile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class KKY_KakaoController {
	
    private final ObjectMapper objectMapper;
    
    public KakaoProfile getProfileInfo(String code) {

        // 3, 4 : 인증 코드를 받은 후, 위의 파라미터들을 모두 포함해 Access 토큰 요청을 보내고 응답을 받는 코드
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "112abdb9f61736f1100d276efac341d4");
        params.add("redirect_uri", "http://localhost:8089/auth/kakao/callback");
        params.add("code", code);

        // HttpHeader 오브젝트 생성
        HttpHeaders headersForAccessToken = new HttpHeaders();
        headersForAccessToken.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headersForAccessToken);

        //POST방식으로 key-value 데이터를 요청(카카오쪽으로)
        RestTemplate rt = new RestTemplate(); //http 요청을 간단하게 해줄 수 있는 클래스
        
        // 실제로 요청하기
        // Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답을 받음.
        ResponseEntity<KKY_KakaoOauthToken> accessTokenResponse = rt.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            KKY_KakaoOauthToken.class
        );

        KKY_KakaoOauthToken oauthToken = accessTokenResponse.getBody();

        // 토큰 전달 받기 완료

        // 5, 6, 7 : 발급받은 Access 토큰으로 API를 호출해서 사용자의 정보를 응답으로 받는 코드
        HttpHeaders headersForRequestProfile = new HttpHeaders();
        headersForRequestProfile.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headersForRequestProfile.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoResourceProfileRequest = new HttpEntity<>(headersForRequestProfile);

        // Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답을 받음.
        ResponseEntity<String> resourceProfileResponse = rt.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.POST,
            kakaoResourceProfileRequest,
            String.class
        );

        KakaoProfile profile = null;
        try {
            profile = objectMapper.readValue(resourceProfileResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return profile;
    }
    
//    public void kakaoLogout(KKY_KakaoOauthToken oauthToken) throws IOException {
//    	String reqURL = "https://kapi.kakao.com/v1/user/logout";
//	    try {
//	        URL url = new URL(reqURL);
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	        conn.setRequestMethod("POST");
//	        conn.setRequestProperty("Authorization", "Bearer " + oauthToken.getAccess_token());
//	        conn.setRequestProperty("client_id", "112abdb9f61736f1100d276efac341d4");
//	        conn.setRequestProperty("logout_redirect_uri", "http://localhost:8089/auth/kakao/logout");
//	        
//	        int responseCode = conn.getResponseCode();
//	        System.out.println("responseCode : " + responseCode);
//	        
//	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	        
//	        String result = "";
//	        String line = "";
//	        
//	        while ((line = br.readLine()) != null) {
//	            result += line;
//	        }
//	        System.out.println(result);
//	    } catch (IOException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	    }
//    }
    
    public void kakaoLogout(KKY_KakaoOauthToken oauthToken) {

    	 // 3, 4 : 인증 코드를 받은 후, 위의 파라미터들을 모두 포함해 Access 토큰 요청을 보내고 응답을 받는 코드
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", "112abdb9f61736f1100d276efac341d4");
        params.add("logout_redirect_uri", "http://localhost:8089/auth/kakao/logout");
    	
        //POST방식으로 key-value 데이터를 요청(카카오쪽으로)
        RestTemplate rt = new RestTemplate(); //http 요청을 간단하게 해줄 수 있는 클래스
        rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        
        // 실제로 요청하기
        // Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답을 받음.
        HttpHeaders headersForRequestLogout = new HttpHeaders();
        headersForRequestLogout.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headersForRequestLogout.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        
        HttpEntity<MultiValueMap<String, String>> kakaoResourceLogoutRequest = new HttpEntity<>(params, headersForRequestLogout);

        // Http 요청하기 - POST 방식으로 - 그리고 response 변수의 응답을 받음.
        rt.exchange(
            "https://kapi.kakao.com/v1/user/logout",
            HttpMethod.POST,
            kakaoResourceLogoutRequest,
            String.class
        );
    }
	
    @GetMapping("/auth/kakao/logout")
    public String logout(HttpSession session, KKY_KakaoOauthToken oauthToken) throws IOException {
    	kakaoLogout(oauthToken);
    	session.removeAttribute("kakaoLogin");
    	return "/login-register";
    }
    
    @GetMapping("/auth/kakao/callback")
    public String kakaoLogin(HttpSession session, String code, KKY_KakaoOauthToken oauthToken) {
    	KakaoProfile profile = getProfileInfo(code);
    	session.setAttribute("kakaoLogin", profile);
    	System.out.println(oauthToken.getAccess_token());
    	System.out.println(oauthToken.getToken_type());
    	System.out.println(oauthToken.getRefresh_token());
    	System.out.println(oauthToken.getExpires_in());
    	System.out.println(oauthToken.getScope());
    	return "redirect:/";
    }

}
