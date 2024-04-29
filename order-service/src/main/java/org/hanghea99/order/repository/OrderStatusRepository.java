package org.hanghea99.order.repository;

import org.hanghea99.order.domain.KeyOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<KeyOrderStatus, Long> {
}
