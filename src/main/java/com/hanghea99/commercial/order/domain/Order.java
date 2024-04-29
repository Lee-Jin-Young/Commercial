package com.hanghea99.commercial.order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long orderId;

    @Column(name = "order_date", nullable = false)
    LocalDateTime orderDate;

    @JoinColumn(name = "user_id", nullable = false)
    Long userId;

    @JoinColumn(name = "product_id", nullable = false)
    Long productId;

    @JoinColumn(name = "order_status_key_id", nullable = false)
    Long orderStatusKeyId;
}
