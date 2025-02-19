package com.wanted.preonboarding.exception.dto;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE("CM_001", "입력 값이 올바르지 않습니다.", 400),
    BALANCE_NOT_EXIST("CM_002", "잔액이 존재하지 않습니다.", 404),
    DISPLAY_NOT_FOUND("CM_003", "데이터가 존재하지 않습니다.", 404),
    SEATS_SOLD_OUT("CM_004", "해당 좌석은 매진되었습니다.", 404),
    INTERNAL_SERVER_ERROR("CM_100", "서버 에러.", 500);

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
