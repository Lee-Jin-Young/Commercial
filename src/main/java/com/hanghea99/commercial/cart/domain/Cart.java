package com.hanghea99.commercial.cart.domain;

import com.hanghea99.commercial.member.domain.Member;
import com.hanghea99.commercial.product.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "cart", schema = "${schema.name}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Cart {
    @Id
    @Column(name = "cart_id")
    UUID wishListId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    Member member;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
