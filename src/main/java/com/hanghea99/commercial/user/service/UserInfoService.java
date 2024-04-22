package com.hanghea99.commercial.user.service;

import com.hanghea99.commercial.user.domain.Member;
import com.hanghea99.commercial.user.dto.UpdateInfoDto;
import com.hanghea99.commercial.user.dto.UserInfoDto;
import com.hanghea99.commercial.user.repository.MemberRepository;
import com.hanghea99.commercial.utilAndSecurity.secure.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final MemberRepository memberRepository;
    private final EncryptService encryptService;

    public UserInfoDto getInfo(UUID memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        // 복호화 된 데이터
        return encryptUserInfoDto(member);
    }

    private UserInfoDto encryptUserInfoDto(Member member) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail(encryptService.decrypt(member.getEmail()));
        userInfoDto.setMemberName(encryptService.decrypt(member.getMemberName()));
        userInfoDto.setPhoneNumber(encryptService.decrypt(member.getPhoneNumber()));
        return userInfoDto;
    }

    public Object updateInfo(UUID memberId, UpdateInfoDto updateInfoDto) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        Member newMember = member.toBuilder()
                .phoneNumber(encryptService.encrypt(updateInfoDto.getPhoneNumber()))
                .build();
        memberRepository.save(newMember);

        // 회원 정보 수정 성공 시
        return member.getMemberId();
    }
}
