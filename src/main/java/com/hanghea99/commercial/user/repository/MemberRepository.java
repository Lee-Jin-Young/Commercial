package com.hanghea99.commercial.user.repository;

import com.hanghea99.commercial.user.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByEmail(String email);

    Optional<Member> findByMemberId(UUID memberId);
}
