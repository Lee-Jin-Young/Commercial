package org.hanghea99.product.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Long price;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "type")
    private String type;
}
