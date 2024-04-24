package com.hanghea99.commercial.member.service;

import com.hanghea99.commercial.member.domain.Member;
import com.hanghea99.commercial.member.dto.LoginDto;
import com.hanghea99.commercial.member.dto.UpdatePasswordDto;
import com.hanghea99.commercial.member.repository.MemberRepository;
import com.hanghea99.commercial.utilAndSecurity.secure.EncryptService;
import com.hanghea99.commercial.utilAndSecurity.secure.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EncryptService encryptService;

    public String authenticateMember(LoginDto loginDto) {
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

    public Object updatePassword(UpdatePasswordDto updatePasswordDto) {
        UUID memberId = updatePasswordDto.getMemberId();
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        // 비밀번호 검증
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(updatePasswordDto.getOldPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String encodedPwd = encoder.encode(updatePasswordDto.getNewPassword());
        Member newMember = member.toBuilder()
                .password(encodedPwd)
                .build();
        memberRepository.save(newMember);

        // 회원 정보 수정 성공 시
        return member.getMemberId();
    }
}
