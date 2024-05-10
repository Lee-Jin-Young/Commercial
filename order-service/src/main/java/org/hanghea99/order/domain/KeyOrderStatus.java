package org.hanghea99.order.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "key_order_status")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyOrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_order_status_id")
    private Long orderStatusKeyId;

    @Column(name = "order_status", nullable = false)
    private String orderStatus;
}
