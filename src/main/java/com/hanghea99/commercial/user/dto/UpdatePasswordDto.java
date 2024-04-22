package com.hanghea99.commercial.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePasswordDto {
    //pk
    private UUID memberId;

    // 기존 비밀번호
    private String oldPassword;

    // 변경 할 비밀번호
    private String newPassword;
}
