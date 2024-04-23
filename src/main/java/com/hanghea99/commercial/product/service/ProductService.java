package com.hanghea99.commercial.product.service;

import com.hanghea99.commercial.product.domain.Product;
import com.hanghea99.commercial.product.dto.ProductDto;
import com.hanghea99.commercial.product.repository.ProductDetailRepository;
import com.hanghea99.commercial.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public Object getList(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Object getDetail(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("없는 물건입니다."));

        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
