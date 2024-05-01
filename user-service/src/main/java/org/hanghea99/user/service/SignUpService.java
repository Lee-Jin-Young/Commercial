package org.hanghea99.user.service;

import org.hanghea99.user.repository.KeyRoleRepository;
import org.hanghea99.user.domain.KeyRole;
import org.hanghea99.user.domain.User;
import org.hanghea99.user.dto.SignUpDto;
import org.hanghea99.user.repository.UserRepository;
import org.hanghea99.user.utilAndSecurity.secure.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final EncryptService encryptService;

    private final UserRepository userRepository;
    private final KeyRoleRepository keyRoleRepository;

    // ADMIN_TOKEN TODO: Admin 개발 시 변경
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public Object signup(SignUpDto signUpDto) {
        // 데이터 암호화 (db와 비교 편하도록 먼저 진행)
        String userName = encryptService.encrypt(signUpDto.getUserName());
        String phoneNumber = encryptService.encrypt(signUpDto.getPhoneNumber());
        String email = encryptService.encrypt(signUpDto.getEmail());

        // 중복확인
        userRepository.findByUserName(userName).ifPresent(u -> {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        });
        userRepository.findByEmail(email).ifPresent(u -> {
            throw new IllegalArgumentException("중복된 이메일이 존재합니다.");
        });

        // 사용자 ROLE 확인
        String role = "user";
        if (signUpDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(signUpDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 올바르지 않습니다.");
            }
            role = "admin";
        }
        KeyRole keyRole = keyRoleRepository.findByUserRole(role).orElseThrow(() -> new IllegalArgumentException("역할을 찾을 수 없습니다."));


        //비밀번호 단방향 암호화
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPwd = encoder.encode(signUpDto.getPassword());

        //엔티티 생성
        User user = User.builder()
                .userName(userName)
                .phoneNumber(phoneNumber)
                .password(encodedPwd)
                .email(email)
                .keyRoleId(keyRole.getKeyRoleId())
                .build();

        //DB에 회원 정보 저장하기
        userRepository.save(user);

        return user.getUserId();
    }

    public Object emailAuthentication(String email) {
        // TODO: Redis 적용하여 개발
//        emailAuthRepository;
        return email;
    }
}
