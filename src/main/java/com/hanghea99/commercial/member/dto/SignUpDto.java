package com.hanghea99.commercial.member.dto;

import lombok.Data;

@Data
public class SignUpDto {
    // 개인정보
    String memberName;
    String phoneNumber;

    // 로그인 정보
    String email; //인증 된 이메일을 함께 받아온다.
    String password;

    // 주소는 회원가입 이후에 따로 등록
}
