//package com.hanghea99.commercial.order.domain;
//
//import com.hanghea99.commercial.product.domain.Product;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Entity
//@Table(name = "order", schema = "${schema.name}")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder(toBuilder = true)
//public class Order {
//
//    @Id
//    @Column(name = "order_id")
//    UUID orderId;
//
//    @Column(name = "member_id")
//    UUID memberId;
//
//
//    @Column(name = "order_date")
//    LocalDateTime orderDate;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "order_status_key_id")
//    OrderStatusKey orderStatusKey;
//}
