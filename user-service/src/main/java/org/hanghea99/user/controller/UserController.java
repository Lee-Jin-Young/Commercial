package org.hanghea99.user.controller;

import org.hanghea99.user.dto.LoginDto;
import org.hanghea99.user.dto.SignUpDto;
import org.hanghea99.user.dto.UpdatePasswordDto;
import org.hanghea99.user.service.AuthService;
import org.hanghea99.user.service.SignUpService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final SignUpService signUpService;
    private final AuthService authService;

    @GetMapping("/email-auth")
    public ResponseEntity<?> emailAuth(@RequestParam String email) {
        Object object = signUpService.emailAuthentication(email);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        try {
            Object object = signUpService.signup(signUpDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(object);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/log-in")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            String token = authService.authenticateUser(loginDto);
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/log-out")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // 세션 무효화
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // 로그아웃 성공 메시지 반환
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    @PostMapping("/update/password")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.updatePassword(updatePasswordDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
