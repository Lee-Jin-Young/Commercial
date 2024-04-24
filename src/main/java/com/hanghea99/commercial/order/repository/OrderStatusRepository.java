package com.hanghea99.commercial.order.repository;

import com.hanghea99.commercial.order.domain.OrderStatusKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusKey, UUID> {
}
