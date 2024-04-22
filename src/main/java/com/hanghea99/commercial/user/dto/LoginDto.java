package com.hanghea99.commercial.user.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class LoginDto {
    private UUID memberId;
    private String email;
    private String password;
}
