package com.hanghea99.commercial.member.service;

import com.hanghea99.commercial.member.domain.Member;
import com.hanghea99.commercial.member.dto.UpdateInfoDto;
import com.hanghea99.commercial.member.dto.MemberInfoDto;
import com.hanghea99.commercial.member.repository.MemberRepository;
import com.hanghea99.commercial.utilAndSecurity.secure.EncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberInfoService {
    private final MemberRepository memberRepository;
    private final EncryptService encryptService;

    public MemberInfoDto getInfo(UUID memberId) {
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));

        // 복호화 된 데이터
        return encryptMemberInfoDto(member);
    }

    private MemberInfoDto encryptMemberInfoDto(Member member) {
        MemberInfoDto memberInfoDto = new MemberInfoDto();
        memberInfoDto.setEmail(encryptService.decrypt(member.getEmail()));
        memberInfoDto.setMemberName(encryptService.decrypt(member.getMemberName()));
        memberInfoDto.setPhoneNumber(encryptService.decrypt(member.getPhoneNumber()));
        return memberInfoDto;
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
