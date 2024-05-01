package org.hanghea99.product.service;

import org.hanghea99.product.domain.Product;
import org.hanghea99.product.domain.ProductDetail;
import org.hanghea99.product.repository.ProductDetailRepository;
import org.hanghea99.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public Object addProduct(String productName, Long price) {
        Product product = Product.builder()
                .title(productName)
                .price(price)
                .build();
        productRepository.save(product);

        ProductDetail detail = ProductDetail.builder()
                .size("M")
                .color("Black")
                .productId(product.getId())
                .build();
        productDetailRepository.save(detail);

        ProductDetail detail2 = ProductDetail.builder()
                .size("M")
                .color("White")
                .productId(product.getId())
                .build();
        productDetailRepository.save(detail2);

        return product.getId();
    }
}
