package com.hanghea99.commercial.member.dto;

import com.hanghea99.commercial.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    String memberName;
    String phoneNumber;
    String password;
    String email;
    String city;
    String detail;
    Integer zipCode;
}
