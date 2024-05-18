package org.hanghea99.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
    // 상품 id
    private Long productId;
    // 상품 명
    private String title;
    // 주문 개수
    private Long quantity;
    // 가격
    private Long price;
}
