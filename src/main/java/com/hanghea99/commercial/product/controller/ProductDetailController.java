package com.hanghea99.commercial.product.controller;

import com.hanghea99.commercial.product.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/add")
public class ProductDetailController {
    private final ProductDetailService productService;

    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestParam String productName,
                                        @RequestParam Long price) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(productName, price));
    }
}
