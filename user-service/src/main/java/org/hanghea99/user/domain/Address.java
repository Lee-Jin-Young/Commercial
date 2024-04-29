package org.hanghea99.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "detail")
    private String detail;

    @Column(name = "zip_code", nullable = false)
    private Integer zipCode;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
