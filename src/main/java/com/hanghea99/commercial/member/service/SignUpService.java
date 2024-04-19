package com.hanghea99.commercial.member.service;

import com.hanghea99.commercial.member.dto.MemberDto;
import com.hanghea99.commercial.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    private MemberRepository memberRepository;

    public Object signup(MemberDto memberDto) {
        memberDto.setMemberName("1234");
        return memberDto;
    }

    public Object emailAuthentication(String email) {

        return email;
    }
}
