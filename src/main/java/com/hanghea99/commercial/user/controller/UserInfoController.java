package com.hanghea99.commercial.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/{userId}/info")
public class UserInfoController {
    @GetMapping("")
    public ResponseEntity<?> getUserInfo() {
        Object object = new Object();
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUserInfo() {
        Object object = new Object();
        return new ResponseEntity<>(object, HttpStatus.CREATED);
    }
}
