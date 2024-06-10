package org.hanghea99.order.entity;

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
    private Long keyOrderStatusId;

    @Column(name = "key_order_status", nullable = false)
    private String status;
}
