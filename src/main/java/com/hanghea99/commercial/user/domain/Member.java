package com.hanghea99.commercial.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "member", schema = "${schema.name}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Member {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "member_id", columnDefinition = "binary(16)", nullable = false)
    UUID memberId;

    @Column(name = "member_name")
    String memberName;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;
}
