package com.hanghea99.commercial.user.service;

import com.hanghea99.commercial.user.domain.Member;
import com.hanghea99.commercial.user.dto.LoginDto;
import com.hanghea99.commercial.user.repository.MemberRepository;
import com.hanghea99.commercial.utilAndSecurity.secure.EncryptService;
import com.hanghea99.commercial.utilAndSecurity.secure.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EncryptService encryptService;

    public String authenticateUser(LoginDto loginDto) {
        // DB에는 암호와 되어 들어가 있으므로
        String encryptedEmail = encryptService.encrypt(loginDto.getEmail());

        // 이메일과 비밀번호로 인증된 사용자인지 확인
        Member member = memberRepository.findByEmail(encryptedEmail);

        // 만일 이메일에 해당 되는 사용자가 없을 경우
        if (member == null) {
            throw new IllegalArgumentException("유효하지 않은 이메일입니다.");
        }

        // 비밀번호 검증
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 JWT 토큰 생성
        return jwtUtil.createAccessToken(loginDto);
    }
}
