package org.hanghea99.user.repository;

import org.hanghea99.user.domain.KeyRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeyRoleRepository extends JpaRepository<KeyRole, Long> {
    Optional<KeyRole> findByUserRole(String userRole);
}
