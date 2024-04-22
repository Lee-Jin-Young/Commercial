package com.hanghea99.commercial.user.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoDto {
    String memberName;
    String phoneNumber;
    String email;
}
