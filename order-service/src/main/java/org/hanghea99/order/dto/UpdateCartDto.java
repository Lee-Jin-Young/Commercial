package org.hanghea99.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartDto {
    // 상품 id
    private Long productId;
    // 주문 개수
    private Long quantity;
}
