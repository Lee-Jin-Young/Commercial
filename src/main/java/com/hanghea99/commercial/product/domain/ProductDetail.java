package com.hanghea99.commercial.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "product_detail", schema = "${schema.name}")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ProductDetail {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "product_detail_id", columnDefinition = "binary(16)", nullable = false)
    private UUID productDetailId;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
