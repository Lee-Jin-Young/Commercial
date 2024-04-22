package com.hanghea99.commercial.user.controller;

import com.hanghea99.commercial.user.dto.UpdateInfoDto;
import com.hanghea99.commercial.user.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/info/{memberId}")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping("")
    public ResponseEntity<?> getUserInfo(@PathVariable UUID memberId) {
        try {
            return ResponseEntity.ok(userInfoService.getInfo(memberId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("")
    public ResponseEntity<?> updateUserInfo(@PathVariable UUID memberId,
                                            @RequestBody UpdateInfoDto updateInfoDto)
    {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userInfoService.updateInfo(memberId, updateInfoDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
