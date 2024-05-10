package org.hanghea99.order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_id")
    private Long orderStatusId;

    @Column(name = "regdate")
    private LocalDateTime regdate;

    @Column(name = "is_now")
    private Boolean isNow;

    @JoinColumn(name = "order_status_key_id", nullable = false)
    private Long orderStatusKeyId;

    @JoinColumn(name = "order_id", nullable = false)
    private Long orderId;
}
