package org.hanghea99.order.controller;

import lombok.RequiredArgsConstructor;
import org.hanghea99.order.dto.PaymentRequest;
import org.hanghea99.order.dto.StartPaymentRequest;
import org.hanghea99.order.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/start")
    public ResponseEntity<String> startPayment(@RequestBody StartPaymentRequest request) {
        boolean success = paymentService.startPaymentProcess(request.getProductId(), request.getUserId());
        if (success) {
            return ResponseEntity.ok("Payment started successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to start payment. Please try again later.");
        }
    }

    @PostMapping("/complete")
    public ResponseEntity<String> completePayment(@RequestBody PaymentRequest request) {
        paymentService.completePaymentProcess(request.getProductId(), request.getOrderId());
        return ResponseEntity.ok("Payment completed successfully.");
    }

    @PostMapping("/fail")
    public ResponseEntity<String> failPayment(@RequestBody PaymentRequest request) {
        paymentService.failPaymentProcess(request.getProductId(), request.getOrderId());
        return ResponseEntity.ok("Payment failed and reverted successfully.");
    }
}
