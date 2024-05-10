package org.hanghea99.product.controller;

import lombok.RequiredArgsConstructor;
import org.hanghea99.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products/{productId}")
public class ProductDetailController {
    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> productDetail(@PathVariable Long productId) {
        try {
            return ResponseEntity.ok(productService.getDetail(productId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/stock")
    public ResponseEntity<?> productStock(@PathVariable Long productId) {
        try {
            return ResponseEntity.ok(productService.getStock(productId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
