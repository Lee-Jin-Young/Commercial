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
@Table(name = "member", schema = "${schemaName}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Member {
    @Id
    @Column(name = "member_id", columnDefinition = "binary(16)", nullable = false)
    UUID memberId;

    @Column(name = "member_name")
    String memberName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address_id")
    UUID addressId;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;
}
