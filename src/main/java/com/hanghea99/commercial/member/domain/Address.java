package com.hanghea99.commercial.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "address", schema = "${schema.name}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Address {
    @Id
    @Column(name = "address_id")
    UUID addressId;

    @Column(name = "city")
    String city;

    @Column(name = "detail")
    String detail;

    @Column(name = "zip_code")
    Integer zipCode;

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;
}
