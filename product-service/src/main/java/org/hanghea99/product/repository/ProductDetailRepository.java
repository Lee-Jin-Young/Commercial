package org.hanghea99.product.repository;

import org.hanghea99.product.domain.Product;
import org.hanghea99.product.domain.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
}
