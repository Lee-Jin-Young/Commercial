package com.hanghea99.commercial.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginDto {
    UUID memberId;
    String email;
}
