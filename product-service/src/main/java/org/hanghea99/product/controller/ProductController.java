package org.hanghea99.product.controller;

import org.hanghea99.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<?> productList(@RequestParam String type,
                                         @RequestParam int page,
                                         @RequestParam int size) {
        return ResponseEntity.ok(productService.getList(type, page, size));
    }
}
