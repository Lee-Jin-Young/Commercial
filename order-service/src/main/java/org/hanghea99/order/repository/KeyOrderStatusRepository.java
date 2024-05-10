package org.hanghea99.order.repository;

import org.hanghea99.order.domain.KeyOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyOrderStatusRepository extends JpaRepository<KeyOrderStatus, Long> {
}
