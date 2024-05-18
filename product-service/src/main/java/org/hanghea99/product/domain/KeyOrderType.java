package org.hanghea99.product.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "key_order_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class KeyOrderType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_order_type_id")
    private Long KeyOrderTypeId;

    @Column(name = "order_type")
    private String orderType;
}
