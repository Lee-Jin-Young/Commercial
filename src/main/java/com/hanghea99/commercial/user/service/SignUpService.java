package com.hanghea99.commercial.user.service;

import com.hanghea99.commercial.user.domain.Member;
import com.hanghea99.commercial.user.dto.SignUpDto;
import com.hanghea99.commercial.user.repository.MemberRepository;
import com.hanghea99.commercial.utilAndSecurity.secure.EncryptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EncryptService encryptService;

    public Object signup(SignUpDto signUpDto) {
        String encryptedName = encryptService.encrypt(signUpDto.getMemberName());
        String encryptedPhoneNum = encryptService.encrypt(signUpDto.getPhoneNumber());
        String encryptedEmail = encryptService.encrypt(signUpDto.getEmail());

        //비밀번호 단방향 암호화
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(signUpDto.getPassword());

        //엔티티 생성
        Member member = Member.builder()
                .memberName(encryptedName)
                .phoneNumber(encryptedPhoneNum)
                .password(encodedPwd)
                .email(encryptedEmail)
                .build();

        //DB에 회원 정보 저장하기
        memberRepository.save(member);

        return member.getMemberId();
    }

    public Object emailAuthentication(String email) {
        // TODO: Redis 적용하여 개발
//        emailAuthRepository;
        return email;
    }
}
