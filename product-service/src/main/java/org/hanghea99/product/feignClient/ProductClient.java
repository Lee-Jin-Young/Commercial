package org.hanghea99.product.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("/client/product/{productId}")
public interface ProductClient {
    @GetMapping("")
    public ResponseEntity<?> productDetail(@PathVariable Long productId);
}
