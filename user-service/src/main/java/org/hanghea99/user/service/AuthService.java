package org.hanghea99.user.service;

import org.hanghea99.user.domain.User;
import org.hanghea99.user.dto.UpdatePasswordDto;
import org.hanghea99.user.repository.UserRepository;
import org.hanghea99.user.dto.LoginDto;
import org.hanghea99.user.utilAndSecurity.secure.EncryptService;
import org.hanghea99.user.utilAndSecurity.secure.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;
    private final EncryptService encryptService;

    public String authenticateUser(LoginDto loginDto) {
        // DB에는 암호와 되어 들어가 있으므로
        String encryptedEmail = encryptService.encrypt(loginDto.getEmail());

        // 이메일 인증된 사용자인지 확인
        User user = userRepository.findByEmail(encryptedEmail).orElseThrow(() -> new IllegalArgumentException("유효하지 않은 이메일입니다."));

        // 비밀번호 검증
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 JWT 토큰 생성
        return jwtUtil.createAccessToken(loginDto);
    }

    public Object updatePassword(UpdatePasswordDto updatePasswordDto) {
        Long userId = updatePasswordDto.getUserId();
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        // 비밀번호 검증
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(updatePasswordDto.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String encodedPwd = encoder.encode(updatePasswordDto.getNewPassword());
        User newUser = user.toBuilder().password(encodedPwd).build();
        userRepository.save(newUser);

        // 회원 정보 수정 성공 시
        return user.getUserId();
    }
}
