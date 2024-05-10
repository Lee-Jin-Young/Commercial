package org.hanghea99.order.client;

import org.hanghea99.order.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// TODO : 추후 Eureka 사용
@FeignClient(name = "product-service", url = "http://localhost:8002")
public interface ProductClient {
    @GetMapping("/api/products/{productId}")
    ProductDto getProductById(@PathVariable Long productId);

    @GetMapping("/api/products")
    List<ProductDto> getAllProducts();
}
