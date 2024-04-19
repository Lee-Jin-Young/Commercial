package com.hanghea99.commercial.member.repository;

import com.hanghea99.commercial.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
}
