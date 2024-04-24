package com.hanghea99.commercial.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member/{memberId}/address")
public class MemberAddressController {
    @PostMapping("/")
    public ResponseEntity<?> newAddress() {
        Object object = new Object();
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddress() {
        Object object = new Object();
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }
}
