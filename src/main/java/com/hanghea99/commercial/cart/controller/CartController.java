package com.hanghea99.commercial.cart.controller;

import com.hanghea99.commercial.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/cart/{memberId}")
public class CartController {
    private final CartService cartService;

    @GetMapping("")
    public ResponseEntity<?> getCart(@PathVariable UUID memberId) {
        try {
            return ResponseEntity.ok(cartService.getInfo(memberId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable UUID memberId,
                                           @PathVariable UUID productId) {
        try {
            return ResponseEntity.ok(cartService.getInfo(memberId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID memberId,
                                           @PathVariable UUID productId) {
        try {
            return ResponseEntity.ok(cartService.getInfo(memberId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
