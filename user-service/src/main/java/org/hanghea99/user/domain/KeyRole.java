package org.hanghea99.user.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "key_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KeyRole {
    @Id
    @Column(name = "key_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long KeyRoleId;

    @Column(name = "user_role", nullable = false, unique = true)
    private String userRole;
}

