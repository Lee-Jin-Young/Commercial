package com.hanghea99.commercial.member.controller;

import com.hanghea99.commercial.member.dto.UpdateInfoDto;
import com.hanghea99.commercial.member.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member/info/{memberId}")
public class MemberInfoController {
    private final MemberInfoService memberInfoService;

    @GetMapping("")
    public ResponseEntity<?> getMemberInfo(@PathVariable UUID memberId) {
        try {
            return ResponseEntity.ok(memberInfoService.getInfo(memberId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("")
    public ResponseEntity<?> updateMemberInfo(@PathVariable UUID memberId,
                                            @RequestBody UpdateInfoDto updateInfoDto)
    {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(memberInfoService.updateInfo(memberId, updateInfoDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
