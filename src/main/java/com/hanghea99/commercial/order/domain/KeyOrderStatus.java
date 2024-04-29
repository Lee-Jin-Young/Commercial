package com.hanghea99.commercial.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "key_order_status")
@Getter
@Setter
public class KeyOrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_order_status_id")
    Long orderStatusKeyId;

    @Column(name = "order_status", nullable = false)
    String orderStatus;
}
