package com.tryIt.domain;

import lombok.Getter;

@Getter
public class KKY_KakaoOauthToken {
	private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;

}
