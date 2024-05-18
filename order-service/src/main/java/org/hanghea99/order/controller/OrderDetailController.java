package org.hanghea99.order.controller;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.service.OrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    // 주문 내역 리스트
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserOrders(@PathVariable Long userId) {
        try {
            Object object = orderDetailService.getUserOrders(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(object);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 주문 취소
    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<?> cancelOrder(@PathVariable Long orderId) {
        try {
            // 주문 취소를 처리하는 메서드 호출
            orderDetailService.cancelOrder(orderId);
            return ResponseEntity.ok("주문이 취소되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 상품 반품
    @PostMapping("/{orderId}/return")
    public ResponseEntity<?> returnProduct(@PathVariable Long orderId) {
        try {
            // 상품 반품을 처리하는 메서드 호출
            orderDetailService.returnProduct(orderId);
            return ResponseEntity.ok("상품이 반품 준비 중 입니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
