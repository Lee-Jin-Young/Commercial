package com.hanghea99.commercial.user.repository;

import com.hanghea99.commercial.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(Long userId);

    Optional<User> findByUserName(String userName);
}
