package org.hanghea99.product.service;

import org.hanghea99.product.dto.ProductDto;
import org.hanghea99.product.domain.Product;
import org.hanghea99.product.repository.ProductDetailRepository;
import org.hanghea99.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;

    public Object getList(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Object getDetail(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("없는 물건입니다."));

        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
