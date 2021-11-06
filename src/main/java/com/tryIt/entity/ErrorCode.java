package com.tryIt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // Common
    ARGUMENT_INPUT_INVALID(400, "C001", "유효하지 않은 입력입니다."),
    UNAUTHORIZED(401, "C002", "인증되지 않은 사용자입니다."),
    INTERNAL_SERVER_ERROR(500, "C002", "내부 서버 오류입니다."),

    //Member

    // Product
    PRODUCT_NOTEXIST_ERROR(400,"p100","해당 상품은 존재하지 않습니다.")
    ;

    private int status;
    private final String code;
    private final String message;
}
