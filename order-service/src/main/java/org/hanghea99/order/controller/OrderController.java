package org.hanghea99.order.controller;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.dto.OrderDto;
import org.hanghea99.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserOrders(@PathVariable Long userId) {
        try {
            Object object = orderService.getUserOrders(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(object);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
