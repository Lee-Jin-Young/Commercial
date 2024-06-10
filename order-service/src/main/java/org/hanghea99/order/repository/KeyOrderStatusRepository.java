package org.hanghea99.order.repository;

import org.hanghea99.order.entity.KeyOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyOrderStatusRepository extends JpaRepository<KeyOrderStatus, Long> {
    @Query("SELECT t.keyOrderStatusId " +
            "FROM KeyOrderStatus t " +
            "WHERE t.status =:status")
    Long findIdByStatus(String status);

    KeyOrderStatus findByKeyOrderStatusId(Long keyOrderStatusId);
}
