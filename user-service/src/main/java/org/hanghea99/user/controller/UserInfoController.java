package org.hanghea99.user.controller;

import org.hanghea99.user.service.UserInfoService;
import org.hanghea99.user.dto.UpdateInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/info/{userId}")
public class UserInfoController {
    private final UserInfoService userInfoService;

    @GetMapping("")
    public ResponseEntity<?> getUserInfo(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(userInfoService.getInfo(userId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("")
    public ResponseEntity<?> updateUserInfo(@PathVariable long userId,
                                            @RequestBody UpdateInfoDto updateInfoDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userInfoService.updateInfo(userId, updateInfoDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
