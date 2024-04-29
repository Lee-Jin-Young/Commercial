package org.hanghea99.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private Long userId;
    private String email;
    private String password;
}
