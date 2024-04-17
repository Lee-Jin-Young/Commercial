package com.hanghea99.commercial.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "address", schema = "${schemaName}")
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
}
