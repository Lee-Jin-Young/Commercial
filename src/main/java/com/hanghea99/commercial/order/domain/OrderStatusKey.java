package com.hanghea99.commercial.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "order_status_key", schema = "${schema.name}")
@Data
public class OrderStatusKey {
    @Id
    @Column(name = "order_status_key_id")
    UUID orderStatusKeyId;

    @Column(name = "order_status")
    String orderStatus;
}
