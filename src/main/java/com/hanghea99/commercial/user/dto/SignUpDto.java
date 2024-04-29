package com.hanghea99.commercial.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpDto {
    // 개인정보
    private String userName;
    private String phoneNumber;

    // 로그인 정보
    private String email; //인증 된 이메일을 함께 받아온다.
    private String password;

    // 관리자
    @Builder.Default
    private boolean isAdmin = false;
    @Builder.Default
    private String adminToken = "";

    // 주소는 회원가입 이후에 따로 등록
}
