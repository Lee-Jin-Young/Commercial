package org.hanghea99.order.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {
    // 주문 id
    private Long orderId;
    // 주문 일자
    private LocalDateTime orderDate;
    // 배송 상태
    private String keyOrderStatus;
    // 상품 명
    private String title;
    // 주문 개수
    private Long quantity;
    // 가격
    private Long price;
}
