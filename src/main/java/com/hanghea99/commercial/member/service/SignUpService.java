package com.hanghea99.commercial.member.service;

import com.hanghea99.commercial.member.dto.MemberLoginDto;
import com.hanghea99.commercial.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private MemberRepository memberRepository;

    public Object signup(MemberLoginDto memberDto) {
        //비밀번호를 암호화해줄 객체를 생성
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //암호화된 비밀번호 얻어내서
//        String encodedPwd = encoder.encode(dto.getPwd());
        //UsersDto 객체에 담고
//        dto.setPwd(encodedPwd););
        //UsersDao 객체를 이용해서 DB에 저장하기
//        dao.insert(dto);

        return memberDto;
    }

    //아이디 중복체크
    public boolean isIdExist(String loginId) {
//        return dao.isExist(loginId) != 0;
    }

    public Object emailAuthentication(String email) {
        // TODO: Redis 적용하여 개발
//        emailAuthRepository;
        return email;
    }
}
