package org.hanghea99.product.repository;

import org.hanghea99.product.domain.KeyOrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeyOrderTypeRepository  extends JpaRepository<KeyOrderType, Long> {
}
