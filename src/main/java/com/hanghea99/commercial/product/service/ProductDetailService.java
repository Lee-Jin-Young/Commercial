package com.hanghea99.commercial.product.service;

import com.hanghea99.commercial.product.domain.Product;
import com.hanghea99.commercial.product.domain.ProductDetail;
import com.hanghea99.commercial.product.repository.ProductDetailRepository;
import com.hanghea99.commercial.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public Object addProduct(String productName, Long price) {
        Product product = Product.builder()
                .productName(productName)
                .price(price)
                .build();
        productRepository.save(product);

        ProductDetail detail = ProductDetail.builder()
                .size("M")
                .color("Black")
                .productId(product.getProductId())
                .build();
        productDetailRepository.save(detail);

        ProductDetail detail2 = ProductDetail.builder()
                .size("M")
                .color("White")
                .productId(product.getProductId())
                .build();
        productDetailRepository.save(detail2);

        return product.getProductId();
    }
}
