package org.hanghea99.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
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

    @Column(name = "quantity", nullable = false)
    Long quantity;

    @JoinColumn(name = "user_id", nullable = false)
    Long userId;

    @JoinColumn(name = "product_id", nullable = false)
    Long productId;
}
