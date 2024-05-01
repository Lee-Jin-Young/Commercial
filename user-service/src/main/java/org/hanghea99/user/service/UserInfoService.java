package org.hanghea99.user.service;

import org.hanghea99.user.domain.User;
import org.hanghea99.user.dto.UpdateInfoDto;
import org.hanghea99.user.dto.UserInfoDto;
import org.hanghea99.user.repository.UserRepository;
import org.hanghea99.user.utilAndSecurity.secure.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserRepository userRepository;
    private final EncryptService encryptService;

    public UserInfoDto getInfo(Long userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        // 복호화 된 데이터
        return encryptUserInfoDto(user);
    }

    private UserInfoDto encryptUserInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(encryptService.decrypt(user.getEmail()));
        userInfoDto.setUserName(encryptService.decrypt(user.getUserName()));
        userInfoDto.setPhoneNumber(encryptService.decrypt(user.getPhoneNumber()));
        return userInfoDto;
    }

    public Object updateInfo(Long userId, UpdateInfoDto updateInfoDto) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        User newUser = user.toBuilder()
                .phoneNumber(encryptService.encrypt(updateInfoDto.getPhoneNumber()))
                .build();
        userRepository.save(newUser);

        // 회원 정보 수정 성공 시
        return user.getUserId();
    }
}
