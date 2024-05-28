package com.walkak.modakfire.repository;

import com.walkak.modakfire.domain.Center;
import com.walkak.modakfire.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,String> {
}
