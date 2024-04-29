package com.hanghea99.commercial.product.controller;

import com.hanghea99.commercial.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<?> productList(@RequestParam int page,
                                         @RequestParam int size) {
        return ResponseEntity.ok(productService.getList(page, size));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> productDetail(@PathVariable Long productId) {
        try {
            return ResponseEntity.ok(productService.getDetail(productId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
