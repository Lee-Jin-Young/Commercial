package org.hanghea99.order.controller;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.dto.UpdateCartDto;
import org.hanghea99.order.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/cart/{userId}")
public class CartController {
    private final CartService cartService;

    @GetMapping("")
    public ResponseEntity<?> getCart(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(cartService.getInfo(userId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> updateCart(@PathVariable Long userId,
                                        @RequestBody List<UpdateCartDto> updateCarttDto) {
        try {
            cartService.updateCart(userId, updateCarttDto);
            return ResponseEntity.ok("장바구니가 수정 되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
