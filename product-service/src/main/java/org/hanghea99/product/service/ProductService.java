package org.hanghea99.product.service;

import lombok.RequiredArgsConstructor;
import org.hanghea99.product.domain.KeyOrderType;
import org.hanghea99.product.domain.Product;
import org.hanghea99.product.domain.ProductDetail;
import org.hanghea99.product.dto.ProductDetailDto;
import org.hanghea99.product.dto.ProductDto;
import org.hanghea99.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<ProductDto> getList(String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByKeyOrderTypeOrderType(type, pageable)
                .map(this::convertToDto);
    }

    // mapper
    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        productDto.setOrderType(product.getKeyOrderType().getOrderType());
        return productDto;
    }

    public Object getDetail(Long productId) {
        // 없는 물건에 대해 임의로 입력해서 들어올 경우
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 제품을 찾을 수 없습니다."));
        ProductDetail detail = product.getDetail();
        if (detail == null) {
            throw new IllegalArgumentException("상세정보를 찾을 수 없습니다.");
        }

        ProductDetailDto productDto = new ProductDetailDto();
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(detail.getDescription());
        return productDto;
    }

    public Object getStock(Long productId) {
        // 없는 물건에 대해 임의로 입력해서 들어올 경우
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 제품을 찾을 수 없습니다."));

        return product.getStock();
    }
}
