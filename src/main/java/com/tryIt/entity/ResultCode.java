package com.tryIt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    // Product
    PRODUCT_REGISTER_SUCCESS(200, "P100" ,"상품이 성공적으로 등록되었습니다."),
    PRODUCT_GET_SUCCESS(200, "P101" ,"상품을 성공적으로 불러왔습니다."),
    PRODUCT_UPDATE_SUCCESS(200, "P102" ,"상품을 성공적으로 수정했습니다."),
    PRODUCT_DELETE_SUCCESS(200, "P103" ,"상품을 성공적으로 삭제했습니다.")
    ;

    //Member

    private int status;
    private final String code;
    private final String message;
}
