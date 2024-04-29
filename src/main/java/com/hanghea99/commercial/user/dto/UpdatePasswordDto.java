package com.hanghea99.commercial.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordDto {
    //pk
    private Long userId;

    // 기존 비밀번호
    private String oldPassword;

    // 변경 할 비밀번호
    private String newPassword;
}
