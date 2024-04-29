package com.hanghea99.commercial.order.repository;

import com.hanghea99.commercial.order.domain.KeyOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<KeyOrderStatus, Long> {
}
