package com.hanghea99.commercial.user.controller;

import com.hanghea99.commercial.user.domain.Member;
import com.hanghea99.commercial.user.dto.LoginDto;
import com.hanghea99.commercial.user.dto.SignUpDto;
import com.hanghea99.commercial.user.repository.MemberRepository;
import com.hanghea99.commercial.user.service.AuthService;
import com.hanghea99.commercial.user.service.SignUpService;
import com.hanghea99.commercial.utilAndSecurity.secure.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private AuthService authService;

    @GetMapping("/email-auth")
    public ResponseEntity<?> emailAuth(@RequestParam String email) {
        Object object = signUpService.emailAuthentication(email);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        Object object = signUpService.signup(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(object);
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
}
