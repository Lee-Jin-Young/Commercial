package com.hanghea99.commercial.member.controller;

import com.hanghea99.commercial.member.dto.MemberLoginDto;
import com.hanghea99.commercial.member.dto.SignUpDto;
import com.hanghea99.commercial.member.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member/sign_up")

public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {
        Object object = signUpService.signup(signUpDto);
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @GetMapping("email-auth")
    public ResponseEntity<?> emailAuth(@RequestParam String email) {
        Object object = signUpService.emailAuthentication(email);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
}
